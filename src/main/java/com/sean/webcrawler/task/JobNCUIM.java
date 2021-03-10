package com.sean.webcrawler.task;

import com.sean.webcrawler.pojo.Phemail;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

// 用 JOB 將 PageProcessor 先實作
@Component
public class JobNCUIM extends JOB {
		@Override
		public void process(Page page) {
				List<String> text = page.getHtml().css("#blcok_line  tbody  div.an", "text").all();
				if (text.size()>0)
					this.savePhmail(page);
		}

		@Override
		public void savePhmail(Page page) {
				 List<Selectable> nodes = page.getHtml().css("#blcok_line .table.table-striped.table-dark.teacher-table").nodes();
//						final List<String> list = node.css("tbody  div.an", "text").all();
//						final List<String> list1 = node.css("tbody td.ba_comment a", "text").all();

				 List<Phemail> list = nodes.stream().map(x -> {
						String[] name = x.css("tbody  div.an", "text")
								.toString()
								.replace("\u00a0", " ")
								.trim()
								.split(" ");
						String email = x.css("tbody td.ba_comment a", "text").toString();
						Phemail phemail = new Phemail();
						 phemail.setEmail(email);
						 phemail.setPhName(name[0]);
						 phemail.setUniversity("國立中央大學");
						 phemail.setDepartment("資訊管理學系");
						return phemail ;
				}).collect(Collectors.toList());
				page.putField("list",list);
		}
}
