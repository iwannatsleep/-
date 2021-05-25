package view;

import java.awt.Button;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import control.CommentsDatabank;
import control.TagDeal;
import data.Comment;
import data.Tag;

public class TagView2 extends  JPanel{;
	ArrayList<Comment> comments_list,comments_show_list;
	ArrayList<Tag> tags;
	ArrayList<String> tagschooselist,tagsclasschooselist;
	JTable commentslist;
	JList tagedlist,taglist;
	int selectedID,deleteID;
	Vector cData,cName,cRow;
	DefaultTableModel cmodel;
	String selectedtagcla,selectedtag,selectedtaged,selectedcommentscla;
	CommentsDatabank commentsdatabank=new CommentsDatabank();
	TagView2() throws IOException {
		selectedID=-1;
////////读入数据
		comments_list=commentsdatabank.readComments("Comment.data");
		tags=TagDeal.readTags("Tag.txt");
		if(tags.get(0).haveID(1)) {
			String temp=tags.get(0).getTagClass()+"."+tags.get(0).getTagName();
		}
		//System.out.print(tags.get(0).getTagClass());
		//System.out.print(tags.get(0).getTagName());
		//System.out.print(tags.get(0).getID());
////////评论表
        JLabel clabel=new JLabel(comments_list.get(0).getSymbol());    //创建标签
        clabel.setBounds(10,10,200,30);
        add(clabel);
        cData = new Vector();
		cName = new Vector();
		cName.add("ID");
		cName.add("内容");
		cRow = new Vector();
		for(int i=0;i<comments_list.size();i++) {
			cRow.add(comments_list.get(i).getId());
			cRow.add(comments_list.get(i).getText());
			cData.add(cRow.clone());
			cRow.clear();
		}
		cmodel = new DefaultTableModel(cData, cName);
		commentslist=new JTable(cmodel){
        public boolean isCellEditable(int row, int column)
        	{
        		return false;//表格不允许被编辑
        	}
        }; 
		JScrollPane cscroll = new JScrollPane(commentslist);
		cscroll.setBounds(10, 50, 300, 350);
		add(cscroll);
		setLayout(null);
////////显示评论内容
		JLabel tlabel=new JLabel("内容：");   
        tlabel.setBounds(330,10,200,30);
        add(tlabel);
		JTextPane txt = new JTextPane();
		JScrollPane txtscroll = new JScrollPane(txt);
		txtscroll.setBackground(SystemColor.inactiveCaptionBorder);
		txtscroll.setBounds(330, 50, 250, 350);
		add(txtscroll);

////////已被标注标签表
		JLabel tdlabel=new JLabel("已标注标签：");    
        tdlabel.setBounds(600,10,200,30);
        add(tdlabel);
		DefaultListModel tmodel = new DefaultListModel();
		tagedlist=new JList(tmodel);
		tagedlist = new JList(tmodel);
		JScrollPane tagedscroll = new JScrollPane(tagedlist);
		tagedscroll.setBounds(600, 50, 250, 350);
		add(tagedscroll);


////////筛选评论下拉栏
        JLabel commentschooselabel=new JLabel("筛选评论:");    //创建标签
        commentschooselabel.setBounds(80,20,60,20);
        String[] commentscombobox= new String[99];
        commentscombobox[0]="全部评论";
        commentscombobox[1]="未标注评论";
        commentscombobox[2]="已标注评论";
        commentscombobox[3]="标注有冲突评论";
        JComboBox<String> commentschooselist=new JComboBox<String>(commentscombobox);    //创建JComboBox
        commentschooselist.setBounds(170,20,150,20);
        tagschooselist=TagDeal.getAllTagandClass(tags);
        for(int i=0;i<tagschooselist.size();i++)
        	commentscombobox[i+4]=tagschooselist.get(i);    //向下拉列表中添加一项
        commentschooselist.setModel(new DefaultComboBoxModel<String>(commentscombobox));
        add(commentschooselabel);
        add(commentschooselist);



///////评论表事件
	    commentslist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// 左键点击选取评论进行标注
	            int row = commentslist.getSelectedRow();
	            selectedID=(int) commentslist.getValueAt(row, 0);
	            txt.setText((String) commentslist.getValueAt(row, 1));//显示评论内容
	            tmodel.clear();
	            for(int i=0;i<TagDeal.getTaged(tags,selectedID).size();i++) {
	            	tmodel.addElement(TagDeal.getTaged(tags,selectedID).get(i));
	            }
	        }
	        else if (SwingUtilities.isRightMouseButton(e)) {
	        	//右键点击进行删除
	        	int row_delete = commentslist.getSelectedRow();
	        	deleteID=(int) commentslist.getValueAt(row_delete, 0);
	        	commentsdatabank.DeleteComment(comments_list,deleteID);
	        	try {
					TagDeal.delAllID(tags, deleteID);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
	        	cData.clear();
	        	cRow.clear();
	        	for(int i=0;i<comments_list.size();i++) {
	    			cRow.add(comments_list.get(i).getId());
	    			cRow.add(comments_list.get(i).getText());
	    			cData.add(cRow.clone());
	    			cRow.clear();
	    		}
	        	cmodel.setDataVector(cData, cName);
	        	txt.setText("");
	        	tmodel.clear();
	        }
	    } 
	});
	
////筛选评论下拉栏事件
	commentschooselist.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	        // 选取标签类后在下方列表显示此标签类的标签
	    	if(e.getStateChange()==ItemEvent.SELECTED){
	    		selectedcommentscla=(String) e.getItem();
	    		comments_show_list=comments_list;
	        	if(selectedcommentscla.equals("全部评论")) {
	        		comments_show_list=comments_list;
	        	}
	        	else if(selectedcommentscla.equals("未标注评论")) {
	        		comments_show_list=commentsdatabank.unTagedComments(comments_list); 
	        	}
	        	else if(selectedcommentscla.equals("已标注评论")) {
	        		comments_show_list=commentsdatabank.TagedComments(comments_list);
	        	}
	        	else if(selectedcommentscla.equals("标注有冲突评论")) {
	        		comments_show_list=commentsdatabank.ConflictComments(comments_list);
	        	}
	        	else {
	        		String[] parts = selectedcommentscla.split(" ");
	        		ArrayList tagIDs = TagDeal.getTagIDs(tags,parts[0],parts[1]);
	        		ArrayList<Comment> choose_comments_list=new ArrayList<Comment>();
	        		for(int i=0;i<tagIDs.size();i++) {
	        			choose_comments_list.add(commentsdatabank.getCommentbyID(comments_list,Integer.parseInt(tagIDs.get(i).toString())));
	        		}
	        		choose_comments_list.remove(0);
	        		comments_show_list=choose_comments_list;
	        	}
        		cData.clear();
	        	cRow.clear();
	        	for(int i=0;i<comments_show_list.size();i++) {
	    			cRow.add(comments_show_list.get(i).getId());
	    			cRow.add(comments_show_list.get(i).getText());
	    			cData.add(cRow.clone());
	    			cRow.clear();
	    		}
	        	cmodel.setDataVector(cData, cName);
	        	txt.setText("");
	        	tmodel.clear();
	    	}
	    }
	});

////删除已贴标签
	tagedlist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// 右键点击选取标签标注此评论
	        	if(selectedID!=-1)
	        	//如果已经选取了某一评论
	        	{
	        		selectedtaged=(String)tagedlist.getSelectedValue();
	        		String[] parts = selectedtaged.split(" ");
	        		try {
						TagDeal.delID(tags,parts[0],parts[1], selectedID);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		if(TagDeal.IsIDempty(tags,selectedID)==1) {
	        			commentsdatabank.ChangeTagedornot(comments_list, selectedID, 0);
	        		}
	        		if(!TagDeal.IsConflict(tags, selectedID)) {
	        			commentsdatabank.ChangeIstagconflict(comments_list, selectedID, 0);
	        		}
	        		tmodel.clear();
		            for(int i=0;i<TagDeal.getTaged(tags,selectedID).size();i++) {
		            	tmodel.addElement(TagDeal.getTaged(tags,selectedID).get(i));
		            }
	        	}
	        }
	        if (SwingUtilities.isRightMouseButton(e)) {
	        	//右键点击修改此标签
	        }
	    }
	});
	}
}