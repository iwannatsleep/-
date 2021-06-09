package view;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.*;
import javax.swing.*;

import data.Comment;
import data.Logs;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import java.awt.event.*;
public class MergeDoc implements ActionListener{
    int[][] idchangelist;
	JFrame frame=new JFrame("ѡ���ļ�");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label4 = new JLabel("Comment�ļ���");
    JLabel label5 = new JLabel("Tag�ļ���");
    JTextField text4 = new JTextField();
    JTextField text5 = new JTextField();
    JButton button4 = new JButton("...");
    JButton button5 = new JButton("...");
    JButton btnOKButton = new JButton("ȷ��");
    JFileChooser jfc1=new JFileChooser();//�ļ�ѡ����
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list1=new ArrayList<Comment>();
    ArrayList<Comment> comments_list2=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list1=new ArrayList<Tag>();
    ArrayList<Tag> tags_list2=new ArrayList<Tag>();
    Logs logwrite=new Logs();
    String selecteditem="�����ϴκϲ�";
    MergeDoc(){
        jfc1.setCurrentDirectory(new File("."));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊ��Ŀ��Ŀ¼
        /*
        //����������ȡ����Ļ�ĸ߶ȺͿ��
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ��
        */
        frame.setBounds(150, 150, 473, 339);//�趨���ڴ�Сλ��
        frame.setContentPane(tabPane);
        label4.setBounds(10, 95, 100, 20);
        label5.setBounds(10, 149, 100, 20);
        text4.setBounds(124, 95, 120, 20);
        text4.setText("Comment.data");
        text5.setBounds(124, 149, 120, 20);
        text5.setText("Tag.txt");
        button4.setBounds(243, 95, 50, 20);
        button5.setBounds(243, 149, 50, 20);
        btnOKButton.setBounds(334, 228, 97, 23);
        button4.addActionListener(this);//����¼�����
        button5.addActionListener(this);//����¼�����
        con.setBackground(Color.WHITE);con.add(label4);con.add(label5);con.add(text4);con.add(text5);
        con.add(button4);
        con.add(button5);
        con.add(btnOKButton);
        con.add(jfc1);
        tabPane.add("Ŀ¼/�ļ�ѡ��",con);  
////������
        JLabel chooselabel=new JLabel("ѡ�����:");    //������ǩ
        chooselabel.setBounds(10,40,100,20);
        String[] combobox= new String[2];
        combobox[0]="�����ϴκϲ�";
        combobox[1]="��ʼ���µĺϲ���Ŀ";
        JComboBox<String> chooselist=new JComboBox<String>(combobox);    //����JComboBox
        chooselist.setBounds(124,40,120,20);
        con.add(chooselabel);
        con.add(chooselist); 
////��������Ӧ
        chooselist.addItemListener(new ItemListener() {
    	    public void itemStateChanged(ItemEvent e) {
    	        // ѡȡ��ǩ������·��б���ʾ�˱�ǩ��ı�ǩ
    	    	if(e.getStateChange()==ItemEvent.SELECTED){
    	    		selecteditem=(String) e.getItem();
    	    	}
    	    }
    	});
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = 0;
        		String log="�ϲ�������۱�ע�ļ�����:"+ " " + selecteditem + " " + text4.getText() + " " + text5.getText();
        		try {
					logwrite.writeLog(log);
				} catch (IOException e3) {
					// TODO �Զ����ɵ� catch ��
					e3.printStackTrace();
				}
        		if(selecteditem.equals("�����ϴκϲ�")) {
	        		try {
	        			comments_list1=commentsdatabank.readComments("CommentMerge.data");
	        			comments_list2=commentsdatabank.readComments(text4.getText());
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
	        		
	        		if(comments_list1.get(0).getSymbol().equals(comments_list2.get(0).getSymbol())) {
						try {
							tags_list1=tagdeal.readTags("TagMerge.txt");
							tags_list2=tagdeal.readTags(text5.getText());
						} catch (IOException e2) {
							// TODO �Զ����ɵ� catch ��
							e2.printStackTrace();
						}
						idchangelist=commentsdatabank.idChangeList(comments_list1, comments_list2);
						tagdeal.tagClassMerge(tags_list1, tags_list2);
						while(idchangelist[i][0]!=0) {
							tagdeal.tagsMerge(tags_list1, tags_list2, idchangelist[i][0], idchangelist[i][1],"TagMerge.txt");
							i++;
						}
						for(Comment comment:comments_list1) {
							if(TagDeal.isConflict(tags_list1, comment.getId())) {
			        			comment.setIsconflict(true);
			        		}
						}
		        		try {
							commentsdatabank.saveComments(comments_list1,"CommentMerge.data");
						} catch (IOException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						};
						try {
							tagdeal.saveTags(tags_list1,"TagMerge.txt");
						} catch (IOException e2) {
							// TODO �Զ����ɵ� catch ��
							e2.printStackTrace();
						}
						try {
							new MainView2();
						} catch (IOException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
						frame.dispose();
	        		}
	        		else {JOptionPane.showMessageDialog(null, "���������������ͬһ֧��Ʊ��������ѡ��");}
        		}
        		else if(selecteditem.equals("��ʼ���µĺϲ���Ŀ")) {
        			try {
						comments_list1=commentsdatabank.readComments(text4.getText());
						tags_list1=tagdeal.readTags(text5.getText());
	        			commentsdatabank.saveComments(comments_list1,"CommentMerge.data");
	        			tagdeal.saveTags(tags_list1,"TagMerge.txt");
	        			new MainView2();
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
        			frame.dispose();
        		}
        		
        	}
        });
        frame.setVisible(true);//���ڿɼ�
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ʹ�ܹرմ��ڣ���������
    }
    
    public void actionPerformed(ActionEvent e){//�¼�����
    	/*
        if(e.getSource().equals(button1)){//�жϴ��������İ�ť���ĸ�
            jfc.setFileSelectionMode(1);//�趨ֻ��ѡ���ļ���
            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc.getSelectedFile();//fΪѡ�񵽵�Ŀ¼
                text1.setText(f.getAbsolutePath());
            }
        }
        */

        if(e.getSource().equals(button4)){
        	jfc1.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ�
            int state=jfc1.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc1.getSelectedFile();//fΪѡ�񵽵��ļ�
                text4.setText(f.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button5)){
        	jfc1.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ�
            int state=jfc1.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc1.getSelectedFile();//fΪѡ�񵽵��ļ�
                text5.setText(f.getAbsolutePath());
            }
        }
    }
    public static void main(String[] args) {
        new MergeDoc();
    }
}