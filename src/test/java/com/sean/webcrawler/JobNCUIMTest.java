package com.sean.webcrawler;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.task.JobNCUIM;
import com.sean.webcrawler.task.SpringDataPipeline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

@SpringBootTest
public class JobNCUIMTest {
		@Autowired
		private PhemailMapper phemailMapper;
		@Autowired
		SpringDataPipeline springDataPipeline;
		@Autowired
		private JobNCUIM jobNCUIM;

		@Test
		public void test01() {
				jobNCUIM.setUrl("https://im.mgt.ncu.edu.tw/teacher");
				Spider.create(jobNCUIM)
						.addUrl(jobNCUIM.getUrl())
						.addPipeline(springDataPipeline)
						.thread(5)
						.run();
		}

}
