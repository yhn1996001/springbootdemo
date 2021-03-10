package com.sean.webcrawler.task;


import com.sean.webcrawler.pojo.JournalOut;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

@Component
public class JobAIS extends JOB {

	protected Logger logger= LoggerFactory.getLogger(getClass());

		@Override
		public void process(Page page) {
				Json json = page.getJson();
				//$.store.book[*].author	獲取json中store下book下的所有author值
				String count="";
				try {
						count = json.jsonPath("$.count").toString();
				}catch (Exception e){}

				switch (count){
						case "": // 每個 journal 頁面
								logger.info("count==''");
								savePhmail(page);
								break;
						default: // 解析Json URL
								saveJson(page);
				}
			/*
				if (count!=""){
						String start = json.jsonPath("$.start").toString();
						List<String> all = json.jsonPath("$.docs[*].url").all();
						for (String s : all) {
								logger.info(s);
								page.addTargetRequest(s);
						}
						String replace = page
								.getUrl().toString().replace(String.format("start=%s", start), String.format("start=%d",Integer.parseInt(start) + 25));
						if (Integer.parseInt(count)!=0)
								page.addTargetRequest(replace);
				}else {
						logger.info("count==''");
						savePhmail(page);
				}
				*/
		}

		@Override
		public void savePhmail(Page page) {
				Html html = page.getHtml();
				List<String> author = html.css("head > meta[property=article:author]", "content").all();
				String title = html.css("head > meta[property=og:title]", "content").toString();
				String journalName= html.css("head > meta[name=bepress_citation_journal_title]", "content").toString();
				String Year = html.css("head > meta[name=bepress_citation_date]", "content").toString();
				String volume = html.css("head > meta[name=bepress_citation_volume]", "content").toString();
				String keywords = html.css("head > meta[name=keywords]", "content").toString();
				String type = html.css("head > meta[property=og:type]", "content").toString();
				String firstpag = html.css("head > meta[name=bepress_citation_firstpage]", "content").toString();
				String issn = html.css("head > meta[name=bepress_citation_issn]", "content").toString();
				String Abstract  = html.css("head > meta[name=description]", "content").toString();
				String pdf_url = html.css("head > meta[name=bepress_citation_pdf_url]", "content").toString();
				String url = page.getUrl().toString();
				String strAuthor="";
				for (String s : author) {
						strAuthor=strAuthor+s+";";
				}
				JournalOut journal=new JournalOut();
				journal.setAuther(strAuthor);
				journal.setTitle(title);
				journal.setJournalName(journalName);
				journal.setYear(Year);
				journal.setVolume(volume);
				journal.setKeyword(keywords);
				journal.setType(type);
				journal.setFirstpag(firstpag);
				journal.setIssn(issn);
				journal.setAbstract(Abstract);
				journal.setUrl(url);
				journal.setDownload_link(pdf_url);

				logger.info(journal.getTitle());
				page.putField("journal",journal);

		}
		// 解析出子頁面
		// 解析出下一個Json
		public void  saveJson(Page page){
				Json json = page.getJson();
				String start = json.jsonPath("$.start").toString();
				List<String> download_link = json.jsonPath("$.docs[*].download_link").all();
				List<String> title = json.jsonPath("$.docs[*].title").all();
				page.putField("download_link",download_link);
				page.putField("title",title);

				/*
 				Json json = page.getJson();
				String start = json.jsonPath("$.start").toString();
				List<String> all = json.jsonPath("$.docs[*].url").all();
				for (String s : all) {
						logger.info(s);
						page.addTargetRequest(s); //子頁面加入對列
				}
				String replace = page
						.getUrl().toString().replace(String.format("start=%s", start), String.format("start=%d",Integer.parseInt(start) + 25));
						page.addTargetRequest(replace);//Json加入對列

				 */
		}

}
