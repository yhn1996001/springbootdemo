package com.sean.webcrawler.task;

import java.util.List;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

@Component
public class JobNTHU  extends JOB{
		@Override
		public void process(Page page) {
				List<String> text = page.getHtml().all();// div.mdetail ul:last-child > span:last-child
				System.out.println(text);
		}
		@Override
		public void savePhmail(Page page) {

		}
}
