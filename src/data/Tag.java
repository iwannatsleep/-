package data;

import java.util.ArrayList;
import java.util.List;

public class Tag{
	private String TagClass;	//标签类
	private String TagName;		//标签名
	private ArrayList ID;			//被标注评论ID
	public Tag(String TagClass,String TagName,ArrayList ID){
		this.TagClass=TagClass;
		this.TagName=TagName;
		this.ID=ID;
	}
    public String getTagClass() {   
        return TagClass;   
    }
    public String getTagName() {   
        return TagName;   
    }
    public ArrayList getID() {   
        return ID;   
    }
    public void addID(int i) {   
        ID.add(i);   
    }
    public void removeID(int i) {   
        ID.remove("i");   
    }
    public int getIDnumber() {   
        return ID.size();   
    }
    public boolean haveID(int i) {
    	if(ID!=null) {
        	for(int j=0;j<ID.size();j++) {
        		if(ID.get(j).toString().equals(String.valueOf(i))) {
        				return true;
        			}
        	}
    	}
    	return false;
    }
    private static String getType(Object a) {
        return a.getClass().toString();
 
    }
}
