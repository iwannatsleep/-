package view;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class DefultView extends  JPanel{
	DefultView(){
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//������ʾ��Ϣ
		JTextPane textPane= new JTextPane();
		textPane.setText("��δ�����κι�������,�������Ͻǵ��밴ť��ʼ����");	
		textPane.setBackground(SystemColor.control);
		add("Center",textPane);
		//textPane_2.setBounds(288, 294, 175, 33);
		// ����ͼƬ
		/*
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/Icons/unhappy.jpg"));
		JLabel labelImage = new JLabel(imageIcon);
		content.add(labelImage);
		labelImage.setBounds(323, 150, 101,90);
		*/
		
	}
}