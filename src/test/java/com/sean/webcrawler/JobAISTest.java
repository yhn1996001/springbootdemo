package com.sean.webcrawler;

import com.sean.webcrawler.mapper.JournalMapper;
import com.sean.webcrawler.pojo.Journal;
import com.sean.webcrawler.task.AISDataPipeline;
import com.sean.webcrawler.task.JobAIS;
import com.sean.webcrawler.task.JobCAIS;
import com.sean.webcrawler.task.JobPAJAIS;
import com.sean.webcrawler.task.JobTHCI;
import com.sean.webcrawler.task.PdfFilePipeline;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

@SpringBootTest
public class JobAISTest {

    @Autowired
    private JournalMapper journalMapper;
    @Autowired
    private AISDataPipeline aisDataPipeline;
    @Autowired
    private JobAIS jobAIS;
    @Autowired
    private JobPAJAIS jobPAJAIS;
    @Autowired
    private JobTHCI JobTHCI;

    @Autowired
    private JobCAIS jobCAIS;

    @Autowired
    private SqlSession sqlSession;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test01journal() {
        //https://aisel.aisnet.org/trr/vol5/iss1/10/
        //		String url="https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Replication%20Research%20)&start=0&context=509156&facet=discipline%3AManagement%20Information%20Systems";
//
//		String url="https://aisel.aisnet.org/do/search/results/json?q=publication_title%3A(%20AIS%20Transactions%20on%20Replication%20Research%20)&query=Search&start=0&context=509156&facet=discipline%3AManagement%20Information%20Systems&facet=&facet=&facet=&facet=&facet=";
//String url="https://aisel.aisnet.org/do/search/results/json?q=publication_title%3A(%20AIS%20Transactions%20on%20Human-Computer%20Interaction%20)&start=0&context=509156&facet=";
        String urls[] = {
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Communications%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=",
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Replication%20Research%20)&start=0&context=509156&facet=discipline%3AManagement%20Information%20Systems#",
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Pacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3APacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems",
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Human-Computer%20Interaction%20)&start=0&context=509156&facet=",
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3AJournal%20of%20the%20Association%20for%20Information%20Systems#",
        };
        for (int i = 0; i < urls.length; i++) {
            urls[i] = urls[i].replace("search/?q", "search/results/json?q");
            urls[i] = urls[i].replace("(", "%28").replace(")", "%29");
        }
//				String url="https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Pacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3APacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems";
//				url=url.replace("search/?q","search/results/json?q");
//				url=url.replace("(","%28").replace(")","%29");

        Spider.create(jobAIS)
                .addUrl(urls)
//						.addPipeline(aisDataPipeline)
                .addPipeline(new FilePipeline("C:\\Users\\USER\\Desktop\\aistrrall\\A1128"))
                .thread(5)
                .run();
    }

    @Test
    public void test02() throws Exception {
//AIS Transactions on Human-Computer Interaction   AIS Transactions on Replication Research  Pacific Asia Journal of the Association for Information Systems
//"C:\\Users\\USER\\Desktop\\aistrrall\\AISTHCIv1.bib"
        String journalName = "AIS Transactions on Human-Computer Interaction", fileName = "C:\\Users\\USER\\Desktop\\aistrrall\\THCI_ALL.bib";
        test02_tmp(journalName, fileName);
    }

    public void test02_tmp(String journalName, String fileName) throws Exception {
        List<Journal> list = journalMapper.queryListByjournalName(journalName);
//				List<Journal> list = journalMapper.queryList();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (Journal journal : list) {
            bw.write(journal.toString() + "\r\n");
        }
        bw.close();
        fos.close();
    }


    @Test //pajais 匯出檔案(.bib)，新增queryListBywhere方法
    public void test3() throws Exception {
        String fileName = "C:\\Users\\USER\\Desktop\\aistrrall\\pajaisv1.bib";
        List<Journal> list = journalMapper.queryListBywhere();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (Journal journal : list) {
            bw.write(journal.toString() + "\r\n");
        }
        bw.close();
        fos.close();
    }

    @Test //AIS Transactions on Human-Computer Interaction 匯出檔案(.bib)，新增queryListBywhere1方法
    public void test4() throws Exception {
        String fileName = "C:\\Users\\USER\\Desktop\\aistrrall\\journal_trr_1224.bib";
        List<Journal> list = journalMapper.queryTRR();
        FileOutputStream fos = new FileOutputStream(fileName);


        fos.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (Journal journal : list) {
            bw.write(journal.toString() + "\r\n");
        }
        bw.close();
        fos.close();
    }

    @Test// 下載PDF
    public void test05() {
        String urls[] = {
                "https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Replication%20Research%20)&start=0&context=509156&facet=discipline%3AManagement%20Information%20Systems#",
//						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Pacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3APacific%20Asia%20Journal%20of%20the%20Association%20for%20Information%20Systems",
//						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20AIS%20Transactions%20on%20Human-Computer%20Interaction%20)&start=0&context=509156&facet=",
//						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Journal%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet=publication_facet%3AJournal%20of%20the%20Association%20for%20Information%20Systems#",
//						"https://aisel.aisnet.org/do/search/?q=publication_title%3A(%20Communications%20of%20the%20Association%20for%20Information%20Systems%20)&start=0&context=509156&facet="


        };
        for (int i = 0; i < urls.length; i++) {
            urls[i] = urls[i].replace("search/?q", "search/results/json?q");
            urls[i] = urls[i].replace("(", "%28").replace(")", "%29");
        }
        Spider.create(jobAIS)
                .addUrl(urls)
//						.addPipeline(aisDataPipeline)
                .addPipeline(new PdfFilePipeline("C:\\Users\\USER\\Desktop\\aistrrall\\A1128"))
                .thread(5)
                .run();
    }

    @Test // Log
    public void Logtest() {


        logger.info("logger---info-----------------");
        logger.debug("logger---debug-----------------");

    }

    @Test
    public void testsql() {

        JournalMapper mapper = sqlSession.getMapper(JournalMapper.class);
//				mapper.addJournal()
        logger.info(mapper.queryList().toString());

    }

    @Test
    public void testPajais() {//
        String urls[] = {
                //			"https://aisel.aisnet.org/pajais/all_issues.html",
                //			"https://aisel.aisnet.org/pajais/vol9/iss1/"
//						"https://aisel.aisnet.org/thci/all_issues.html",
                "https://aisel.aisnet.org/thci/vol4/iss2/"
        };
        Spider.create(jobPAJAIS)
                .addUrl(urls)
//						.addPipeline(aisDataPipeline)
// 					.addPipeline(new PdfFilePipeline("C:\\Users\\USER\\Desktop\\aistrrall\\A1128"))
                .thread(5)
                .run();
    }

    @Test
    public void testTHCI() {
        String urls[] = {
                "https://aisel.aisnet.org/thci/all_issues.html",
//						"https://aisel.aisnet.org/thci/vol4/iss2/",
//						"https://aisel.aisnet.org/thci/vol12/iss2/",
//						"https://aisel.aisnet.org/thci/vol11/iss4/"
        };
        Spider.create(JobTHCI)
                .addUrl(urls)
                .addPipeline(aisDataPipeline)
// 					.addPipeline(new PdfFilePipeline("C:\\Users\\USER\\Desktop\\aistrrall\\A1128"))
                .thread(5)
                .run();
    }

    @Test
    public void testCAIS() {
        String urls[] = {
                "https://aisel.aisnet.org/cais/all_issues.html",
//						"https://aisel.aisnet.org/thci/vol4/iss2/",
//						"https://aisel.aisnet.org/thci/vol12/iss2/",
//						"https://aisel.aisnet.org/thci/vol11/iss4/"
        };
        Spider.create(jobCAIS)
                .addUrl(urls)
                .addPipeline(aisDataPipeline)
// 					.addPipeline(new PdfFilePipeline("C:\\Users\\USER\\Desktop\\aistrrall\\A1128"))
                .thread(10)
                .run();
    }

    @Test
    public void test() throws SQLException {
        String user = "root",
                password = "root",
                url = "";

        DriverManager.getConnection(url, user, password);

    }


}
