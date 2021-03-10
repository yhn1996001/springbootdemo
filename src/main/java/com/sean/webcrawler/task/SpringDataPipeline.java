package com.sean.webcrawler.task;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.pojo.Phemail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class SpringDataPipeline implements Pipeline {

		@Autowired
		private PhemailMapper phemailMapper;

		@Override
		public void process(ResultItems resultItems, Task task) {
		// 獲得 phemail
				Phemail phemail=resultItems.get("phemail");
				List<Phemail> list = resultItems.get("list");
				//  phemail 不為空

				if (list!=null) {
						for (Phemail phemail1 : list) {
								phemailMapper.addPhemail(phemail1);
						}
				}
				if (phemail!=null)
						phemailMapper.addPhemail(phemail);
		}
}
