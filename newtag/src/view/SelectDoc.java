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
public class SelectDoc implements ActionListener{
    JFrame frame=new JFrame("ѡ���ļ�");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label2=new JLabel("ѡ��Comment");
    JLabel label3=new JLabel("ѡ��Tag");
    JTextField text2=new JTextField();
    JTextField text3=new JTextField();
    JButton button2=new JButton("...");
    JButton button3=new JButton("...");
    JButton btnOKButton = new JButton("ȷ��");
    JFileChooser jfc=new JFileChooser();//�ļ�ѡ����
    JFileChooser jfc1=new JFileChooser();//�ļ�ѡ����
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list=new ArrayList<Tag>();
    SelectDoc(){
        jfc.setCurrentDirectory(new File("."));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��
        jfc1.setCurrentDirectory(new File("."));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��
        /*
        //����������ȡ����Ļ�ĸ߶ȺͿ��
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ��
        */
        frame.setBounds(150, 150, 430, 220);//�趨���ڴ�Сλ��
        frame.setContentPane(tabPane);
        label2.setBounds(10,38,100,20);
        label3.setBounds(10, 92, 100, 20);
        text2.setBounds(101,38,120,20);
        text2.setText("Comment.data");
        text3.setBounds(101, 92, 120, 20);
        text3.setText("Tag.txt");
        button2.setBounds(220,38,50,20);
        button3.setBounds(220, 92, 50, 20);
        btnOKButton.setBounds(287, 121, 97, 23);
        button2.addActionListener(this);//����¼�����
        button3.addActionListener(this);//����¼�����
        con.setBackground(Color.WHITE);
        con.add(label2);
        con.add(label3);
        con.add(text2);
        con.add(text3);
        con.add(button2);
        con.add(button3);
        con.add(btnOKButton);
        con.add(jfc);
        con.add(jfc1);
        tabPane.add("Ŀ¼/�ļ�ѡ��",con);
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			comments_list=commentsdatabank.readComments(text2.getText());
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
        		try {
					commentsdatabank.saveComments(comments_list);
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				};
				try {
					tags_list=tagdeal.readTags(text3.getText());
				} catch (IOException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				try {
					tagdeal.saveTags(tags_list);
				} catch (IOException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				try {
					new MainView();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				frame.dispose();
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
            jfc.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ�
            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                File f=jfc.getSelectedFile();//fΪѡ�񵽵��ļ�
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
    }
    public static void main(String[] args) {
        new SelectDoc();
    }
}