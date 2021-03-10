package com.sean.webcrawler.task;

import java.util.List;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Component
public class JobPAJAIS extends JobAIS {

		@Override
		public void process(Page page) {
				Html html = page.getHtml();
				List<String> all = html.css("#toc h3 > a", "href").all();
				List<String> href = html.css("#main > div.article-list > #article~div.doc > p:nth-child(2) > a", "href").all();

				logger.info("all()");
				logger.info(all.toString());
				logger.info("hreftoString()");
				logger.info(href.toString());
				if (all.size()>0){ // https://aisel.aisnet.org/pajais/all_issues.html
						page.addTargetRequests(all);
				}else if (all.size()<1 && href.size()>0){ // https://aisel.aisnet.org/pajais/vol12/iss3/
						page.addTargetRequests(href);
				}else {
						savePhmail(page);
				}

		}
}
