package control;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Comment;
import data.Tag;

public class TagDealTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	////��ȡĳһ��ǩ������б�ǩ�б����
	public void testGetAllTagString() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(0);
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("�Ƿ���","��",a));
		data.add(new Tag("�Ƿ���","��",a));
		//��ȡ���Ϊ���Ƿ��桱�ı�ǩ�б�
		ArrayList<String> result=method.getAllTagString(data, "�Ƿ���");
		assertEquals("��",result.get(0));
		assertEquals("��",result.get(1));
	}

	@Test
	//����ɾ�����б�ǩ�б���ָ��ID����
	public void testDelAllID() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(2);a.add(4);
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("�Ƿ���","��",a));
		//ɾ�����б�ǩ�б���IDΪ4����
		try {
			method.delAllID(data,4,"Tag.txt");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//����ǩ�б����Ƿ���IDΪ4����
		for(int i=0;i<data.get(0).getID().size();i++) {
			assertNotEquals(4,Integer.parseInt(data.get(0).getID().get(i).toString()));
		}
		for(int i=0;i<data.get(0).getID().size();i++) {
			assertNotEquals(4,Integer.parseInt(data.get(1).getID().get(i).toString()));
		}
	}

	@Test
	//�������Ƿ񱻱�ǩ��ע����
	public void testIsIDempty() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(2);
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("�Ƿ���","��",a));
		assertEquals(0,method.isIDempty(data, 2));
		assertEquals(1,method.isIDempty(data, 3));
	}

	@Test
	//��ȡ���б�ǩ�������ǩ���ַ����б����
	public void testGetAllTagandClass() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(2);
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("�Ƿ���","��",a));
		ArrayList<String> result=method.getAllTagandClass(data);
		assertEquals("��Ϊ�ǵ� ����",result.get(0));
		assertEquals("�Ƿ��� ��",result.get(1));
	}

	@Test
	//����ָ����ID�����۵�arraylist�б����
	public void testGetTagIDs() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(2);a.add(4);
		ArrayList b=new ArrayList();b.add(1);b.add(3);
		data.add(new Tag("��Ϊ�ǵ�","����",a));
		data.add(new Tag("�Ƿ���","��",b));
		ArrayList result=method.getTagIDs(data, "�Ƿ���", "��");
		assertEquals(1,Integer.parseInt(result.get(0).toString()));
		assertEquals(3,Integer.parseInt(result.get(1).toString()));
	}
	
	@Test
	//��������ǩ�ļ��ı�ǩ���ǩ�ϲ�������tags_a�У�������ID��ArrayList������
	public void testTagClassMerge() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		ArrayList<Tag> data2=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(4);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		ArrayList d=new ArrayList();d.add(0);d.add(4);d.add(5);
		ArrayList e=new ArrayList();e.add(0);e.add(1);e.add(2);e.add(3);
		ArrayList f=new ArrayList();f.add(0);f.add(1);f.add(2);
		data1.add(new Tag("��Ϊ�ǵ�","����",a));
		data1.add(new Tag("��Ϊ�ǵ�","����",b));
		data1.add(new Tag("�Ƿ���","��",c));
		data2.add(new Tag("��Ϊ�ǵ�","����",d));
		data2.add(new Tag("��Ϊ�ǵ�","����",e));
		data2.add(new Tag("�Ƿ�ֹ�","��",f));
		method.tagClassMerge(data1,data2);
		assertEquals("�Ƿ�ֹ�",data1.get(3).getTagClass());
		assertEquals("��",data1.get(3).getTagName());
	}
	
	@Test
	//��������ǩ�ļ��ı�ǩ�ϲ�������tags_a�У�����ID��ArrayList������
	public void testTagsMerge() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		ArrayList<Tag> data2=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//Tags�������
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(4);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		ArrayList d=new ArrayList();d.add(0);d.add(4);d.add(5);
		ArrayList e=new ArrayList();e.add(0);e.add(1);e.add(2);e.add(3);
		ArrayList f=new ArrayList();f.add(0);f.add(1);f.add(2);
		data1.add(new Tag("��Ϊ�ǵ�","����",a));
		data1.add(new Tag("��Ϊ�ǵ�","����",b));
		data1.add(new Tag("�Ƿ���","��",c));
		data2.add(new Tag("��Ϊ�ǵ�","����",d));
		data2.add(new Tag("��Ϊ�ǵ�","����",e));
		data2.add(new Tag("�Ƿ�ֹ�","��",f));
		//Comment�������
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
		int[][] idchangelist=commentsdatabank.idChangeList(comments_test_list_a, comments_test_list_b);
		method.tagClassMerge(data1,data2);
		int i=0;
		while(idchangelist[i][0]!=0) {
			method.tagsMerge(data1,data2,idchangelist[i][0],idchangelist[i][1],"TagMerge.txt");
			i++;
		}
		assertEquals(5,Integer.parseInt(data1.get(0).getID().get(4).toString()));
		assertEquals(6,Integer.parseInt(data1.get(0).getID().get(5).toString()));
	}

	@Test
	//�ж�ͬһ�������Ƿ���ͬһ����ǩ�����ע�˶�������򷵻�true������
	public void testIsConflict() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(2);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		data1.add(new Tag("��Ϊ�ǵ�","����",a));
		data1.add(new Tag("��Ϊ�ǵ�","����",b));
		data1.add(new Tag("�Ƿ���","��",c));
		boolean flag1=method.isConflict(data1,1);
		boolean flag2=method.isConflict(data1,2);
		assertFalse(flag1);
		assertTrue(flag2);
	}
	
	@Test
	//�ж�ͬһ�������Ƿ���ͬһ����ǩ�����ע�˶�������򷵻�true������
	public void testSolveConflict() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//�������
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		data1.add(new Tag("��Ϊ�ǵ�","����",a));
		data1.add(new Tag("��Ϊ�ǵ�","����",b));
		data1.add(new Tag("�Ƿ���","��",c));
		method.solveConflict(data1,"��Ϊ�ǵ�","����",1,"Tag.txt");
		boolean flag2=method.isConflict(data1,2);
		assertFalse(data1.get(0).haveID(1));
	}
	
}
