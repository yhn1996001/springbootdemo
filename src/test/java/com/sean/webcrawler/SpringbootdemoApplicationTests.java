package com.sean.webcrawler;

import com.sean.webcrawler.mapper.JournalMapper;
import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.pojo.Journal;
import com.sean.webcrawler.task.JobNTCU;
import com.sean.webcrawler.task.SpringDataPipeline;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

@SpringBootTest
class SpringbootdemoApplicationTests {

		@Autowired
		private JournalMapper journalMapper;

		@Autowired
		private PhemailMapper phemailMapper;
		@Autowired
		JobNTCU jobNTCU ;
		@Autowired
		SpringDataPipeline springDataPipeline;


		@Test
		void contextLoads() {
//				Phemail phemail = new Phemail();
//				phemail.setUniversity("交通");
//				phemail.setDepartment("資管");
//				phemail.setEmail("mjtsai@cc.nctu.edu.tw");
//				phemail.setPhName("蔡銘箴");
//				List<Phemail> list = new ArrayList<>();
//				list.add(phemail);
//				final int i = phemailMapper.addPhemailList(list);
//				System.out.println(i);
//				final int addPhemail = phemailMapper.addPhemail(phemail);

		}
		@Test
		public void test01() {
			Spider.create(jobNTCU)
					.addUrl(jobNTCU.getUrl())
					.addPipeline(springDataPipeline)
					.thread(5)
					.run();
		}


		// journalMapper   11-25 0135 -> OK
		@Test
		public void testjournalMapper() {
				Journal journal=new Journal();
				journal.setAuther("dsaa");
				journal.setIssn("dsaa");
				journalMapper.addJournal(journal);
		}


}
