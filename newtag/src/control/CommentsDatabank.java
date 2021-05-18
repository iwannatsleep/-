package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.Comment;

public class CommentsDatabank {
    // �ļ���������ָ������������ı��༭��������ļ���ע�⣬���±��޷������У�   
    static String filename = "Comment.data";   
    ArrayList<Comment> comments_list;
    
    public ArrayList<Comment> getCommentslist() {
        return comments_list;
    }
    public void setCommentslist(ArrayList<Comment> comments_list) {this.comments_list=comments_list;}
    
    // ��������ļ��������ʽΪ��   
    // 1��ÿ��һ��   
    // 2��ÿ�����δ��id text symbol��������ֵ���� tab ����  
    public void saveComments(ArrayList<Comment> comments) throws IOException {   
    
    	File file = new File(filename);
    	if (!file.exists()) {
    	    try {
    	        file.createNewFile();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
	    // �����ļ�����   
	    String data = "";   
	    for (Comment comment : comments) {   
	    data += getCommentString(comment) + "\n";   
	    }   
	    
	    // �����ļ�����   
	    FileWriter writer = new FileWriter(filename);   
	    writer.write(data);   
	    writer.close();   
	    //System.out.println("���󱣴���ϡ�");
	    
    }   
    
    // ���ļ��ж�ȡ����
    public ArrayList<Comment> readComments(String filename1) throws IOException {   
        ArrayList<Comment> result = new ArrayList<Comment>();   
    
        BufferedReader reader = new BufferedReader(new FileReader(filename1));   
        String line;   
        while ((line = reader.readLine()) != null) {   
            result.add(getPersonFromString(line));   
        }   
        reader.close();
        return result;   
    }
    //ɾ������
    public void DeleteComment(ArrayList<Comment> comments,int id) {
    	if(id>0) {
	    	for (Comment comment : comments) {
	    		if(comment.getId()==id) {
	    			comments.remove(comment);
	    			break;
	    		}
	    	}
    	}
    	try {
			saveComments(comments);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    //��־tagedornotλ,����bool=1��Ϊtrue,����������ֵ��Ϊfalse
    public void ChangeTagedornot(ArrayList<Comment> comments,int id,int bool) {
    	if(id>0) {
	    	for (Comment comment : comments) {
	    		if(comment.getId()==id) {
	    			if(bool==1) comment.setTagedornot(true);
	    			else comment.setTagedornot(false);
	    			break;
	    		}
	    	}
    	}
    	try {
			saveComments(comments);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    //�������б���ʽ����δ��ע������
    public ArrayList<Comment> unTagedComments(ArrayList<Comment> comments){
    	ArrayList<Comment> untagedcomments=new ArrayList<Comment>();
    	for(Comment comment : comments) {
    		if(!comment.getTagedornot()) {
    			untagedcomments.add(comment);
    		}
    	}
    	return untagedcomments;
    }
    
  //�������б���ʽ�����ѱ�ע������
    public ArrayList<Comment> TagedComments(ArrayList<Comment> comments){
    	ArrayList<Comment> tagedcomments=new ArrayList<Comment>();
    	for(Comment comment : comments) {
    		if(comment.getTagedornot()) {
    			tagedcomments.add(comment);
    		}
    	}
    	return tagedcomments;
    }
    
    //����ָ��ID��Comment
    public Comment getCommentbyID(ArrayList<Comment> comments,int id){
    	Comment commentofid=new Comment(1,"","",false);
    	for(Comment comment : comments) {
    		if(comment.getId()==id) {
    			commentofid=new Comment(comment.getId(),comment.getText(),comment.getSymbol(),comment.getTagedornot());
    			break;
    		}
    	}  	
		return commentofid;
    }
    
    // ͨ��һ���ļ���������һ�� Comment ����   
    private Comment getPersonFromString(String line) {   
        String[] parts = line.split("\t");  // ��ȡ���ָ����ĸ�����   
    
        return new Comment(Integer.parseInt(parts[0]),  parts[1],  parts[2], Boolean.parseBoolean(parts[3]));   
    }
    
    //�����ʽ����\t�ָ�
    private static String getCommentString(Comment comment) {   
        return comment.getId() + "\t" + comment.getText() + "\t" + comment.getSymbol() + "\t" + comment.getTagedornot();   
    }
    
    // ��ʾ Person ����   
   /* private static void showPersons(List<Comment> comments) {   
        for (Comment comment : comments) {   
            System.out.println(comment.getName() + ", " +   
            		comment.getBirthday() + ", " +   
                    (comment.isMale() ? "��" : "Ů"));   
        }   
    }*/
    
    /*public static void main(String[] args) throws Exception {   
    // ����������������顣
    // ��һ�����ᴴ��һЩ���󲢱��浽�ļ���
    // �ڶ���������ļ��ж�ȡ�������ݲ���ʾ������
    if (!new File(filename).exists()) {
    	
    } else {
        readAndShow();
    }   
	}*/ 
	
	// ���ɲ��������   
	/*private static void createAndSave() throws IOException {
	    ArrayList<Comment> persons = createPersons();
	    savePersons(persons);
	}   */
	
	
	// ����Ҫ����Ķ���   
	/*private static ArrayList<Comment> createPersons() {
	    ArrayList<Comment> result = new ArrayList<Comment>();   
	    result.add(new Comment("����", new Date(), true));   
	    result.add(new Comment("����", new Date(), false));   
	    result.add(new Comment("����", new Date(), true));   
	    return result;   
	}*/
}
