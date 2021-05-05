package View;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SelectDoc implements ActionListener{
    JFrame frame=new JFrame("选择文件");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    JLabel label2=new JLabel("选择文件");
    JTextField text2=new JTextField();
    JButton button2=new JButton("...");
    JFileChooser jfc=new JFileChooser();//文件选择器
    SelectDoc(){
        jfc.setCurrentDirectory(new File("d:\\"));//文件选择器的初始目录定为d盘
        /*
        //下面两行是取得屏幕的高度和宽度
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置
        */
        frame.setBounds(150, 150, 430, 220);//设定窗口大小位置
        frame.setContentPane(tabPane);
        label2.setBounds(10,56,100,20);
        text2.setBounds(102,56,120,20);
        button2.setBounds(221,56,50,20);
        button2.addActionListener(this);//添加事件处理
        con.setBackground(Color.WHITE);
        con.add(label2);
        con.add(text2);
        con.add(button2);
        con.add(jfc);
        tabPane.add("目录/文件选择",con);
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
    }
    public static void main(String[] args) {
        new SelectDoc();
    }
}