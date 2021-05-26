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
    JFrame frame=new JFrame("ѡ��·��");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label2=new JLabel("ѡ�񵼳�Ŀ¼:");
    JTextField text2=new JTextField();
    JButton button2=new JButton("...");
    JButton btnOKButton = new JButton("ȷ��");
    CommentsDatabank commentsdatabank=new CommentsDatabank();
    ArrayList<Comment> comments_list=new ArrayList<Comment>();
    TagDeal tagdeal=new TagDeal();
    ArrayList<Tag> tags_list=new ArrayList<Tag>();
    Logs logwrite=new Logs();
    ExportDoc(){

        frame.setBounds(150, 150, 430, 220);//�趨���ڴ�Сλ��
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
        tabPane.add("Ŀ¼ѡ��",con);
////������
        JLabel commentschooselabel=new JLabel("ѡ������:");    //������ǩ
        commentschooselabel.setBounds(10,88,100,20);
        String[] commentscombobox= new String[2];
        commentscombobox[0]="ȫ������";
        commentscombobox[1]="δ��ע����";
        JComboBox<String> commentschooselist=new JComboBox<String>(commentscombobox);    //����JComboBox
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
        frame.setVisible(true);//���ڿɼ�
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ʹ�ܹرմ��ڣ���������
    }
    public void actionPerformed(ActionEvent e){//�¼�����
        if(e.getSource().equals(button2)){
        	int result = 0;
        	File file = null;
        	String path = null;
        	JFileChooser fileChooser = new JFileChooser();
        	FileSystemView fsv = FileSystemView.getFileSystemView();  //ע���ˣ�������Ҫ��һ��
        	System.out.println(fsv.getHomeDirectory());                //�õ�����·��
        	fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        	fileChooser.setDialogTitle("��ѡ��Ҫ�ϴ����ļ�...");
        	fileChooser.setApproveButtonText("ȷ��");
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