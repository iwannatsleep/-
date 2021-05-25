package control;

import java.io.*;   
import java.util.ArrayList;   
import java.util.Date;   
import java.util.List;

import data.Tag;   

public class TagDeal {   
    
    // 文件名可随意指定，你可以用文本编辑器打开这个文件（注意，记事本无法处理换行）   
    static String filename = "Tag.txt";

    // 创建要保存的 Person 对象   
    public static  ArrayList<Tag> createTags( ArrayList<Tag> tags,String TagClass,String TagName) throws IOException {   
    	ArrayList empty= new ArrayList();
    	empty.add(0);
        tags.add(new Tag(TagClass,TagName,empty));
        saveTags(tags);
        return tags;   
    }   
    
    // 保存 Tag 对象到文件。保存格式为：   
    // 1、每个 Tag 一行   
    // 2、每行依次存放 TagClass,TagName,ID 三个属性值，用 tab 隔开,ID不同元素间用逗号隔开
    public static void saveTags( ArrayList<Tag> tags) throws IOException {   
    
        // 生成文件内容   
        String data = "";   
        for (Tag tag : tags) {   
            data += getTagString(tag) + "\n";   
        }   
    
        // 保存文件内容   
        FileWriter writer = new FileWriter(filename);   
        writer.write(data);   
        writer.close();   
    }   
    
    private static String getTagString(Tag tag) {   
        return tag.getTagClass() + "\t" + tag. getTagName() + "\t" + IDtoString(tag.getID());   
    }   
    
    // 从文件中读取 Tag 对象   
    public static  ArrayList<Tag> readTags(String filename) throws IOException {   
    	 ArrayList<Tag> result = new ArrayList<Tag>();   
    
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
    private static void showTags(ArrayList<Tag> tags){   
        for (Tag tag : tags) {   
            System.out.println(tag.getTagClass() + "	" +   
                    tag.getTagName() + "	" +   
                    IDtoString(tag.getID()));   
        }
    }
    
    //将ID转换为动态数组
    private static ArrayList IDtoInt(String SID){
    	   ArrayList ID= new ArrayList();
    	   String[] CID = SID.split(",");
    	   for(int i=0;i<CID.length;i++) {
    	   ID.add(CID[i]);
    	   }
    	   return ID;
    }
    
    //将ID转换为字符串,数组元素间用","分隔
    private static String IDtoString(ArrayList ID){
    		String SID="";
    		String a;
    		for(int i=0;i<ID.size();i++){
    				a=ID.get(i).toString();
    				SID=SID+a+",";
    		}
			return SID;
    }

    //添加被此标签标注的评论ID
    public static void addID(ArrayList<Tag> tags,String tagclass,String tagname,int ID) throws IOException{    	   	 
         for (Tag tag : tags) {   
        	 if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname) && !tag.haveID(ID)) {
        		 tag.addID(ID);
        		 break;
        	 }
         } 
         saveTags(tags);
    }
    
    //删除被此评论标注的ID
    public static void delID(ArrayList<Tag> tags,String tagclass,String tagname,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname)) {
        		tag.removeID(ID);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
    //删除某一标签
    public static void delTag(ArrayList<Tag> tags,String tagclass,String tagname) throws IOException {
        for (Tag tag : tags) {   
        	if(tag.getTagClass().equals(tagclass) && tag.getTagName().equals(tagname)) {
        		tags.remove(tag);
        		break;
        	}
        } 
        saveTags(tags);
    }
    
  //获取所有标签类
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
    
    /*//获取所有标签类
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
    
    
    //获取某一标签类的所有标签
    public static ArrayList<Tag> getAllTag(ArrayList<Tag> tags,String tagclass) {
    	ArrayList<Tag> tagnames= new ArrayList<Tag>();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass)) {
    			tagnames.add(tag);
    		}
    	}
		return tagnames;
    }
    
    //获取标注此评论的所有标签
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
    					temp=temp+"            冲突";
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
    
  //获取某一标签类的所有标签列表(xinhanrui)
    public static ArrayList<String> getAllTagString(ArrayList<Tag> tags,String tagclass) {
    	ArrayList<String> tagnames= new ArrayList<String>();
    	for(Tag tag : tags) {
    		if(tag.getTagClass().equals(tagclass)) {
    			tagnames.add(tag.getTagName());
    		}
    	}
		return tagnames;
    }
    
	//测试删除所有标签列表中指定ID(xinhanrui)
    public static void delAllID(ArrayList<Tag> tags,int ID) throws IOException{
        for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		tag.removeID(ID);
        	}
        } 
        saveTags(tags);
    }
    
  //返回该评论是否被标签标注(xinhanrui)
    public static int IsIDempty(ArrayList<Tag> tags,int ID){
        int isempty=1;
    	for (Tag tag : tags) {   
        	if(tag.haveID(ID)) {
        		isempty=0;
        	}
        } 
        return isempty;
    }
    
    //获取所有标签类与其标签的字符串列表(xinhanrui)
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
    
    //返回指定的ID的评论的arraylist列表(xinhanrui)
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    	}
    }
    
    //判断同一个评论是否在同一个标签类里标注了多个
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
