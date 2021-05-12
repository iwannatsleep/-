package xhr.group8.databank;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xhr.group8.data.Comment;

public class CommentsDatabankTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveComments() {
		CommentsDatabank savecomments =new CommentsDatabank(); 
		ArrayList<Comment> commentsdatabanksavetest=new ArrayList<Comment>();
		commentsdatabanksavetest.add(new Comment(0,"评论1","SH601919"));
		try {
			savecomments.saveComments(commentsdatabanksavetest);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadComments() {
		CommentsDatabank readcomments =new CommentsDatabank();
		ArrayList<Comment> commentsdatabankreadtest=new ArrayList<Comment>();
		try {
			commentsdatabankreadtest=readcomments.readComments("Comment.data");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(0,commentsdatabankreadtest.get(0).getId());
		assertEquals("评论1",commentsdatabankreadtest.get(0).getText());
		assertEquals("SH601919",commentsdatabankreadtest.get(0).getSymbol());
	}

}
