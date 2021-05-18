package control;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
			method.delAllID(data,4);
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
		assertEquals(0,method.IsIDempty(data, 2));
		assertEquals(1,method.IsIDempty(data, 3));
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
		assertEquals("��Ϊ�ǵ�:����",result.get(0));
		assertEquals("�Ƿ���:��",result.get(1));
	}

	@Test
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

}
