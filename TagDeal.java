package View;

import java.io.*;   
import java.util.ArrayList;   
import java.util.Date;   
import java.util.List;   

public class TagDeal {   
    
    // 文件名可随意指定，你可以用文本编辑器打开这个文件（注意，记事本无法处理换行）   
    static String filename = "Tag.txt";
    /*
    public static void main(String[] args) throws Exception {   
        // 将这个程序运行两遍。   
        // 第一遍它会创建一些 Tag 对象并保存到文件；   
        // 第二遍它会从文件中读取对象数据并显示出来。   
        if (!new File(filename).exists()) {   
            createAndSave();   
        } else {   
            readAndShow();   
        }   
    }
     
    // 生成并保存 Tag 对象   
    private static void createAndSave() throws IOException {   
        List<Tag> Tags = createTags();   
        saveTags(Tags);   
    }
       
    // 读取并显示 Tag 对象   
    private static void readAndShow() throws IOException {   
        List<Tag> Tags = readTags();   
        showTags(Tags);   
    } 
    */
    // 创建要保存的 Person 对象   
    private static List<Tag> createTags(/*String TagClass,String TagName*/) {   
        List<Tag> result = new ArrayList<Tag>();
        List<String> empty= new ArrayList();
        //result.add(new Tag(TagClass,TagName,empty))
        result.add(new Tag("是否好差评","好评",empty));   
        result.add(new Tag("是否好差评","差评",empty));   
        result.add(new Tag("是否好差评","无关",empty));   
        return result;   
    }   
    
    // 保存 Tag 对象到文件。保存格式为：   
    // 1、每个 Tag 一行   
    // 2、每行依次存放 TagClass,TagName,ID 三个属性值，用 tab 隔开,ID不同元素间用逗号隔开
    private static void saveTags(List<Tag> tags) throws IOException {   
    
        // 生成文件内容   
        String data = "";   
        for (Tag tag : tags) {   
            data += getTagString(tag) + "\n";   
        }   
    
        // 保存文件内容   
        FileWriter writer = new FileWriter(filename);   
        writer.write(data);   
        writer.close();   
        System.out.println("对象保存完毕。");   
    }   
    
    private static String getTagString(Tag tag) {   
        return tag.getTagClass() + "\t" + tag. getTagName() + "\t" + IDtoString(tag.getID());   
    }   
    
    // 从文件中读取 Tag 对象   
    private static List<Tag> readTags() throws IOException {   
        List<Tag> result = new ArrayList<Tag>();   
    
        BufferedReader reader = new BufferedReader(new FileReader(filename));   
        String line;   
        while ((line = reader.readLine()) != null) {   
            result.add(getTagFromString(line));   
        }   
    
        return result;   
    }   
    
    // 通过一行文件内容生成一个 Tag 对象   
    private static Tag getTagFromString(String line) {   
        String[] parts = line.split("\t");  // 获取被分隔的三个部分   
    
        return new Tag(   
                parts[0],     // 标签类  
                parts[1],     // 标签名          
                IDtoInt(parts[2])// 被标注评论ID 
        );   
    }   
    
    // 显示 Tag 对象   
    private static void showTags(List<Tag> tags){   
        for (Tag tag : tags) {   
            System.out.println(tag.getTagClass() + "	" +   
                    tag.getTagName() + "	" +   
                    IDtoString(tag.getID()));   
        }
    }
    
    //将ID转换为动态数组
    private static List IDtoInt(String SID){
    	   List ID= new ArrayList();
    	   String[] CID = SID.split(",");
    	   for(int i=0;i<CID.length;i++) {
    	   ID.add(CID[i]);
    	   }
    	   return ID;
    }
    
    //将ID转换为字符串,数组元素间用","分隔
    private static String IDtoString(List ID){
    		String SID="";
    		String a;
    		for(int i=0;i<ID.size();i++){
    				a=(String)ID.get(i);
    				SID=SID+a+",";
    		}
			return SID;
    }

    //添加被此标签标注的评论ID
    public static void addID(List<Tag> tags,String tagclass,String tagname,int ID) throws IOException{    	   	 
         for (Tag tag : tags) {   
        	 if(tag.getTagClass() == tagclass && tag.getTagName() == tagname) {
        		 tag.addID(ID);
        		 break;
        	 }
         } 
         saveTags(tags);
    }
    
    //删除被此评论标注的ID
    public static void delID(List<Tag> tags,String tagclass,String tagname,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.getTagClass() == tagclass && tag.getTagName() == tagname) {
        		tag.removeID(ID);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
    //删除某一标签
    public static void delTag(List<Tag> tags,String tagclass,String tagname) throws IOException {
        for (Tag tag : tags) {   
        	if(tag.getTagClass() == tagclass && tag.getTagName() == tagname) {
        		tags.remove(tag);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
 } 

