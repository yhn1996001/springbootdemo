package com.sean.webcrawler.mapper;

import com.sean.webcrawler.pojo.Journal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JournalMapper {
		List<Journal> queryList();
		List<Journal> querypajais();
		List<Journal> queryTHCI();
		List<Journal> queryCAIS();
		List<Journal> queryTRR();

		List<Journal> queryListBywhere();
		List<Journal> queryListBywhere1();
		List<Journal> queryListByjournalName(String journalName);
		Journal queryById(Long id);
		int addJournal(Journal journal);
		int addTHCI(Journal journal);
}
