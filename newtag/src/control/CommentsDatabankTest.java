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
	// ��������ļ����ܲ���
	public void testSaveComments() {
		File folder = new File("Comment.data");
		if (folder.exists()) {folder.delete();}//����ļ����ڣ���ɾ��
		assertTrue(!folder.exists());//�ļ������ڲ�ͨ��
		CommentsDatabank savecomments = new CommentsDatabank();
		ArrayList<Comment> commentsdatabanksavetest=new ArrayList<Comment>();
		commentsdatabanksavetest.add(new Comment(1,"����1","SH600519",false,false));
		try {
			savecomments.saveComments(commentsdatabanksavetest);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		File folder2 = new File("Comment.data");
		assertTrue(folder2.exists());//�ļ����ڲ�ͨ��
		
	}

	@Test
	// ���ļ��ж�ȡComment����
	public void testReadComments() {
		CommentsDatabank comments = new CommentsDatabank();
		ArrayList<Comment> commentsdatabanktest=new ArrayList<Comment>();
		//�����ļ����һ������
		commentsdatabanktest.add(new Comment(1,"����1","SH600519",false,false));
		try {
			comments.saveComments(commentsdatabanktest);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//
		try {
			commentsdatabanktest=comments.readComments("Comment.data");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		assertEquals(1,commentsdatabanktest.get(0).getId());
		assertEquals("����1",commentsdatabanktest.get(0).getText());
		assertEquals("SH600519",commentsdatabanktest.get(0).getSymbol());
		assertEquals(false,commentsdatabanktest.get(0).getTagedornot());
	}

	@Test
	//ɾ�����۹��ܲ���
	public void testDeleteComment() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"����1","SH600519",false,false));//���һ��IDΪ1������
		for(Comment comment : comments_test_list) {
			assertEquals(1,comment.getId());//�ж��Ƿ���IDΪ1������
		}
		commentsdatabank.DeleteComment(comments_test_list,1);//ɾ��IDΪ1������
		for(Comment comment : comments_test_list) {
			assertNotEquals(1,comment.getId());//�ж��Ƿ���IDΪ1������
		}
	}

	@Test
	//��־tagedornotλ,����bool=1��Ϊtrue,����������ֵ��Ϊfalse�����ܲ���
	public void testChangeTagedornot() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"����1","SH600519",false,false));//���һ��tagedornotλΪfalse������
		comments_test_list.add(new Comment(2,"����2","SH600519",true,false));//���һ��tagedornotλΪtrue������
		for(Comment comment : comments_test_list) {
			if(comment.getId()==2) {assertTrue(comment.getTagedornot());}//�ж�ԭ���Ƿ�Ϊtrue
			if(comment.getId()==1) {assertFalse(comment.getTagedornot());}//�ж�ԭ���Ƿ�Ϊfalse
		}
		commentsdatabank.ChangeTagedornot(comments_test_list,1,1);//��true
		commentsdatabank.ChangeTagedornot(comments_test_list,2,0);//��false
		for(Comment comment : comments_test_list) {
			if(comment.getId()==2) {assertFalse(comment.getTagedornot());}//�жϸ��ĺ��Ƿ�Ϊfalse
			if(comment.getId()==1) {assertTrue(comment.getTagedornot());}//�жϸ��ĺ��Ƿ�Ϊtrue
		}
	}

	@Test
	//���Է������б���ʽ����δ��ע�����۹��ܣ�δ��ע����tagedornotλΪfalse
	public void testUnTagedComments() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"����1","SH600519",false,false));//���һ��tagedornotλΪfalse������
		comments_test_list.add(new Comment(2,"����2","SH600519",true,false));//���һ��tagedornotλΪtrue������
		ArrayList<Comment> test_list=commentsdatabank.unTagedComments(comments_test_list);//δ��ע�������б�
		for(Comment comment : test_list) {
			assertFalse(comment.getTagedornot());//�жϷ����б�ı�־λ�Ƿ�ΪFalse
		}
	}

	@Test
	//���Է������б���ʽ����δ��ע�����۹��ܣ��ѱ�ע����tagedornotλΪtrue
	public void testTagedComments() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"����1","SH600519",false,false));//���һ��tagedornotλΪfalse������
		comments_test_list.add(new Comment(2,"����2","SH600519",true,false));//���һ��tagedornotλΪtrue������
		ArrayList<Comment> test_list=commentsdatabank.TagedComments(comments_test_list);//�ѱ�ע�������б�
		for(Comment comment : test_list) {
			assertTrue(comment.getTagedornot());//�жϷ����б�ı�־λ�Ƿ�Ϊtrue
		}
	}

	@Test
	//����ָ��ID��Comment����
	public void testGetCommentbyID() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list=new ArrayList<Comment>();
		comments_test_list.add(new Comment(1,"����1","SH600519",false,false));//���һ��tagedornotλΪfalse������
		comments_test_list.add(new Comment(2,"����2","SH600519",true,false));//���һ��tagedornotλΪtrue������
		Comment test_comment=commentsdatabank.getCommentbyID(comments_test_list,1);
		assertEquals(1,test_comment.getId());
	}

	@Test
	//
	public void testIdChangeList() {
		CommentsDatabank commentsdatabank = new CommentsDatabank();
		ArrayList<Comment> comments_test_list_a=new ArrayList<Comment>();
		comments_test_list_a.add(new Comment(1,"����1","SH600519",false,false));
		comments_test_list_a.add(new Comment(2,"����2","SH600519",false,false));
		comments_test_list_a.add(new Comment(3,"����3","SH600519",false,false));
		comments_test_list_a.add(new Comment(4,"����4","SH600519",false,false));
		comments_test_list_a.add(new Comment(5,"����5","SH600519",false,false));
		ArrayList<Comment> comments_test_list_b=new ArrayList<Comment>();
		comments_test_list_b.add(new Comment(1,"����2","SH600519",false,false));
		comments_test_list_b.add(new Comment(2,"����3","SH600519",false,false));
		comments_test_list_b.add(new Comment(3,"����4","SH600519",false,false));
		comments_test_list_b.add(new Comment(4,"����5","SH600519",false,false));
		comments_test_list_b.add(new Comment(5,"����6","SH600519",false,false));
		int[][] idchangelist=commentsdatabank.IdChangeList(comments_test_list_a, comments_test_list_b);
		comments_test_list_a.size();
		
	}
}
