package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import control.CommentsDatabank;
import control.TagDeal;
import data.Comment;
import data.Logs;
import data.Tag;

public class Main{
	public static void main(String[] args) throws IOException {
		Logs logwrite=new Logs();
	    ArrayList<Comment> comments_list=new ArrayList<Comment>();
		ArrayList<Tag> tags=new ArrayList<Tag>();
	    CommentsDatabank commentsDatabank=new CommentsDatabank();
	    TagDeal tagdeal=new TagDeal();
		String log="程序启动";
		try {
			logwrite.writeLog(log);
		} catch (IOException e3) {
			// TODO 自动生成的 catch 块
			e3.printStackTrace();
		}
		//初始化数据文件
		comments_list.add(new Comment(0,"请下载或导入数据","SH000000",false,false));
		ArrayList a=new ArrayList();a.add(0);
		tags.add(new Tag("认为涨跌","看涨",a));
		tags.add(new Tag("认为涨跌","看跌",a));
		tags.add(new Tag("认为涨跌","中立",a));
		tags.add(new Tag("是否广告","是",a));
		tags.add(new Tag("是否广告","否",a));
		
		commentsDatabank.saveComments(comments_list, "Comment.data");
		commentsDatabank.saveComments(comments_list, "CommentMerge.data");
		tagdeal.saveTags(tags, "Tag.txt");
		tagdeal.saveTags(tags, "TagMerge.txt");
		
		new MainView();
	}
}
