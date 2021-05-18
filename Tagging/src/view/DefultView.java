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
		//文字提示信息
		JTextPane textPane= new JTextPane();
		textPane.setText("还未导入任何股评数据,请点击左上角导入按钮开始导入");	
		textPane.setBackground(SystemColor.control);
		add("Center",textPane);
		//textPane_2.setBounds(288, 294, 175, 33);
		// 插入图片
		/*
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/Icons/unhappy.jpg"));
		JLabel labelImage = new JLabel(imageIcon);
		content.add(labelImage);
		labelImage.setBounds(323, 150, 101,90);
		*/
		
	}
}