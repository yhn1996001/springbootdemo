package com.sean.webcrawler.pojo;

public class Journal {
		private String id;
		private String auther;
		private String title;
		private String journalName;
		private String year;
		private String volume;
		private String keyword;
		private String type;
		private String firstpag;
		private String issn;

		public Journal() {
		}


		public Journal(String auther, String title, String journalName, String year, String volume, String keyword, String type, String firstpag, String issn) {
				this.auther = auther;
				this.title = title;
				this.journalName = journalName;
				this.year = year;
				this.volume = volume;
				this.keyword = keyword;
				this.type = type;
				this.firstpag = firstpag;
				this.issn = issn;
		}
		public String getId() {
				return id;
		}

		public String getAuther() {
				return auther;
		}

		public void setAuther(String auther) {
				this.auther = auther;
		}

		public String getTitle() {
				return title;
		}

		public void setTitle(String title) {
				this.title = title;
		}

		public String getJournalName() {
				return journalName;
		}

		public void setJournalName(String journalName) {
				this.journalName = journalName;
		}

		public String getYear() {
				return year;
		}

		public void setYear(String year) {
				this.year = year;
		}

		public String getVolume() {
				return volume;
		}

		public void setVolume(String volume) {
				this.volume = volume;
		}

		public String getKeyword() {
				return keyword;
		}

		public void setKeyword(String keyword) {
				this.keyword = keyword;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}

		public String getFirstpag() {
				return firstpag;
		}

		public void setFirstpag(String firstpag) {
				this.firstpag = firstpag;
		}

		public String getIssn() {
				return issn;
		}

		public void setIssn(String issn) {
				this.issn = issn;
		}

		@Override
		public String toString() {
				return "Journal{" +
						"auther='" + auther + '\'' +
						", title='" + title + '\'' +
						", journalName='" + journalName + '\'' +
						", year='" + year + '\'' +
						", volume='" + volume + '\'' +
						", keyword='" + keyword + '\'' +
						", type='" + type + '\'' +
						", firstpag='" + firstpag + '\'' +
						'}';
		}

}
