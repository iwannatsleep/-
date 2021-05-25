package control;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Comment;

public class CommentsDatabankTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	// 保存对象到文件功能测试
	public void testSaveComments() {
		File folder = new File("Comment.data");
		if (folder.exists()) {folder.delete();}//如果文件存在，先删除
		assertTrue(!folder.exists());//文件不存在才通过
		CommentsDatabank savecomments = new CommentsDatabank();
		ArrayList<Comment> commentsdatabanksavetest=new ArrayList<Comment>();
		commentsdatabanksavetest.add(new Comment(1,"评论1","SH600519",false,false));
		try {
			savecomments.saveComments(commentsdatabanksavetest);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		File folder2 = new File("Comment.data");
		assertTrue(folder2.exists());//文件存在才通过
		
	}

	@Test
	// 从文件中读取Comment测试
	public void testReadComments() {
		CommentsDatabank comments = new CommentsDatabank();
		ArrayList<Comment> commentsdatabanktest=new ArrayList<Comment>();
		//先往文件里存一个数据
		commentsdatabanktest.add(new Comment(1,"评论1","SH600519",false,false));
		try {
			comments.saveComments(commentsdatabanktest);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//
		try {
			commentsdatabanktest=comments.readComments("Comment.data");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(1,commentsdatabanktest.get(0).getId());
		assertEquals("评论1",commentsdatabanktest.get(0).getText());
		assertEquals("SH600519",commentsdatabanktest.get(0).getSymbol());
		assertEquals(false,commentsdatabanktest.get(0).getTagedornot());
	}

	@Test
	//删除评论功能测试
	public void testDeleteComment() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"评论1","SH600519",false,false));//添加一条ID为1的评论
		for(Comment comment : comments_test_list) {
			assertEquals(1,comment.getId());//判断是否有ID为1的评论
		}
		commentsdatabank.DeleteComment(comments_test_list,1);//删除ID为1的评论
		for(Comment comment : comments_test_list) {
			assertNotEquals(1,comment.getId());//判断是否有ID为1的评论
		}
	}

	@Test
	//标志tagedornot位,传入bool=1置为true,传入其他数值置为false，功能测试
	public void testChangeTagedornot() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"评论1","SH600519",false,false));//添加一条tagedornot位为false的评论
		comments_test_list.add(new Comment(2,"评论2","SH600519",true,false));//添加一条tagedornot位为true的评论
		for(Comment comment : comments_test_list) {
			if(comment.getId()==2) {assertTrue(comment.getTagedornot());}//判断原本是否为true
			if(comment.getId()==1) {assertFalse(comment.getTagedornot());}//判断原本是否为false
		}
		commentsdatabank.ChangeTagedornot(comments_test_list,1,1);//置true
		commentsdatabank.ChangeTagedornot(comments_test_list,2,0);//置false
		for(Comment comment : comments_test_list) {
			if(comment.getId()==2) {assertFalse(comment.getTagedornot());}//判断更改后是否为false
			if(comment.getId()==1) {assertTrue(comment.getTagedornot());}//判断更改后是否为true
		}
	}

	@Test
	//测试返回以列表形式返回未标注的评论功能，未标注评论tagedornot位为false
	public void testUnTagedComments() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"评论1","SH600519",false,false));//添加一条tagedornot位为false的评论
		comments_test_list.add(new Comment(2,"评论2","SH600519",true,false));//添加一条tagedornot位为true的评论
		ArrayList<Comment> test_list=commentsdatabank.unTagedComments(comments_test_list);//未标注的评论列表
		for(Comment comment : test_list) {
			assertFalse(comment.getTagedornot());//判断返回列表的标志位是否为False
		}
	}

	@Test
	//测试返回以列表形式返回未标注的评论功能，已标注评论tagedornot位为true
	public void testTagedComments() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"评论1","SH600519",false,false));//添加一条tagedornot位为false的评论
		comments_test_list.add(new Comment(2,"评论2","SH600519",true,false));//添加一条tagedornot位为true的评论
		ArrayList<Comment> test_list=commentsdatabank.TagedComments(comments_test_list);//已标注的评论列表
		for(Comment comment : test_list) {
			assertTrue(comment.getTagedornot());//判断返回列表的标志位是否为true
		}
	}

	@Test
	//返回指定ID的Comment功能
	public void testGetCommentbyID() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"评论1","SH600519",false,false));//添加一条tagedornot位为false的评论
		comments_test_list.add(new Comment(2,"评论2","SH600519",true,false));//添加一条tagedornot位为true的评论
		Comment test_comment=commentsdatabank.getCommentbyID(comments_test_list,1);
		assertEquals(1,test_comment.getId());
	}

	@Test
	//
	public void testIdChangeList() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list_a=new ArrayList<Comment>();
		comments_test_list_a.add(new Comment(1,"评论1","SH600519",false,false));
		comments_test_list_a.add(new Comment(2,"评论2","SH600519",false,false));
		comments_test_list_a.add(new Comment(3,"评论3","SH600519",false,false));
		comments_test_list_a.add(new Comment(4,"评论4","SH600519",false,false));
		comments_test_list_a.add(new Comment(5,"评论5","SH600519",false,false));
		ArrayList<Comment> comments_test_list_b=new ArrayList<Comment>();
		comments_test_list_b.add(new Comment(1,"评论2","SH600519",false,false));
		comments_test_list_b.add(new Comment(2,"评论3","SH600519",false,false));
		comments_test_list_b.add(new Comment(3,"评论4","SH600519",false,false));
		comments_test_list_b.add(new Comment(4,"评论5","SH600519",false,false));
		comments_test_list_b.add(new Comment(5,"评论6","SH600519",false,false));
		int[][] idchangelist=commentsdatabank.IdChangeList(comments_test_list_a, comments_test_list_b);
		comments_test_list_a.size();
		
	}
}
