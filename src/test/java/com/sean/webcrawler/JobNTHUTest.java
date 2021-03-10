package com.sean.webcrawler;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.task.JobNTHU;
import com.sean.webcrawler.task.SpringDataPipeline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

@SpringBootTest
public class JobNTHUTest {
		@Autowired
		private PhemailMapper phemailMapper;
		@Autowired
		SpringDataPipeline springDataPipeline;
		@Autowired
		JobNTHU job;
		@Test
		public void test01() {

				job.setUrl("http://isa.site.nthu.edu.tw/p/412-1182-82.php?Lang=zh-tw");
				Spider.create(job)
						.addUrl(job.getUrl())
//						.addPipeline(springDataPipeline)
						.thread(5)
						.run();
		}
}
