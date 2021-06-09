package crawler;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Comment;

public class CrawlerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCraw() {
		Crawler crawlertest=new Crawler("SH600519",5);//��Ʊ���룬����ҳ��
		crawlertest.craw();
		String filename = "data_comments.csv"; //py��������۴�������ļ��java��Ŀ�Ӹ��ļ��ж�ȡ����
        FileInputStream fis;
        String line=null;
		try {
			fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
	        BufferedReader reader = new BufferedReader(isr);
	        line = reader.readLine();//�ӵڶ������ۿ�ʼ������һ����0��
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		assertNotNull(line);
	}

	@Test
	public void testLoad() {
		Crawler crawlertest=new Crawler("SH600519",5);//��Ʊ���룬����ҳ��
		crawlertest.craw();
		try {
			crawlertest.load();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ArrayList<Comment> comments_list=crawlertest.getCommentslist();
		assertNotNull(comments_list);
	}

}
