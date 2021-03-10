package com.sean.webcrawler;

import com.sean.webcrawler.task.AISDataPipeline;
import com.sean.webcrawler.task.JobAIS;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import us.codecraft.webmagic.Spider;

@MapperScan("com.sean.webcrawler.mapper")
@SpringBootApplication
public class SpringbootdemoApplication{



		public static void main(String[] args) {
			ApplicationContext context = SpringApplication.run(SpringbootdemoApplication.class, args);
			AISDataPipeline aisDataPipeline = context.getBean("AISDataPipeline", AISDataPipeline.class);
			JobAIS jobAIS = context.getBean("jobAIS", JobAIS.class);
			String urls[]={
						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Replication%20Research%20)&start=0&context=509156&facet=discipline%3AManagement%20Information%20Systems#",
						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Pacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3APacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems",
						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Human-Computer%20Interaction%20)&start=0&context=509156&facet=",
						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3AJournal%20of%20the%20Association%20for%20Information%20Systems#",
						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Communications%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet="
				};
				for (int i = 0; i < urls.length; i++) {
						urls[i]=urls[i].replace("search/?q","search/results/json?q");
						urls[i]=urls[i].replace("(","%28").replace(")","%29");
				}
				Spider.create(jobAIS)
						.addUrl(urls)
						.addPipeline(aisDataPipeline)
						.thread(5)
						.run();
		}

}
