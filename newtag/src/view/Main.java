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
		String log="��������";
		try {
			logwrite.writeLog(log);
		} catch (IOException e3) {
			// TODO �Զ����ɵ� catch ��
			e3.printStackTrace();
		}
		//��ʼ�������ļ�
		comments_list.add(new Comment(0,"�����ػ�������","SH000000",false,false));
		ArrayList a=new ArrayList();a.add(0);
		tags.add(new Tag("��Ϊ�ǵ�","����",a));
		tags.add(new Tag("��Ϊ�ǵ�","����",a));
		tags.add(new Tag("��Ϊ�ǵ�","����",a));
		tags.add(new Tag("�Ƿ���","��",a));
		tags.add(new Tag("�Ƿ���","��",a));
		
		commentsDatabank.saveComments(comments_list, "Comment.data");
		commentsDatabank.saveComments(comments_list, "CommentMerge.data");
		tagdeal.saveTags(tags, "Tag.txt");
		tagdeal.saveTags(tags, "TagMerge.txt");
		
		new MainView();
	}
}
