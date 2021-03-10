package com.sean.webcrawler.task;


import com.sean.webcrawler.mapper.JournalMapper;
import com.sean.webcrawler.pojo.Journal;
import com.sean.webcrawler.pojo.JournalOut;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class AISDataPipeline implements Pipeline {

		@Autowired
		private JournalMapper journalMapper;
		protected Logger logger= LoggerFactory.getLogger(getClass());
		@Override
		public void process(ResultItems resultItems, Task task) {
				// 獲得  journal
				JournalOut journal=resultItems.get("journal");

				logger.info("AISDataPipeline++++++++++");
				logger.info("AISDataPipeline "+journal);
				List<Journal> list = resultItems.get("list");
				if (list!=null) {
						for (Journal journal1 : list) {
								journalMapper.addJournal(journal1);
						}
				}
				if (journal!=null)
						journalMapper.addJournal(journal);
		}
}

