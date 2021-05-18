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
////////��������
		comments_list=CommentsDatabank.readComments("Comment.data");
		tags=TagDeal.readTags();
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
		Vector cData = new Vector();
		Vector cName = new Vector();
		cName.add("ID");
		cName.add("����");
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
                                            return false;//��������༭
                      }
            }; 
		JScrollPane cscroll = new JScrollPane(commentslist);
		cscroll.setBounds(10, 50,300, 350);
		add(cscroll);
		setLayout(null);
////////��ʾ��������
		JLabel tlabel=new JLabel("���ݣ�");   
        tlabel.setBounds(320,50,200,30);
        add(tlabel);
		JTextPane txt = new JTextPane();
		JScrollPane txtscroll = new JScrollPane(txt);
		txtscroll.setBackground(SystemColor.inactiveCaptionBorder);
		txtscroll.setBounds(320, 80, 200, 145);
		add(txtscroll);

////////�ѱ���ע��ǩ��
		JLabel tdlabel=new JLabel("�ѱ�ע��ǩ��");    
        tdlabel.setBounds(320,225,200,30);
        add(tdlabel);
		DefaultListModel tmodel = new DefaultListModel();
		tagedlist=new JList(tmodel);
		tagedlist = new JList(tmodel);
		JScrollPane tagedscroll = new JScrollPane(tagedlist);
		tagedscroll.setBounds(320,255,200,145);
		add(tagedscroll);
		
////////��ǩ��������
        JLabel tclabel=new JLabel("��ǩ��:");  
        tclabel.setBounds(530,50,50,20);
        JComboBox tclist=new JComboBox();    //����JComboBox
        tclist.setBounds(600,50,200,20);
        for(int i=0;i<TagDeal.getAllTagClass(tags).size();i++)
        	tclist.addItem(TagDeal.getAllTagClass(tags).get(i));    //���ǩ�������б������һ��
        add(tclabel);
        add(tclist);
////////��ǩ��
		DefaultListModel alltmodel = new DefaultListModel();
		taglist=new JList(alltmodel);
		JScrollPane tagscroll = new JScrollPane(taglist);
		for(int i=0;i<TagDeal.getAllTag(tags,(String)tclist.getSelectedItem()).size();i++)
			alltmodel.addElement(TagDeal.getAllTag(tags,(String)tclist.getSelectedItem()).get(i).getTagName());    //���ǩ���б������һ��
		tagscroll.setBounds(600,100,200,150);
		add(tagscroll);
///////�Ҽ�ɾ���˵�
		JPopupMenu popup=new JPopupMenu();
	    JMenuItem del=new JMenuItem("ɾ��");
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
	    			//�˴���Ҫ����tclist
	    			tclist.remove(tclist.getSelectedIndex());
	    		}
	    	}
	    	});
	    popup.add(del);
///////���ӱ�ǩ
		JLabel addtaglabel=new JLabel("��ӱ�ǩ");   
		addtaglabel.setBounds(610,260,100,30);
        add(addtaglabel);
		JLabel addtagclass=new JLabel("��ǩ������");   
		addtagclass.setBounds(530,290,100,30);
        add(addtagclass);
        JTextField tct = new JTextField();
        tct.setBounds(600, 290, 200, 30);
        add(tct);
		JLabel addtag=new JLabel("��ǩ����");   
		addtag.setBounds(530,330,100,30);
        add(addtag);
        JTextField tt = new JTextField();
        tt.setBounds(600, 330, 200, 30);
        add(tt);
		Button addbutton= new Button();
		addbutton.setLabel("ȷ��");
		addbutton.setBounds(600,370,80,30);
		add(addbutton);
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
	        }
	    } 
	});
////��ǩ���������¼�
	tclist.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	        // ѡȡ��ǩ������·��б���ʾ�˱�ǩ��ı�ǩ
	    	if(e.getStateChange()==ItemEvent.SELECTED){
	    		selectedtagcla=(String) e.getItem();
	        	alltmodel.clear();
	            for(int i=0;i<TagDeal.getAllTag(tags, selectedtagcla).size();i++){
	            	alltmodel.addElement(TagDeal.getAllTag(tags, selectedtagcla).get(i).getTagName());
	            } 
	    	}
	    }
	});
////��ǩ�б��¼�
	taglist.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        if (SwingUtilities.isLeftMouseButton(e)) {
	        	// ������ѡȡ��ǩ��ע������
	        	if(selectedID!=-1)
	        	//����Ѿ�ѡȡ��ĳһ����
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
////���ӱ�ǩ�¼�
	addbutton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if( !tct.getText().trim().equals("")&& !tt.getText().trim().equals("")) {
				int flag=0;
				ArrayList tc=new ArrayList();
				tc=TagDeal.getAllTagClass(tags);
				for(int i=0;i<tc.size();i++) {
					//�����ڴ˱�ǩ��,flag��1,�����޸ı�ǩ��������
					if(tc.get(i).equals(tct.getText())) {
						flag=1;
						ArrayList<Tag> t=new ArrayList<Tag>();
						t=TagDeal.getAllTag(tags,tct.getText());
						for(int j=0;j<t.size();j++) {
							//�����ڴ˱�ǩ,flag��2
							if(t.get(j).getTagName().equals(tt.getText())) {
								flag=2;
								// ��ʾ�˱�ǩ�Ѵ���
								JOptionPane.showMessageDialog(null, "�˱�ǩ�Ѵ���!");
							}
						}
					}
				}
				if(flag==0) {
					//��δ���ڴ˱�ǩ��,�ڱ�ǩ������������Ӵ˱�ǩ��
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
				JOptionPane.showMessageDialog(null, "��ǩ����ǩδ��д!");
		}
	});
	}
}