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
	JFrame frame=new JFrame("选择文件");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    JLabel label4 = new JLabel("Comment文件：");
    JLabel label5 = new JLabel("Tag文件：");
    JTextField text4 = new JTextField();
    JTextField text5 = new JTextField();
    JButton button4 = new JButton("...");
    JButton button5 = new JButton("...");
    JButton btnOKButton = new JButton("确定");
    JFileChooser jfc1=new JFileChooser();//文件选择器
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list1=new ArrayList<Comment>();
    ArrayList<Comment> comments_list2=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list1=new ArrayList<Tag>();
    ArrayList<Tag> tags_list2=new ArrayList<Tag>();
    Logs logwrite=new Logs();
    String selecteditem="继续上次合并";
    MergeDoc(){
        jfc1.setCurrentDirectory(new File("."));//文件选择器的初始目录定为项目根目录
        /*
        //下面两行是取得屏幕的高度和宽度
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置
        */
        frame.setBounds(150, 150, 473, 339);//设定窗口大小位置
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
        button4.addActionListener(this);//添加事件处理
        button5.addActionListener(this);//添加事件处理
        con.setBackground(Color.WHITE);con.add(label4);con.add(label5);con.add(text4);con.add(text5);
        con.add(button4);
        con.add(button5);
        con.add(btnOKButton);
        con.add(jfc1);
        tabPane.add("目录/文件选择",con);  
////下拉栏
        JLabel chooselabel=new JLabel("选择操作:");    //创建标签
        chooselabel.setBounds(10,40,100,20);
        String[] combobox= new String[2];
        combobox[0]="继续上次合并";
        combobox[1]="初始化新的合并项目";
        JComboBox<String> chooselist=new JComboBox<String>(combobox);    //创建JComboBox
        chooselist.setBounds(124,40,120,20);
        con.add(chooselabel);
        con.add(chooselist); 
////下拉栏响应
        chooselist.addItemListener(new ItemListener() {
    	    public void itemStateChanged(ItemEvent e) {
    	        // 选取标签类后在下方列表显示此标签类的标签
    	    	if(e.getStateChange()==ItemEvent.SELECTED){
    	    		selecteditem=(String) e.getItem();
    	    	}
    	    }
    	});
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = 0;
        		String log="合并多个评论标注文件数据:"+ " " + selecteditem + " " + text4.getText() + " " + text5.getText();
        		try {
					logwrite.writeLog(log);
				} catch (IOException e3) {
					// TODO 自动生成的 catch 块
					e3.printStackTrace();
				}
        		if(selecteditem.equals("继续上次合并")) {
	        		try {
	        			comments_list1=commentsdatabank.readComments("CommentMerge.data");
	        			comments_list2=commentsdatabank.readComments(text4.getText());
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	        		
	        		if(comments_list1.get(0).getSymbol().equals(comments_list2.get(0).getSymbol())) {
						try {
							tags_list1=tagdeal.readTags("TagMerge.txt");
							tags_list2=tagdeal.readTags(text5.getText());
						} catch (IOException e2) {
							// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						};
						try {
							tagdeal.saveTags(tags_list1,"TagMerge.txt");
						} catch (IOException e2) {
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
						try {
							new MainView2();
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						frame.dispose();
	        		}
	        		else {JOptionPane.showMessageDialog(null, "加入的数据需来自同一支股票，请重新选择！");}
        		}
        		else if(selecteditem.equals("初始化新的合并项目")) {
        			try {
						comments_list1=commentsdatabank.readComments(text4.getText());
						tags_list1=tagdeal.readTags(text5.getText());
	        			commentsdatabank.saveComments(comments_list1,"CommentMerge.data");
	        			tagdeal.saveTags(tags_list1,"TagMerge.txt");
	        			new MainView2();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
        			frame.dispose();
        		}
        		
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

        if(e.getSource().equals(button4)){
        	jfc1.setFileSelectionMode(0);//设定只能选择到文件
            int state=jfc1.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                File f=jfc1.getSelectedFile();//f为选择到的文件
                text4.setText(f.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button5)){
        	jfc1.setFileSelectionMode(0);//设定只能选择到文件
            int state=jfc1.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                File f=jfc1.getSelectedFile();//f为选择到的文件
                text5.setText(f.getAbsolutePath());
            }
        }
    }
    public static void main(String[] args) {
        new MergeDoc();
    }
}