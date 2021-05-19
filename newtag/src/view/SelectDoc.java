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
    JFrame frame=new JFrame("选择文件");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    JLabel label2=new JLabel("选择Comment");
    JLabel label3=new JLabel("选择Tag");
    JTextField text2=new JTextField();
    JTextField text3=new JTextField();
    JButton button2=new JButton("...");
    JButton button3=new JButton("...");
    JButton btnOKButton = new JButton("确定");
    JFileChooser jfc=new JFileChooser();//文件选择器
    JFileChooser jfc1=new JFileChooser();//文件选择器
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list=new ArrayList<Tag>();
    SelectDoc(){
        jfc.setCurrentDirectory(new File("."));//文件选择器的初始目录定为d盘
        jfc1.setCurrentDirectory(new File("."));//文件选择器的初始目录定为d盘
        /*
        //下面两行是取得屏幕的高度和宽度
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置
        */
        frame.setBounds(150, 150, 430, 220);//设定窗口大小位置
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
        button2.addActionListener(this);//添加事件处理
        button3.addActionListener(this);//添加事件处理
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
        tabPane.add("目录/文件选择",con);
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			comments_list=commentsdatabank.readComments(text2.getText());
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
        		try {
					commentsdatabank.saveComments(comments_list);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				};
				try {
					tags_list=tagdeal.readTags(text3.getText());
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				try {
					tagdeal.saveTags(tags_list);
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				try {
					new MainView();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.dispose();
        	}
        });
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//使能关闭窗口，结束程序
    }
    public void actionPerformed(ActionEvent e){//事件处理
    	/*
        if(e.getSource().equals(button1)){//判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(1);//设定只能选择到文件夹
            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                File f=jfc.getSelectedFile();//f为选择到的目录
                text1.setText(f.getAbsolutePath());
            }
        }
        */
        if(e.getSource().equals(button2)){
            jfc.setFileSelectionMode(0);//设定只能选择到文件
            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                File f=jfc.getSelectedFile();//f为选择到的文件
                text2.setText(f.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button3)){
            jfc1.setFileSelectionMode(0);//设定只能选择到文件
            int state=jfc1.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                File f=jfc1.getSelectedFile();//f为选择到的文件
                text3.setText(f.getAbsolutePath());
            }
        }
    }
    public static void main(String[] args) {
        new SelectDoc();
    }
}