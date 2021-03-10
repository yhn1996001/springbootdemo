package com.sean.webcrawler.mapper;

import com.sean.webcrawler.pojo.Phemail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PhemailMapper {
		List<Phemail>queryList();
		Phemail queryById(Long id);
		int addPhemail(Phemail phemail);

		// 該方法
		/*
		* You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near '
      (NULL,NULL,' 劉敦仁 ','dliu@mail.nctu.edu.tw');
		* */
		int addPhemailList(List<Phemail> list);

}