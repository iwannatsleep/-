package view;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import data.Comment;
import data.Logs;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import java.awt.event.*;
public class ExportDoc implements ActionListener{
    JFrame frame=new JFrame("选择路径");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    JLabel label2=new JLabel("选择导出目录:");
    JTextField text2=new JTextField();
    JButton button2=new JButton("...");
    JButton btnOKButton = new JButton("确定");
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list=new ArrayList<Tag>();
    Logs logwrite=new Logs();
    ExportDoc(){

        frame.setBounds(150, 150, 430, 220);//设定窗口大小位置
        frame.setContentPane(tabPane);
        label2.setBounds(10,38,100,20);
        text2.setBounds(101,38,120,20);
        text2.setText("Comment.data");
        button2.setBounds(220,38,50,20);
        btnOKButton.setBounds(287, 121, 97, 23);
        button2.addActionListener(this);
        con.setBackground(Color.WHITE);
        con.add(label2);
        con.add(text2);
        con.add(button2);
        con.add(btnOKButton);
        tabPane.add("目录选择",con);
////下拉栏
        JLabel commentschooselabel=new JLabel("选择数据:");    //创建标签
        commentschooselabel.setBounds(10,88,100,20);
        String[] commentscombobox= new String[2];
        commentscombobox[0]="全部评论";
        commentscombobox[1]="未标注评论";
        JComboBox<String> commentschooselist=new JComboBox<String>(commentscombobox);    //创建JComboBox
        commentschooselist.setBounds(101,88,120,20);
        commentschooselist.setModel(new DefaultComboBoxModel<String>(commentscombobox));
        con.add(commentschooselabel);
        con.add(commentschooselist); 
        
        btnOKButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Calendar nowtime = new GregorianCalendar();
        		String time=nowtime.get(Calendar.YEAR)+"_"+
        				nowtime.get(Calendar.MONTH)+"_" +
        				nowtime.get(Calendar.DATE)+"_" +
        				nowtime.get(Calendar.HOUR)+"_" +
        				nowtime.get(Calendar.MINUTE)+"_" +
        				nowtime.get(Calendar.SECOND)+"_" ;
        		String path=text2.getText();
        		String commentfilename=path+"\\"+"Comment_"+time+".data";
        		String tagfilename=path+"\\"+"Tag_"+time+".txt";
        		//comments_list=commentsdatabank.readComments(tagfilename);
				frame.dispose();
        	}
        });
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//使能关闭窗口，结束程序
    }
    public void actionPerformed(ActionEvent e){//事件处理
        if(e.getSource().equals(button2)){
        	int result = 0;
        	File file = null;
        	String path = null;
        	JFileChooser fileChooser = new JFileChooser();
        	FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
        	System.out.println(fsv.getHomeDirectory());                //得到桌面路径
        	fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        	fileChooser.setDialogTitle("请选择要上传的文件...");
        	fileChooser.setApproveButtonText("确定");
        	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	result = fileChooser.showOpenDialog(null);
        	if (JFileChooser.APPROVE_OPTION == result) {
        			path=fileChooser.getSelectedFile().getPath();
        			text2.setText(path);
        	   }
        }
    }
    public static void main(String[] args) {
        new ExportDoc();
    }
}