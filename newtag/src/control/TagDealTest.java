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
			method.delAllID(data,4,"Tag.txt");
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
		assertEquals(0,method.isIDempty(data, 2));
		assertEquals(1,method.isIDempty(data, 3));
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
		assertEquals("认为涨跌 看涨",result.get(0));
		assertEquals("是否广告 是",result.get(1));
	}

	@Test
	//返回指定的ID的评论的arraylist列表测试
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
	
	@Test
	//将两个标签文件的标签类标签合并，存在tags_a中（不包括ID的ArrayList）测试
	public void testTagClassMerge() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		ArrayList<Tag> data2=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(4);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		ArrayList d=new ArrayList();d.add(0);d.add(4);d.add(5);
		ArrayList e=new ArrayList();e.add(0);e.add(1);e.add(2);e.add(3);
		ArrayList f=new ArrayList();f.add(0);f.add(1);f.add(2);
		data1.add(new Tag("认为涨跌","看涨",a));
		data1.add(new Tag("认为涨跌","看跌",b));
		data1.add(new Tag("是否广告","是",c));
		data2.add(new Tag("认为涨跌","看涨",d));
		data2.add(new Tag("认为涨跌","看跌",e));
		data2.add(new Tag("是否持股","是",f));
		method.tagClassMerge(data1,data2);
		assertEquals("是否持股",data1.get(3).getTagClass());
		assertEquals("是",data1.get(3).getTagName());
	}
	
	@Test
	//将两个标签文件的标签合并，存在tags_a中（包括ID的ArrayList）测试
	public void testTagsMerge() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		ArrayList<Tag> data2=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//Tags添加数据
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(4);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		ArrayList d=new ArrayList();d.add(0);d.add(4);d.add(5);
		ArrayList e=new ArrayList();e.add(0);e.add(1);e.add(2);e.add(3);
		ArrayList f=new ArrayList();f.add(0);f.add(1);f.add(2);
		data1.add(new Tag("认为涨跌","看涨",a));
		data1.add(new Tag("认为涨跌","看跌",b));
		data1.add(new Tag("是否广告","是",c));
		data2.add(new Tag("认为涨跌","看涨",d));
		data2.add(new Tag("认为涨跌","看跌",e));
		data2.add(new Tag("是否持股","是",f));
		//Comment添加数据
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
	//判断同一个评论是否在同一个标签类里标注了多个，是则返回true，测试
	public void testIsConflict() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(0);b.add(2);b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		data1.add(new Tag("认为涨跌","看涨",a));
		data1.add(new Tag("认为涨跌","看跌",b));
		data1.add(new Tag("是否广告","是",c));
		boolean flag1=method.isConflict(data1,1);
		boolean flag2=method.isConflict(data1,2);
		assertFalse(flag1);
		assertTrue(flag2);
	}
	
	@Test
	//判断同一个评论是否在同一个标签类里标注了多个，是则返回true，测试
	public void testSolveConflict() {
		ArrayList<Tag> data1=new ArrayList<Tag>();
		TagDeal method=new TagDeal();
		//添加数据
		ArrayList a=new ArrayList();a.add(0);a.add(1);a.add(2);a.add(3);
		ArrayList b=new ArrayList();b.add(5);
		ArrayList c=new ArrayList();c.add(0);c.add(1);c.add(2);
		data1.add(new Tag("认为涨跌","看涨",a));
		data1.add(new Tag("认为涨跌","看跌",b));
		data1.add(new Tag("是否广告","是",c));
		method.solveConflict(data1,"认为涨跌","看跌",1,"Tag.txt");
		boolean flag2=method.isConflict(data1,2);
		assertFalse(data1.get(0).haveID(1));
	}
	
}
