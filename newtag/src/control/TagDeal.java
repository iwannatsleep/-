package control;

import java.io.*;   
import java.util.ArrayList;   
import java.util.Date;   
import java.util.List;

import data.Tag;   

public class TagDeal {   
    
    // �ļ���������ָ������������ı��༭��������ļ���ע�⣬���±��޷������У�   
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
    public static  ArrayList<Tag> readTags(String filename) throws IOException {   
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

    //��ӱ��˱�ǩ��ע������ID
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
    	String[][] checklist=new String[1000][2];
    	ArrayList<String> taged= new ArrayList<String>();
    	String temp;
    	int i = 0;
    	int j = 0;
    	for(Tag tag : tags) {
    		if(tag.haveID(ID)) {
    			checklist[i][0]=tag.getTagClass();
    			checklist[i][1]=tag.getTagName();
    			i++;
    		}
        }
    	for(Tag tag : tags) {
    		if(tag.haveID(ID)) {
    			temp=tag.getTagClass()+" "+tag.getTagName();
    			while(checklist[j][0]!=null) {
    				if(checklist[j][0].equals(tag.getTagClass())&&(!checklist[j][1].equals(tag.getTagName()))) {
    					temp=temp+"            ��ͻ";
    					break;
    				}
    				j++;
    			}
    			taged.add(temp);
    			j = 0;
    		}
        }
    	return taged;
    } 
    
  //��ȡĳһ��ǩ������б�ǩ�б�(xinhanrui)
    public static ArrayList<String> getAllTagString(ArrayList<Tag> tags,String tagclass) {
    	ArrayList<String> tagnames= new ArrayList<String>();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass)) {
    			tagnames.add(tag.getTagName());
    		}
    	}
		return tagnames;
    }
    
	//����ɾ�����б�ǩ�б���ָ��ID(xinhanrui)
    public static void delAllID(ArrayList<Tag> tags,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		tag.removeID(ID);
        	}
        } 
        saveTags(tags);
    }
    
  //���ظ������Ƿ񱻱�ǩ��ע(xinhanrui)
    public static int IsIDempty(ArrayList<Tag> tags,int ID){
        int isempty=1;
    	for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		isempty=0;
        	}
        } 
        return isempty;
    }
    
    //��ȡ���б�ǩ�������ǩ���ַ����б�(xinhanrui)
    public static ArrayList<String> getAllTagandClass(ArrayList<Tag> tags) {
    	ArrayList<String> alltagclass,alltag;
    	ArrayList<String> tagandclass= new ArrayList<String>();
    	alltagclass=getAllTagClass(tags);
    	for(String tagclass : alltagclass) {
    		alltag=getAllTagString(tags,tagclass);
    		for(int i=0;i<alltag.size();i++) {
    		tagandclass.add(tagclass + " " + alltag.get(i));
    		}
    	}
		return tagandclass;
    }
    
    //����ָ����ID�����۵�arraylist�б�(xinhanrui)
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
    
    //
    public static void TagClassMerge(ArrayList<Tag> tags_a,ArrayList<Tag> tags_b) {
    	boolean flag;
    	for(Tag tag_b:tags_b) {
    		flag=true;
    		for(Tag tag_a:tags_a) {
    			if(tag_a.getTagClass().equals(tag_b.getTagClass()) && tag_a.getTagName().equals(tag_b.getTagName())) {
    				flag=false;
    			}
    		}
    		if(flag) {
    			ArrayList a=new ArrayList();a.add(0);
    			tags_a.add(new Tag(tag_b.getTagClass(),tag_b.getTagName(),a));
    		}
    	}
    }
    
    //
    public static void TagsMerge(ArrayList<Tag> tags_a,ArrayList<Tag> tags_b,int IDA,int IDB) {
    	ArrayList<String> idstring=getTaged(tags_b,IDB);
    	for(int i=0;i<idstring.size();i++) {
    		String[] parts = idstring.get(i).split(" ");
    		try {
				addID(tags_a,parts[0],parts[1], IDA);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
    	}
    }
    
    //�ж�ͬһ�������Ƿ���ͬһ����ǩ�����ע�˶��
    public static boolean IsConflict(ArrayList<Tag> tags,int ID) {
    	String[][] checklist=new String[1000][2];
    	int i = 0;
    	int j = 0;
    	for(Tag tag : tags) {
    		if(tag.haveID(ID)) {
    			checklist[i][0]=tag.getTagClass();
    			checklist[i][1]=tag.getTagName();
    			i++;
    		}
        }
    	for(Tag tag : tags) {
    		if(tag.haveID(ID)) {
    			while(checklist[j][0]!=null) {
    				if(checklist[j][0].equals(tag.getTagClass())&&(!checklist[j][1].equals(tag.getTagName()))) {
    					return true;
    				}
    				j++;
    			}
    			j = 0;
    		}
        }
    	return false;
    }
    
	public static void main(String[] args) throws IOException {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		 tags=readTags("Tag.txt");
	}
 } 
