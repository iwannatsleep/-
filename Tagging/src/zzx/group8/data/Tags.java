package zzx.group8.data;

import java.util.List;

public class Tags {
	private String TagClass;	//��ǩ��
	private String TagName;		//��ǩ��
	private List ID;			//����ע����ID
	Tags(String TagClass,String TagName,List ID){
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
