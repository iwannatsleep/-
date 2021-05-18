package control;

import java.io.*;   
import java.util.ArrayList;   
import java.util.Date;   
import java.util.List;

import data.Tag;   

public class TagDeal {   
    
    // �ļ���������ָ������������ı��༭��������ļ���ע�⣬���±��޷��������У�   
    static String filename = "Tag.txt";

    // ����Ҫ����� Person ����   
    public static  ArrayList<Tag> createTags( ArrayList<Tag> tags,String TagClass,String TagName) throws IOException {   
    	ArrayList empty= new ArrayList();
    	empty.add(0);
        tags.add(new Tag(TagClass,TagName,empty));
        saveTags(tags);
        return tags;   
    }   
    
    // ���� Tag �����ļ��������ʽΪ��   
    // 1��ÿ�� Tag һ��   
    // 2��ÿ�����δ�� TagClass,TagName,ID ��������ֵ���� tab ����,ID��ͬԪ�ؼ��ö��Ÿ���
    public static void saveTags( ArrayList<Tag> tags) throws IOException {   
    
        // �����ļ�����   
        String data = "";   
        for (Tag tag : tags) {   
            data += getTagString(tag) + "\n";   
        }   
    
        // �����ļ�����   
        FileWriter writer = new FileWriter(filename);   
        writer.write(data);   
        writer.close();   
    }   
    
    private static String getTagString(Tag tag) {   
        return tag.getTagClass() + "\t" + tag. getTagName() + "\t" + IDtoString(tag.getID());   
    }   
    
    // ���ļ��ж�ȡ Tag ����   
    public static  ArrayList<Tag> readTags() throws IOException {   
    	 ArrayList<Tag> result = new ArrayList<Tag>();   
    
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
    private static void showTags(ArrayList<Tag> tags){   
        for (Tag tag : tags) {   
            System.out.println(tag.getTagClass() + "	" +   
                    tag.getTagName() + "	" +   
                    IDtoString(tag.getID()));   
        }
    }
    
    //��IDת��Ϊ��̬����
    private static ArrayList IDtoInt(String SID){
    	   ArrayList ID= new ArrayList();
    	   String[] CID = SID.split(",");
    	   for(int i=0;i<CID.length;i++) {
    	   ID.add(CID[i]);
    	   }
    	   return ID;
    }
    
    //��IDת��Ϊ�ַ���,����Ԫ�ؼ���","�ָ�
    private static String IDtoString(ArrayList ID){
    		String SID="";
    		String a;
    		for(int i=0;i<ID.size();i++){
    				a=ID.get(i).toString();
    				SID=SID+a+",";
    		}
			return SID;
    }

    //���ӱ��˱�ǩ��ע������ID
    public static void addID(ArrayList<Tag> tags,String tagclass,String tagname,int ID) throws IOException{    	   	 
         for (Tag tag : tags) {   
        	 if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname) && !tag.haveID(ID)) {
        		 tag.addID(ID);
        		 break;
        	 }
         } 
         saveTags(tags);
    }
    
    //ɾ���������۱�ע��ID
    public static void delID(ArrayList<Tag> tags,String tagclass,String tagname,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname)) {
        		tag.removeID(ID);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
    //ɾ��ĳһ��ǩ
    public static void delTag(ArrayList<Tag> tags,String tagclass,String tagname) throws IOException {
        for (Tag tag : tags) {   
        	if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname)) {
        		tags.remove(tag);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
  //��ȡ���б�ǩ��
    public static ArrayList<String> getAllTagClass(ArrayList<Tag> tags) {
    	ArrayList<String> tagclasses= new ArrayList<String>();
    	for(Tag tag : tags) {
    		int flag=0;
    		for (int i=0;i<tagclasses.size();i++) {
    			if(tag.getTagClass().equals(tagclasses.get(i))) {
    				flag=1;
    			}
    		}
    		if(flag==0) {tagclasses.add(tag.getTagClass());}	
    	}

        return tagclasses;
    }
    
    /*//��ȡ���б�ǩ��
    public static ArrayList<String> getAllTagClass(ArrayList<Tag> tags) {
    	ArrayList<String> tagclasses= new ArrayList<String>();
    	for(Tag tag : tags) {
    		tagclasses.add(tag.getTagClass());
    	}
        for (int i = 0; i < tagclasses.size() - 1; i++) {
            for (int j = i + 1; j < tagclasses.size(); j++) {
                if (tagclasses.get(i).equals(tagclasses.get(j))) {
                	tagclasses.remove(j);
                }
            }
        }
        return tagclasses;
    }*/
    
    
    //��ȡĳһ��ǩ������б�ǩ
    public static ArrayList<Tag> getAllTag(ArrayList<Tag> tags,String tagclass) {
    	ArrayList<Tag> tagnames= new ArrayList<Tag>();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass)) {
    			tagnames.add(tag);
    		}
    	}
		return tagnames;
    }
    
    //��ȡ��ע�����۵����б�ǩ
    public static ArrayList<String> getTaged(ArrayList<Tag> tags,int ID) {
    	ArrayList<String> taged= new ArrayList<String>();
    	String temp;
    	for(Tag tag : tags) {
    		if(tag.haveID(ID)) {
    			temp=tag.getTagClass()+":"+tag.getTagName();
    			taged.add(temp);
    		}
        }
    	return taged;
    } 
    
  //��ȡĳһ��ǩ������б�ǩ
    public static ArrayList<String> getAllTagString(ArrayList<Tag> tags,String tagclass) {
    	ArrayList<String> tagnames= new ArrayList<String>();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass)) {
    			tagnames.add(tag.getTagName());
    		}
    	}
    	/*
    	for(String tagn:tagnames)
    		System.out.printf(tagn);
    		*/
		return tagnames;
    }
    
	//ɾ�����б������۱�ע��ID
    public static void delAllID(ArrayList<Tag> tags,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		tag.removeID(ID);
        	}
        } 
        saveTags(tags);
    }
    
  //���ظ������Ƿ񱻱�ǩ��ע
    public static int IsIDempty(ArrayList<Tag> tags,int ID){
        int isempty=1;
    	for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		isempty=0;
        	}
        } 
        return isempty;
    }
    
    //��ȡ���б�ǩ�������ǩ���ַ����б�
    public static ArrayList<String> getAllTagandClass(ArrayList<Tag> tags) {
    	ArrayList<String> alltagclass,alltag;
    	ArrayList<String> tagandclass= new ArrayList<String>();
    	alltagclass=getAllTagClass(tags);
    	for(String tagclass : alltagclass) {
    		alltag=getAllTagString(tags,tagclass);
    		for(int i=0;i<alltag.size();i++) {
    		tagandclass.add(tagclass + ":" + alltag.get(i));
    		}
    	}
		return tagandclass;
    }
    
    //����ָ����ID��arraylist�б�
    public static ArrayList getTagIDs(ArrayList<Tag> tags,String tagclass,String tagname) {
    	ArrayList tagIDs=new ArrayList();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname)) {
    			for(int i=0;i<tag.getID().size();i++) {
    				tagIDs.add(tag.getID().get(i));
    			}
    		}
    	}
    	return tagIDs;
    }
    
	public static void main(String[] args) throws IOException {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		 tags=readTags();
	}
 } 