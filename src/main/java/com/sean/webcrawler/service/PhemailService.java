package com.sean.webcrawler.service;

import com.sean.webcrawler.pojo.Phemail;
import java.util.List;


public interface PhemailService  {

		//儲存資料
		void save(Phemail phemail);

		//儲存資料
		void save(List<Phemail> list);

		// 查詢全部
		List<Phemail> findAll();
		// 查詢部分
		List<Phemail> findAll(Phemail phemail);

}
