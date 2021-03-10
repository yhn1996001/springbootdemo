package com.sean.webcrawler.task;

import java.util.List;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Component
public class JobCAIS extends JobAIS {
		@Override
		public void process(Page page) {
				Html html = page.getHtml();
				List<String> all = html.css("#toc div.item h2 > a", "href").all();

//#teaching_case~div.doc:not(#main > div.article-list > div+h2~div.doc)
				List<String> href = html.css("#main > div.article-list  #article~div.doc:not(#main > div.article-list>div+h2~div.doc) p:nth-child(2) > a ","href").all();
				logger.info("all()");
				logger.info(all.toString());
				logger.info("hreftoString()");
				logger.info(href.toString());
				if (all.size()>0){ // https://aisel.aisnet.org/thci/all_issues.html
						page.addTargetRequests(all.subList(0,all.size()-7));
				}else if (all.size()<1 && href.size()>0){ // https://aisel.aisnet.org/thci/vol4/iss2/
						page.addTargetRequests(href);
				}else {
						savePhmail(page);
				}
		}
}
