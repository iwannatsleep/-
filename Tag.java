package View;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	private String TagClass;	//标签类
	private String TagName;		//标签名
	private List ID;			//被标注评论ID
	Tag(String TagClass,String TagName,List ID){
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
    public List getID() {   
        return ID;   
    }
    public void addID(int i) {   
        ID.add(i);   
    }
    public void removeID(int i) {   
        ID.remove(i);   
    }
    public int getIDnumber() {   
        return ID.size();   
    }
}
