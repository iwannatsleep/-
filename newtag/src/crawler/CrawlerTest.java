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
		Crawler crawlertest=new Crawler("SH600519",5);//股票代码，评论页数
		crawlertest.craw();
		String filename = "data_comments.csv"; //py爬虫后评论存在这个文件里，java项目从该文件中读取评论
        FileInputStream fis;
        String line=null;
		try {
			fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
	        BufferedReader reader = new BufferedReader(isr);
	        line = reader.readLine();//从第二行评论开始读（第一行是0）
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertNotNull(line);
	}

	@Test
	public void testLoad() {
		Crawler crawlertest=new Crawler("SH600519",5);//股票代码，评论页数
		crawlertest.craw();
		try {
			crawlertest.load();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ArrayList<Comment> comments_list=crawlertest.getCommentslist();
		assertNotNull(comments_list);
	}

}
