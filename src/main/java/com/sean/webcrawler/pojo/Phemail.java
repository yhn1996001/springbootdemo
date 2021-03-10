package com.sean.webcrawler.pojo;

public class Phemail {
		private  Long id;
		private String University;
		private String Department;
		private String PhName;
		private String Email;

		public Phemail() {
		}

		public Phemail(Long id, String university, String department, String phName, String email) {
				this.id = id;
				University = university;
				Department = department;
				PhName = phName;
				Email = email;
		}

		@Override
		public String toString() {
				return "Phemail{" +
						"id=" + id +
						", University='" + University + '\'' +
						", Department='" + Department + '\'' +
						", PhName='" + PhName + '\'' +
						", Email='" + Email + '\'' +
						'}';
		}

		public Long getId() {
				return id;
		}

		public void setId(Long id) {
				this.id = id;
		}

		public String getUniversity() {
				return University;
		}

		public void setUniversity(String university) {
				University = university;
		}

		public String getDepartment() {
				return Department;
		}

		public void setDepartment(String department) {
				Department = department;
		}

		public String getPhName() {
				return PhName;
		}

		public void setPhName(String phName) {
				PhName = phName;
		}

		public String getEmail() {
				return Email;
		}

		public void setEmail(String email) {
				Email = email;
		}
}
