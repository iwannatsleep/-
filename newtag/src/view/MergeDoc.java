package view;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import data.Comment;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import java.awt.event.*;
public class MergeDoc implements ActionListener{
    int[][] idchangelist;
	JFrame frame=new JFrame("ѡ���ļ�");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label2=new JLabel("��עԱ1��Comment");
    JLabel label3=new JLabel("��עԱ1��Tag");
    JLabel label4 = new JLabel("��עԱ2��Comment");
    JLabel label5 = new JLabel("��עԱ2��Tag");
    JTextField text2 = new JTextField();
    JTextField text3 = new JTextField();
    JTextField text4 = new JTextField();
    JTextField text5 = new JTextField();
    JButton button2 = new JButton("...");
    JButton button3 = new JButton("...");
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
        label2.setBounds(10,38,100,20);
        label3.setBounds(10, 92, 100, 20);
        label4.setBounds(10, 144, 100, 20);
        label5.setBounds(10, 198, 100, 20);
        text2.setBounds(124,38,120,20);
        text2.setText("Comment.data");
        text3.setBounds(124, 92, 120, 20);
        text3.setText("Tag.txt");
        text4.setBounds(124, 144, 120, 20);
        text4.setText("Comment.data");
        text5.setBounds(124, 198, 120, 20);
        text5.setText("Tag.txt");
        button2.setBounds(243,38,50,20);
        button3.setBounds(243, 92, 50, 20);
        button4.setBounds(243, 144, 50, 20);
        button5.setBounds(243, 198, 50, 20);
        btnOKButton.setBounds(334, 228, 97, 23);
        button2.addActionListener(this);//����¼�����
        button3.addActionListener(this);//����¼�����
        button4.addActionListener(this);//����¼�����
        button5.addActionListener(this);//����¼�����
        con.setBackground(Color.WHITE);
        con.add(label2);con.add(label3);con.add(label4);con.add(label5);
        con.add(text2);con.add(text3);con.add(text4);con.add(text5);
        con.add(button2);
        con.add(button3);
        con.add(button4);
        con.add(button5);
        con.add(btnOKButton);
        con.add(jfc1);
        tabPane.add("Ŀ¼/�ļ�ѡ��",con);
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = 0;
        		try {
        			comments_list1=commentsdatabank.readComments(text2.getText());
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
        		try {
        			comments_list2=commentsdatabank.readComments(text4.getText());
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
        		if(comments_list1.get(0).getSymbol().equals(comments_list2.get(0).getSymbol())) {
					try {
						tags_list1=tagdeal.readTags(text3.getText());
					} catch (IOException e2) {
						// TODO �Զ����ɵ� catch ��
						e2.printStackTrace();
					}
					try {
						tags_list2=tagdeal.readTags(text5.getText());
					} catch (IOException e2) {
						// TODO �Զ����ɵ� catch ��
						e2.printStackTrace();
					}
					idchangelist=commentsdatabank.IdChangeList(comments_list1, comments_list2);
					tagdeal.TagClassMerge(tags_list1, tags_list2);
					while(idchangelist[i][0]!=0) {
						tagdeal.TagsMerge(tags_list1, tags_list2, idchangelist[i][0], idchangelist[i][1]);
						i++;
					}
					for(Comment comment:comments_list1) {
						if(TagDeal.IsConflict(tags_list1, comment.getId())) {
		        			comment.setIsconflict(true);
		        		}
					}
	        		try {
						commentsdatabank.saveComments(comments_list1);
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					};
					try {
						tagdeal.saveTags(tags_list1);
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
        		else {JOptionPane.showMessageDialog(null, "����������������ͬ��Ʊ��������ѡ��");}
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
        if(e.getSource().equals(button2)){
            jfc1.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ�
            int state=jfc1.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc1.getSelectedFile();//fΪѡ�񵽵��ļ�
                text2.setText(f.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button3)){
        	jfc1.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ�
            int state=jfc1.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc1.getSelectedFile();//fΪѡ�񵽵��ļ�
                text3.setText(f.getAbsolutePath());
            }
        }
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