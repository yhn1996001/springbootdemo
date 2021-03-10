package com.sean.webcrawler;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.task.JobNTUIM;
import com.sean.webcrawler.task.SpringDataPipeline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;
@SpringBootTest
public class JobNTUIMTest {
		@Autowired
		private PhemailMapper phemailMapper;
		@Autowired
		SpringDataPipeline springDataPipeline;
		@Autowired
		JobNTUIM job;
		@Test
		public void test01() {
				job.setUrl("https://management.ntu.edu.tw/IM/faculty");
				Spider.create(job)
						.addUrl(job.getUrl())
//						.addPipeline(springDataPipeline)
						.thread(5)
						.run();
		}

}
