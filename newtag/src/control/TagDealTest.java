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
	////获取某一标签类的所有标签列表测试
	public void testGetAllTagString() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(0);
		data.add(new Tag("认为涨跌","看涨",a));
		data.add(new Tag("认为涨跌","看跌",a));
		data.add(new Tag("是否广告","是",a));
		data.add(new Tag("是否广告","否",a));
		//获取类别为“是否广告”的标签列表
		ArrayList<String> result=method.getAllTagString(data, "是否广告");
		assertEquals("是",result.get(0));
		assertEquals("否",result.get(1));
	}

	@Test
	//测试删除所有标签列表中指定ID功能
	public void testDelAllID() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(2);a.add(4);
		data.add(new Tag("认为涨跌","看涨",a));
		data.add(new Tag("是否广告","是",a));
		//删除所有标签列表中ID为4的项
		try {
			method.delAllID(data,4);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//检测标签列表中是否还有ID为4的项
		for(int i=0;i<data.get(0).getID().size();i++) {
			assertNotEquals(4,Integer.parseInt(data.get(0).getID().get(i).toString()));
		}
		for(int i=0;i<data.get(0).getID().size();i++) {
			assertNotEquals(4,Integer.parseInt(data.get(1).getID().get(i).toString()));
		}
	}

	@Test
	//该评论是否被标签标注测试
	public void testIsIDempty() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(2);
		data.add(new Tag("认为涨跌","看涨",a));
		data.add(new Tag("是否广告","是",a));
		assertEquals(0,method.IsIDempty(data, 2));
		assertEquals(1,method.IsIDempty(data, 3));
	}

	@Test
	//获取所有标签类与其标签的字符串列表测试
	public void testGetAllTagandClass() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(2);
		data.add(new Tag("认为涨跌","看涨",a));
		data.add(new Tag("是否广告","是",a));
		ArrayList<String> result=method.getAllTagandClass(data);
		assertEquals("认为涨跌:看涨",result.get(0));
		assertEquals("是否广告:是",result.get(1));
	}

	@Test
	public void testGetTagIDs() {
		ArrayList<Tag> data=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(2);a.add(4);
		ArrayList b=new ArrayList();b.add(1);b.add(3);
		data.add(new Tag("认为涨跌","看涨",a));
		data.add(new Tag("是否广告","是",b));
		ArrayList result=method.getTagIDs(data, "是否广告", "是");
		assertEquals(1,Integer.parseInt(result.get(0).toString()));
		assertEquals(3,Integer.parseInt(result.get(1).toString()));
	}

}
