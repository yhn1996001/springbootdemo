package com.sean.webcrawler.task;

import com.sean.webcrawler.pojo.Phemail;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class JobNTCU implements PageProcessor {
		private Site site=Site.me()
				.setCharset("UTF-8")//設定編碼
				.setRetryTimes(3)//重試次數
				.setCycleRetryTimes(3000)//重試間隔時間
				.setTimeOut(10*1000) //超時，時間
				;//

		private String url="https://www.iim.nctu.edu.tw/faculty/all";

		@Override
		public void process(Page page) {
				List<String> all = page.getHtml().css("table.table").css("h3.title", "text").all();
				if (all.size()!=0)
					this.savePhmail(page);

		}
//
		private void savePhmail(Page page) {
				// 獲得HTML
				final Html html = page.getHtml();
				//
				List<String> all = html.css("table.table").css("h3.title", "text").all();
				List<String> all1 = html.css("table.table").css("div.info_mail > h4:nth-child(2)", "text").all();
				List<Phemail> all2=new ArrayList<>(all.size());
				for (int i = 0; i < all.size(); i++) {
						Phemail phemail=new Phemail();
						phemail.setPhName(all.get(i));
						phemail.setEmail(all1.get(i).trim());
						all2.add(phemail);
				}
				page.putField("list",all2);
		}

		@Override
		public Site getSite() {
				return site;
		}
		public String getUrl() {
				return url;
		}

		public void setUrl(String url) {
				this.url = url;
		}
}
