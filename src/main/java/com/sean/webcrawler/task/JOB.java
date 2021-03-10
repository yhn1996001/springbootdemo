package com.sean.webcrawler.task;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class JOB  implements PageProcessor {
		/*
方法                    說明                    示例
setCharset(String)	    設定編碼	               site.setCharset(“utf-8”)
setUserAgent(String)    設定UserAgent	         site.setUserAgent(“Spider”)
setTimeOut(int)	        設定超時時間，單位是毫秒 site.setTimeOut(3000)
setRetryTimes(int)	    設定重試次數	          site.setRetryTimes(3)
setCycleRetryTimes(int)	設定迴圈重試次數	      site.setCycleRetryTimes(3)
addCookie(String,String)新增一條cookie	        site.addCookie(“dotcomt_user”,“code4craft”)
setDomain(String)	      設定域名，需設定域名後
												，addCookie才可生效	site.setDomain(“github.com”)
addHeader(String,String)新增一條addHeader	 site.addHeader(“Referer”,“https://github.com”)
setHttpProxy(HttpHost)	設定Http代理	       site.setHttpProxy(new HttpHost(“127.0.0.1”,8080))
		* */
		private Site site=Site.me()
				.setCharset("UTF-8")//設定編碼
				.setRetryTimes(3)//重試次數
				.setCycleRetryTimes(3)//設定迴圈重試次數
				.setTimeOut(30*1000) //超時，時間
				.setSleepTime(3000)
				.setRetrySleepTime(1000)
				;
		private String url="https://www.iim.nctu.edu.tw/faculty/all";

		public abstract void savePhmail(Page page);
		@Override
		public Site getSite() {
				return site;
		}
		public void setSite(Site site) {
				this.site = site;
		}
		public String getUrl() {
				return url;
		}
		public void setUrl(String url) {
				this.url = url;
		}
}
