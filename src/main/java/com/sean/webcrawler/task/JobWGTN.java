package com.sean.webcrawler.task;

import com.sean.webcrawler.pojo.Phemail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class JobWGTN implements PageProcessor {
		private Site site=Site.me()
				.setCharset("UTF-8")//設定編碼
				.setRetryTimes(3)//重試次數
				.setCycleRetryTimes(3000)//重試間隔時間
				.setTimeOut(10*1000) //超時，時間
				;//

		private String url="https://www.wgtn.ac.nz/sim/about/staff";

		@Override
		public void process(Page page) {
				List<String> all = page.getHtml().css("div.content-panel").all();
				if (all.size()!=0)
					this.savePhmail(page);
		}
//
		private void savePhmail(Page page) {
				// 獲得HTML
				final Html html = page.getHtml();
				String[] title = html.css("title","text").toString().trim().split(" \\| ");
				//
				List<String> all =  html.css("div.content-panel").css("div.result-body").css("h3>a","text" ).all();
				List<String> all1 = html.css("div.content-panel div.result-body > div.result-job-group-wrapper li:nth-child(1) > a > span:nth-child(2)", "text").all();
				List<Phemail> all2=new ArrayList<>(all.size());
				for (int i = 0; i < all.size(); i++) {
						System.out.println(all.get(i) +" "+ all1.get(i));
						System.out.println("----------------------------");
						Phemail phemail=new Phemail();
						phemail.setUniversity(title[2].trim());
						phemail.setDepartment(title[1].trim());
						phemail.setPhName(all.get(i).trim());
						phemail.setEmail(all1.get(i).trim());
						all2.add(phemail);
				}
				page.putField("list",all2);
		}

		@Override
		public Site getSite() {
				return site;
		}
		public String getUrl() {
				return url;
		}

		public void setUrl(String url) {
				this.url = url;
		}
}
