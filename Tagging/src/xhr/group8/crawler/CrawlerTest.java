package xhr.group8.crawler;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrawlerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCraw() {
		Crawler crawlertest=new Crawler("SH600519",30);//��Ʊ���룬����ҳ��
		try {
			crawlertest.craw();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		assertNotEquals(0,crawlertest.getCommentslist().size());
	}

}
