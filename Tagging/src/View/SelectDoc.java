package View;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SelectDoc implements ActionListener{
    JFrame frame=new JFrame("ѡ���ļ�");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label2=new JLabel("ѡ���ļ�");
    JTextField text2=new JTextField();
    JButton button2=new JButton("...");
    JFileChooser jfc=new JFileChooser();//�ļ�ѡ����
    SelectDoc(){
        jfc.setCurrentDirectory(new File("d:\\"));//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��
        /*
        //����������ȡ����Ļ�ĸ߶ȺͿ��
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setTitle("\u9009\u62E9\u6587\u4EF6");
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ��
        */
        frame.setBounds(150, 150, 430, 220);//�趨���ڴ�Сλ��
        frame.setContentPane(tabPane);
        label2.setBounds(10,56,100,20);
        text2.setBounds(102,56,120,20);
        button2.setBounds(221,56,50,20);
        button2.addActionListener(this);//����¼�����
        con.setBackground(Color.WHITE);
        con.add(label2);
        con.add(text2);
        con.add(button2);
        con.add(jfc);
        tabPane.add("Ŀ¼/�ļ�ѡ��",con);
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
    }
    public static void main(String[] args) {
        new SelectDoc();
    }
}