package com.sean.webcrawler.service;

import com.sean.webcrawler.pojo.Journal;
import java.util.List;


public interface JournalService {
		//儲存資料
		void save(Journal journal);

		//儲存資料
		void save(List<Journal> list);

		// 查詢全部
		List<Journal> findAll();
		// 查詢部分
		List<Journal> findAll(Journal phemail);
}
