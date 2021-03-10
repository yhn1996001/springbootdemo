package com.sean.webcrawler.service;

import com.sean.webcrawler.mapper.PhemailMapper;
import com.sean.webcrawler.pojo.Phemail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PhemailServiceimpl implements PhemailService {
		@Autowired
		private PhemailMapper phemailMapper;

		@Override
		@Transactional
		public void save(Phemail phemail) {
				//根據Email phName 查詢資料
				Phemail param=new Phemail();
				param.setEmail(phemail.getEmail());
				param.setPhName(phemail.getPhName());

				List<Phemail> list = this.findAll(param);
		}

		@Override
		public void save(List<Phemail> list) {
				//phemailMapper.addPhemail(phemail);
				for (Phemail phemail : list) {
						phemailMapper.addPhemail(phemail);
				}
		}

		@Override
		public List<Phemail> findAll() {
				final List<Phemail> list = this.phemailMapper.queryList();
				return list;
		}

		@Override
		public List<Phemail> findAll(Phemail phemail) {
				return null;
		}
}
