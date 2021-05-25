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
////////��������
		comments_list=commentsdatabank.readComments("Comment.data");
		tags=TagDeal.readTags("Tag.txt");
		if(tags.get(0).haveID(1)) {
			String temp=tags.get(0).getTagClass()+"."+tags.get(0).getTagName();
		}
		//System.out.print(tags.get(0).getTagClass());
		//System.out.print(tags.get(0).getTagName());
		//System.out.print(tags.get(0).getID());
////////���۱�
        JLabel clabel=new JLabel(comments_list.get(0).getSymbol());    //������ǩ
        clabel.setBounds(10,10,200,30);
        add(clabel);
        cData = new Vector();
		cName = new Vector();
		cName.add("ID");
		cName.add("����");
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
        		return false;//��������༭
        	}
        }; 
		JScrollPane cscroll = new JScrollPane(commentslist);
		cscroll.setBounds(10, 50, 300, 350);
		add(cscroll);
		setLayout(null);
////////��ʾ��������
		JLabel tlabel=new JLabel("���ݣ�");   
        tlabel.setBounds(330,10,200,30);
        add(tlabel);
		JTextPane txt = new JTextPane();
		JScrollPane txtscroll = new JScrollPane(txt);
		txtscroll.setBackground(SystemColor.inactiveCaptionBorder);
		txtscroll.setBounds(330, 50, 250, 350);
		add(txtscroll);

////////�ѱ���ע��ǩ��
		JLabel tdlabel=new JLabel("�ѱ�ע��ǩ��");    
        tdlabel.setBounds(600,10,200,30);
        add(tdlabel);
		DefaultListModel tmodel = new DefaultListModel();
		tagedlist=new JList(tmodel);
		tagedlist = new JList(tmodel);
		JScrollPane tagedscroll = new JScrollPane(tagedlist);
		tagedscroll.setBounds(600, 50, 250, 350);
		add(tagedscroll);


////////ɸѡ����������
        JLabel commentschooselabel=new JLabel("ɸѡ����:");    //������ǩ
        commentschooselabel.setBounds(80,20,60,20);
        String[] commentscombobox= new String[99];
        commentscombobox[0]="ȫ������";
        commentscombobox[1]="δ��ע����";
        commentscombobox[2]="�ѱ�ע����";
        commentscombobox[3]="��ע�г�ͻ����";
        JComboBox<String> commentschooselist=new JComboBox<String>(commentscombobox);    //����JComboBox
        commentschooselist.setBounds(170,20,150,20);
        tagschooselist=TagDeal.getAllTagandClass(tags);
        for(int i=0;i<tagschooselist.size();i++)
        	commentscombobox[i+4]=tagschooselist.get(i);    //�������б������һ��
        commentschooselist.setModel(new DefaultComboBoxModel<String>(commentscombobox));
        add(commentschooselabel);
        add(commentschooselist);



///////���۱��¼�
	    commentslist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// ������ѡȡ���۽��б�ע
	            int row = commentslist.getSelectedRow();
	            selectedID=(int) commentslist.getValueAt(row, 0);
	            txt.setText((String) commentslist.getValueAt(row, 1));//��ʾ��������
	            tmodel.clear();
	            for(int i=0;i<TagDeal.getTaged(tags,selectedID).size();i++) {
	            	tmodel.addElement(TagDeal.getTaged(tags,selectedID).get(i));
	            }
	        }
	        else if (SwingUtilities.isRightMouseButton(e)) {
	        	//�Ҽ��������ɾ��
	        	int row_delete = commentslist.getSelectedRow();
	        	deleteID=(int) commentslist.getValueAt(row_delete, 0);
	        	commentsdatabank.DeleteComment(comments_list,deleteID);
	        	try {
					TagDeal.delAllID(tags, deleteID);
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
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
	
////ɸѡ�����������¼�
	commentschooselist.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	        // ѡȡ��ǩ������·��б���ʾ�˱�ǩ��ı�ǩ
	    	if(e.getStateChange()==ItemEvent.SELECTED){
	    		selectedcommentscla=(String) e.getItem();
	    		comments_show_list=comments_list;
	        	if(selectedcommentscla.equals("ȫ������")) {
	        		comments_show_list=comments_list;
	        	}
	        	else if(selectedcommentscla.equals("δ��ע����")) {
	        		comments_show_list=commentsdatabank.unTagedComments(comments_list); 
	        	}
	        	else if(selectedcommentscla.equals("�ѱ�ע����")) {
	        		comments_show_list=commentsdatabank.TagedComments(comments_list);
	        	}
	        	else if(selectedcommentscla.equals("��ע�г�ͻ����")) {
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

////ɾ��������ǩ
	tagedlist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// �Ҽ����ѡȡ��ǩ��ע������
	        	if(selectedID!=-1)
	        	//����Ѿ�ѡȡ��ĳһ����
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
	        	//�Ҽ�����޸Ĵ˱�ǩ
	        }
	    }
	});
	}
}