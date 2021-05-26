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
    public void saveComments(ArrayList<Comment> comments,String filename) throws IOException {   
    
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
    public void DeleteComment(ArrayList<Comment> comments,int id,String filename) {
    	if(id>0) {
	    	for (Comment comment : comments) {
	    		if(comment.getId()==id) {
	    			comments.remove(comment);
	    			break;
	    		}
	    	}
    	}
    	try {
			saveComments(comments,filename);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    //��־tagedornotλ,����bool=1��Ϊtrue,����������ֵ��Ϊfalse
    public void ChangeTagedornot(ArrayList<Comment> comments,int id,int bool,String filename) {
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
			saveComments(comments,filename);
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
    	Comment commentofid=new Comment(1,"","",false,false);
    	for(Comment comment : comments) {
    		if(comment.getId()==id) {
    			commentofid=new Comment(comment.getId(),comment.getText(),comment.getSymbol(),comment.getTagedornot(),comment.getIsconflict());
    			break;
    		}
    	}  	
		return commentofid;
    }
    
    //�ϲ����������ļ���������ͬ�ĺϲ���Ϊһ��
    public int[][] IdChangeList(ArrayList<Comment> a_commentslist,ArrayList<Comment> b_commentslist){
    	int[][] idchangelist=new int[10000][2];
    	int i=0;
    	int j=0;
    	for(Comment comment_a:a_commentslist) {
    		while(b_commentslist.size() != j) {
    			if(comment_a.getText().equals(b_commentslist.get(j).getText())) {
    				idchangelist[i][0]=comment_a.getId();
    				idchangelist[i][1]=b_commentslist.get(j).getId();
    				b_commentslist.remove(j);
    				i++;
    			}
    			else {j++;}
    		}
    		j=0;
    	}
    	int x=a_commentslist.get(a_commentslist.size()-1).getId()+1;
    	int y=0;
    	if(b_commentslist.size()!=0) {
    		for(Comment comment_b:b_commentslist) {
    			a_commentslist.add(new Comment(x,comment_b.getText(),comment_b.getSymbol(),comment_b.getTagedornot(), comment_b.getIsconflict()));
    			idchangelist[i][0]=x;
				idchangelist[i][1]=b_commentslist.get(y).getId();
				x++;
				i++;
				y++;
    		}
    	}
    	return idchangelist;
    }
    
    //��־istagconflictλ,����bool=1��Ϊtrue,����������ֵ��Ϊfalse
    public void ChangeIstagconflict(ArrayList<Comment> comments,int id,int bool,String filename) {
    	if(id>0) {
	    	for (Comment comment : comments) {
	    		if(comment.getId()==id) {
	    			if(bool==1) comment.setIsconflict(true);
	    			else comment.setIsconflict(false);
	    			break;
	    		}
	    	}
    	}
    	try {
			saveComments(comments,filename);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    //�������б���ʽ���ر�ע�޳�ͻ������
    public ArrayList<Comment> noConflictComments(ArrayList<Comment> comments){
    	ArrayList<Comment> noconflictcomments=new ArrayList<Comment>();
    	for(Comment comment : comments) {
    		if(!comment.getIsconflict()) {
    			noconflictcomments.add(comment);
    		}
    	}
    	return noconflictcomments;
    }
    
  //�������б���ʽ���ر�ע�г�ͻ������
    public ArrayList<Comment> ConflictComments(ArrayList<Comment> comments){
    	ArrayList<Comment> conflictcomments=new ArrayList<Comment>();
    	for(Comment comment : comments) {
    		if(comment.getIsconflict()) {
    			conflictcomments.add(comment);
    		}
    	}
    	return conflictcomments;
    }
    
    // ͨ��һ���ļ���������һ�� Comment ����   
    private Comment getPersonFromString(String line) {   
        String[] parts = line.split("\t");  // ��ȡ���ָ����ĸ�����   
    
        return new Comment(Integer.parseInt(parts[0]),  parts[1],  parts[2], Boolean.parseBoolean(parts[3]), Boolean.parseBoolean(parts[4]));   
    }
    
    //�����ʽ����\t�ָ�
    private static String getCommentString(Comment comment) {   
        return comment.getId() + "\t" + comment.getText() + "\t" + comment.getSymbol() + "\t" + comment.getTagedornot() + "\t" + comment.getIsconflict();   
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
