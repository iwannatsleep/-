package xhr.group8.databank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xhr.group8.data.Comment;

public class CommentsDatabank {
    // �ļ���������ָ������������ı��༭��������ļ���ע�⣬���±��޷������У�   
    static String filename = "Comment.data";   
    ArrayList<Comment> comments_list;
    
    public ArrayList<Comment> getCommentslist() {
        return comments_list;
    }
    public void setCommentslist(ArrayList<Comment> comments_list) {this.comments_list=comments_list;}
    
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
    
    // ��������ļ��������ʽΪ��   
    // 1��ÿ��һ��   
    // 2��ÿ�����δ�� name��birthday��male ��������ֵ���� tab ����   
    // 3��birthday ���Ա�����Ǻ�������male ���Ա�������ַ���   
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
    
    private static String getCommentString(Comment comment) {   
        return comment.getId() + "\t" + comment.getText() + "\t" + comment.getSymbol();   
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
    
    // ͨ��һ���ļ���������һ�� Person ����   
    private static Comment getPersonFromString(String line) {   
        String[] parts = line.split("\t");  // ��ȡ���ָ�����������   
    
        return new Comment(Integer.parseInt(parts[0]),  parts[1],  parts[2] );   
    }   
    
    // ��ʾ Person ����   
   /* private static void showPersons(List<Comment> comments) {   
        for (Comment comment : comments) {   
            System.out.println(comment.getName() + ", " +   
            		comment.getBirthday() + ", " +   
                    (comment.isMale() ? "��" : "Ů"));   
        }   
    }*/
}
