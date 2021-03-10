package com.sean.webcrawler.pojo;

public class JournalOut extends Journal {

		private String Abstract ;
		private String url;


		private String download_link;

		public String getUrl() {
				return url;
		}

		public void setUrl(String url) {
				this.url = url;
		}

		public String getDownload_link() {
				return download_link;
		}

		public void setDownload_link(String download_link) {
				this.download_link = download_link;
		}

		public String getAbstract() {
				return Abstract;
		}

		public void setAbstract(String anAbstract) {
				Abstract = anAbstract;
		}
		/*
		@article{RN409,
		author = {Adams, Troy L and Li, Yuanxia and Liu, Hao },
		title = {A Replication of Beyond the Turk: Alternative Platforms for Crowdsourcing Behavioral Research–Sometimes Preferable to Student Groups},
		journal = {AIS Transactions on Replication Research
		},
		volume = {6},
		number = {1},
		pages = {15},
		ISSN = {2473-3458},
		year = {2020},
		type = {Journal Article}
		keywords = {Online research, Crowdsourcing, Data quality, Amazon Mechanical Turk Prolific Academic, Replication}
		}
				*/
		@Override
		public String toString() {
				String s = super.getAuther().replace(";", " and ");
				int i = s.lastIndexOf("and");
				if (i>0)
					s=s.substring(0,i);
				return String.format("@article{RN%s,\r\nauthor = {%s },\r\n" +
						"title = {%s},\r\n" +
						"journal = {%s},\r\n" +
						"volume = {%s},\r\n" +
						"pages = {%s},\r\n" +
						"ISSN = {%s},\r\n" +
						"year = {%s},\r\n" +
						"type = {%s}\r\n" +
						"keywords = {%s}\r\n" +
						"Abstract = {%s}\r\n" +
								"}",
						super.getId(),
						s,
						super.getTitle(),
						super.getJournalName(),
						super.getVolume(),
						super.getFirstpag(),
						super.getIssn(),
						super.getYear(),
						super.getType(),
						super.getKeyword(),
						this.Abstract
				);
		}
}
