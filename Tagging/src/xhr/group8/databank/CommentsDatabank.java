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
    // 文件名可随意指定，你可以用文本编辑器打开这个文件（注意，记事本无法处理换行）   
    static String filename = "Comment.data";   
    ArrayList<Comment> comments_list;
    
    public ArrayList<Comment> getCommentslist() {
        return comments_list;
    }
    public void setCommentslist(ArrayList<Comment> comments_list) {this.comments_list=comments_list;}
    
    /*public static void main(String[] args) throws Exception {   
        // 将这个程序运行两遍。
        // 第一遍它会创建一些对象并保存到文件；
        // 第二遍它会从文件中读取对象数据并显示出来。
        if (!new File(filename).exists()) {
        	
        } else {
            readAndShow();
        }   
    }*/ 
    
    // 生成并保存对象   
    /*private static void createAndSave() throws IOException {
        ArrayList<Comment> persons = createPersons();
        savePersons(persons);
    }   */
    
    
    // 创建要保存的对象   
    /*private static ArrayList<Comment> createPersons() {
        ArrayList<Comment> result = new ArrayList<Comment>();   
        result.add(new Comment("张三", new Date(), true));   
        result.add(new Comment("李四", new Date(), false));   
        result.add(new Comment("王五", new Date(), true));   
        return result;   
    }*/
    
    // 保存对象到文件。保存格式为：   
    // 1、每个一行   
    // 2、每行依次存放 name、birthday、male 三个属性值，用 tab 隔开   
    // 3、birthday 属性保存的是毫秒数，male 属性保存的是字符串   
    public void saveComments(ArrayList<Comment> comments) throws IOException {   
    
    	File file = new File(filename);
    	if (!file.exists()) {
    	    try {
    	        file.createNewFile();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
	    // 生成文件内容   
	    String data = "";   
	    for (Comment comment : comments) {   
	    data += getCommentString(comment) + "\n";   
	    }   
	    
	    // 保存文件内容   
	    FileWriter writer = new FileWriter(filename);   
	    writer.write(data);   
	    writer.close();   
	    //System.out.println("对象保存完毕。");
	    
    }   
    
    private static String getCommentString(Comment comment) {   
        return comment.getId() + "\t" + comment.getText() + "\t" + comment.getSymbol();   
    }
    
    // 从文件中读取对象
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
    
    // 通过一行文件内容生成一个 Person 对象   
    private static Comment getPersonFromString(String line) {   
        String[] parts = line.split("\t");  // 获取被分隔的三个部分   
    
        return new Comment(Integer.parseInt(parts[0]),  parts[1],  parts[2] );   
    }   
    
    // 显示 Person 对象   
   /* private static void showPersons(List<Comment> comments) {   
        for (Comment comment : comments) {   
            System.out.println(comment.getName() + ", " +   
            		comment.getBirthday() + ", " +   
                    (comment.isMale() ? "男" : "女"));   
        }   
    }*/
}
