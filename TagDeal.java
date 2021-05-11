package View;

import java.io.*;   
import java.util.ArrayList;   
import java.util.Date;   
import java.util.List;   

public class TagDeal {   
    
    // �ļ���������ָ������������ı��༭��������ļ���ע�⣬���±��޷������У�   
    static String filename = "Tag.txt";
    /*
    public static void main(String[] args) throws Exception {   
        // ����������������顣   
        // ��һ�����ᴴ��һЩ Tag ���󲢱��浽�ļ���   
        // �ڶ���������ļ��ж�ȡ�������ݲ���ʾ������   
        if (!new File(filename).exists()) {   
            createAndSave();   
        } else {   
            readAndShow();   
        }   
    }
     
    // ���ɲ����� Tag ����   
    private static void createAndSave() throws IOException {   
        List<Tag> Tags = createTags();   
        saveTags(Tags);   
    }
       
    // ��ȡ����ʾ Tag ����   
    private static void readAndShow() throws IOException {   
        List<Tag> Tags = readTags();   
        showTags(Tags);   
    } 
    */
    // ����Ҫ����� Person ����   
    private static List<Tag> createTags(/*String TagClass,String TagName*/) {   
        List<Tag> result = new ArrayList<Tag>();
        List<String> empty= new ArrayList();
        //result.add(new Tag(TagClass,TagName,empty))
        result.add(new Tag("�Ƿ�ò���","����",empty));   
        result.add(new Tag("�Ƿ�ò���","����",empty));   
        result.add(new Tag("�Ƿ�ò���","�޹�",empty));   
        return result;   
    }   
    
    // ���� Tag �����ļ��������ʽΪ��   
    // 1��ÿ�� Tag һ��   
    // 2��ÿ�����δ�� TagClass,TagName,ID ��������ֵ���� tab ����,ID��ͬԪ�ؼ��ö��Ÿ���
    private static void saveTags(List<Tag> tags) throws IOException {   
    
        // �����ļ�����   
        String data = "";   
        for (Tag tag : tags) {   
            data += getTagString(tag) + "\n";   
        }   
    
        // �����ļ�����   
        FileWriter writer = new FileWriter(filename);   
        writer.write(data);   
        writer.close();   
        System.out.println("���󱣴���ϡ�");   
    }   
    
    private static String getTagString(Tag tag) {   
        return tag.getTagClass() + "\t" + tag. getTagName() + "\t" + IDtoString(tag.getID());   
    }   
    
    // ���ļ��ж�ȡ Tag ����   
    private static List<Tag> readTags() throws IOException {   
        List<Tag> result = new ArrayList<Tag>();   
    
        BufferedReader reader = new BufferedReader(new FileReader(filename));   
        String line;   
        while ((line = reader.readLine()) != null) {   
            result.add(getTagFromString(line));   
        }   
    
        return result;   
    }   
    
    // ͨ��һ���ļ���������һ�� Tag ����   
    private static Tag getTagFromString(String line) {   
        String[] parts = line.split("\t");  // ��ȡ���ָ�����������   
    
        return new Tag(   
                parts[0],     // ��ǩ��  
                parts[1],     // ��ǩ��          
                IDtoInt(parts[2])// ����ע����ID 
        );   
    }   
    
    // ��ʾ Tag ����   
    private static void showTags(List<Tag> tags){   
        for (Tag tag : tags) {   
            System.out.println(tag.getTagClass() + "	" +   
                    tag.getTagName() + "	" +   
                    IDtoString(tag.getID()));   
        }
    }
    
    //��IDת��Ϊ��̬����
    private static List IDtoInt(String SID){
    	   List ID= new ArrayList();
    	   String[] CID = SID.split(",");
    	   for(int i=0;i<CID.length;i++) {
    	   ID.add(CID[i]);
    	   }
    	   return ID;
    }
    
    //��IDת��Ϊ�ַ���,����Ԫ�ؼ���","�ָ�
    private static String IDtoString(List ID){
    		String SID="";
    		String a;
    		for(int i=0;i<ID.size();i++){
    				a=(String)ID.get(i);
    				SID=SID+a+",";
    		}
			return SID;
    }

    //��ӱ��˱�ǩ��ע������ID
    public static void addID(List<Tag> tags,String tagclass,String tagname,int ID) throws IOException{    	   	 
         for (Tag tag : tags) {   
        	 if(tag.getTagClass() == tagclass && tag.getTagName() == tagname) {
        		 tag.addID(ID);
        		 break;
        	 }
         } 
         saveTags(tags);
    }
    
    //ɾ���������۱�ע��ID
    public static void delID(List<Tag> tags,String tagclass,String tagname,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.getTagClass() == tagclass && tag.getTagName() == tagname) {
        		tag.removeID(ID);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
    //ɾ��ĳһ��ǩ
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

