package com.sean.webcrawler.task;

import com.sean.webcrawler.pojo.Phemail;
import java.util.List;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

@Component
public class JobNTUIM extends JOB {
// 台大沒有爬完整 ，  台大網頁規則較不易  老師各自有網頁
		@Override
		public void savePhmail(Page page) {
				String name="";
				String email="";
				try {
						name = page.getHtml().css("body  div.right_b1 > div.new_tea02", "text").toString();
						email = page.getHtml().css("body > div.all_box.mt20.mb60  div.right_b > div > div > div > div.con > div.all_box > div.right_b1 > div:nth-child(8) > a", "text").toString();

						name=name.trim().replace("\u00a0","");
						email=email.trim().replace("\u00a0","");

				}catch (NullPointerException e){

				}
				Phemail phemail=new Phemail();
				phemail.setUniversity("國立臺灣大學");
				phemail.setDepartment("資訊管理學系暨研究所");
				phemail.setPhName(name);
				phemail.setEmail(email);
				page.putField("phemail",phemail);
				System.out.println(name+email);
		}

		@Override
		public void process(Page page) {
//body > div.all_box.mt20.mb60 > div > div > div.right_b > ul > li > div.sub span.mr15
				List<String> text = page.getHtml().css("body > div.all_box.mt20.mb60 > div > div > div.right_b > ul > li > div.sub span.mr15>a", "href").all();

				// 獲得 個老師 URL
				if (text.size()>0){
						for (int i = 0; i < text.size(); i++) {
								text.set(i,"https://management.ntu.edu.tw"+text.get(i));
						}
						page.addTargetRequests(text);
				}else {
						savePhmail(page);
				}
				System.out.println(text);
				System.out.println(getUrl());
		}
}
