package com.sean.webcrawler;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.task.JobWGTN;
import com.sean.webcrawler.task.SpringDataPipeline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

@SpringBootTest
public class JobWGTNTest {
		@Autowired
		private  JobWGTN jobWGTN;
		@Autowired
		private PhemailMapper phemailMapper;
		@Autowired
		SpringDataPipeline springDataPipeline;

		@Test
		public void test01() {
				Spider.create(jobWGTN)
						.addUrl(jobWGTN.getUrl())
						.addPipeline(springDataPipeline)
						.thread(5)
						.run();
		}

}
