package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

public class TagView extends  JPanel{;
	ArrayList<Comment> comments_list;
	ArrayList<Tag> tags;
	JTable commentslist;
	JList tagedlist,taglist;
	int selectedID;
	String selectedtagcla,selectedtag;
	TagView() throws IOException {
		selectedID=-1;
////////读入数据
		comments_list=CommentsDatabank.readComments("Comment.data");
		tags=TagDeal.readTags();
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
		Vector cData = new Vector();
		Vector cName = new Vector();
		cName.add("ID");
		cName.add("内容");
		Vector cRow = new Vector();
		for(int i=0;i<comments_list.size();i++) {
			cRow.add(comments_list.get(i).getId());
			cRow.add(comments_list.get(i).getText());
			cData.add(cRow.clone());
			cRow.clear();
		}
		DefaultTableModel cmodel = new DefaultTableModel(cData, cName);
		commentslist=new JTable(cmodel){
            public boolean isCellEditable(int row, int column)
                      {
                                            return false;//表格不允许被编辑
                      }
            }; 
		JScrollPane cscroll = new JScrollPane(commentslist);
		cscroll.setBounds(10, 50,300, 350);
		add(cscroll);
		setLayout(null);
////////显示评论内容
		JLabel tlabel=new JLabel("内容：");   
        tlabel.setBounds(320,50,200,30);
        add(tlabel);
		JTextPane txt = new JTextPane();
		JScrollPane txtscroll = new JScrollPane(txt);
		txtscroll.setBackground(SystemColor.inactiveCaptionBorder);
		txtscroll.setBounds(320, 80, 200, 145);
		add(txtscroll);

////////已被标注标签表
		JLabel tdlabel=new JLabel("已标注标签：");    
        tdlabel.setBounds(320,225,200,30);
        add(tdlabel);
		DefaultListModel tmodel = new DefaultListModel();
		tagedlist=new JList(tmodel);
		tagedlist = new JList(tmodel);
		JScrollPane tagedscroll = new JScrollPane(tagedlist);
		tagedscroll.setBounds(320,255,200,145);
		add(tagedscroll);
		
////////标签类下拉栏
        JLabel tclabel=new JLabel("标签类:");  
        tclabel.setBounds(530,50,50,20);
        JComboBox tclist=new JComboBox();    //创建JComboBox
        tclist.setBounds(600,50,200,20);
        for(int i=0;i<TagDeal.getAllTagClass(tags).size();i++)
        	tclist.addItem(TagDeal.getAllTagClass(tags).get(i));    //向标签类下拉列表中添加一项
        add(tclabel);
        add(tclist);
////////标签表
		DefaultListModel alltmodel = new DefaultListModel();
		taglist=new JList(alltmodel);
		JScrollPane tagscroll = new JScrollPane(taglist);
		for(int i=0;i<TagDeal.getAllTag(tags,(String)tclist.getSelectedItem()).size();i++)
			alltmodel.addElement(TagDeal.getAllTag(tags,(String)tclist.getSelectedItem()).get(i).getTagName());    //向标签表列表中添加一项
		tagscroll.setBounds(600,100,200,150);
		add(tagscroll);
///////右键删除菜单
		JPopupMenu popup=new JPopupMenu();
	    JMenuItem del=new JMenuItem("删除");
	    del.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		try {
					TagDeal.delTag(tags,selectedtagcla,selectedtag);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		alltmodel.removeElement(selectedtag);
	    		if(alltmodel.isEmpty()) {
	    			//此处需要更新tclist
	    			tclist.remove(tclist.getSelectedIndex());
	    		}
	    	}
	    	});
	    popup.add(del);
///////增加标签
		JLabel addtaglabel=new JLabel("添加标签");   
		addtaglabel.setBounds(610,260,100,30);
        add(addtaglabel);
		JLabel addtagclass=new JLabel("标签类名：");   
		addtagclass.setBounds(530,290,100,30);
        add(addtagclass);
        JTextField tct = new JTextField();
        tct.setBounds(600, 290, 200, 30);
        add(tct);
		JLabel addtag=new JLabel("标签名：");   
		addtag.setBounds(530,330,100,30);
        add(addtag);
        JTextField tt = new JTextField();
        tt.setBounds(600, 330, 200, 30);
        add(tt);
		Button addbutton= new Button();
		addbutton.setLabel("确认");
		addbutton.setBounds(600,370,80,30);
		add(addbutton);
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
	        }
	    } 
	});
////标签类下拉栏事件
	tclist.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	        // 选取标签类后在下方列表显示此标签类的标签
	    	if(e.getStateChange()==ItemEvent.SELECTED){
	    		selectedtagcla=(String) e.getItem();
	        	alltmodel.clear();
	            for(int i=0;i<TagDeal.getAllTag(tags, selectedtagcla).size();i++){
	            	alltmodel.addElement(TagDeal.getAllTag(tags, selectedtagcla).get(i).getTagName());
	            } 
	    	}
	    }
	});
////标签列表事件
	taglist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// 左键点击选取标签标注此评论
	        	if(selectedID!=-1)
	        	//如果已经选取了某一评论
	        	{
	        		try {
						TagDeal.addID(tags,(String)selectedtagcla,(String)taglist.getSelectedValue(), selectedID);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		tmodel.clear();
	        		for(int i=0;i<TagDeal.getTaged(tags, selectedID).size();i++){
		            	tmodel.addElement(TagDeal.getTaged(tags, selectedID).get(i));
		            } 
	        	}
	        }
	        if (SwingUtilities.isRightMouseButton(e)) {
	        	selectedtag=(String)taglist.getSelectedValue();
	        	popup.show(e.getComponent(),e.getX(),e.getY());
	        }
	    }
	});
////增加标签事件
	addbutton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if( !tct.getText().trim().equals("")&& !tt.getText().trim().equals("")) {
				int flag=0;
				ArrayList tc=new ArrayList();
				tc=TagDeal.getAllTagClass(tags);
				for(int i=0;i<tc.size();i++) {
					//若存在此标签类,flag置1,无需修改标签类下拉栏
					if(tc.get(i).equals(tct.getText())) {
						flag=1;
						ArrayList<Tag> t=new ArrayList<Tag>();
						t=TagDeal.getAllTag(tags,tct.getText());
						for(int j=0;j<t.size();j++) {
							//若存在此标签,flag置2
							if(t.get(j).getTagName().equals(tt.getText())) {
								flag=2;
								// 提示此标签已存在
								JOptionPane.showMessageDialog(null, "此标签已存在!");
							}
						}
					}
				}
				if(flag==0) {
					//若未存在此标签类,在标签类下拉栏中添加此标签类
					tclist.addItem(tct.getText());
					try {
						TagDeal.createTags(tags,tct.getText(),tt.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(flag==1) {
					try {
						TagDeal.createTags(tags,tct.getText(),tt.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 	
			}
			else
				JOptionPane.showMessageDialog(null, "标签类或标签未填写!");
		}
	});
	}
}