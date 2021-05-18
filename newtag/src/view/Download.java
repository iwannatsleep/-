package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import crawler.Crawler;
import data.Comment;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import javax.swing.JSplitPane;

public class Download extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public Crawler crawler;
	JFrame frame=new JFrame("下载股票");

	/**
	 * Create the frame.
	 */
	public Download() {
	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 150, 430, 220);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("输入股票代码");
		lblNewLabel.setBounds(60, 30, 80, 29);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setBounds(100, 70, 180, 21);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.setVisible(true);
		
		JButton Button_ok = new JButton("确定");
		Button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击确定的响应
				String symbol=textField.getText();
				int page=10;
				crawler=new Crawler(symbol,page);
				try {
					crawler.craw();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				CommentsDatabank comments_databank=new CommentsDatabank();
				ArrayList<Comment> comments_list=crawler.getCommentslist();
				comments_databank.setCommentslist(comments_list);
				try {
					comments_databank.saveComments(comments_list);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				TagDeal tagdeal=new TagDeal();
				ArrayList<Tag> tags_list=new ArrayList<Tag>();
				ArrayList a=new ArrayList();a.add(0);
				tags_list.add(new Tag("认为涨跌","看涨",a));
				tags_list.add(new Tag("认为涨跌","看跌",a));
				tags_list.add(new Tag("认为涨跌","中立",a));
				tags_list.add(new Tag("是否广告","是",a));
				tags_list.add(new Tag("是否广告","否",a));
				try {
					TagDeal.saveTags(tags_list);
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
			}
		});
		Button_ok.setBounds(230, 130, 93, 23);
		contentPane.add(Button_ok, BorderLayout.SOUTH);
		
		JButton Button_cancel = new JButton("取消");
		Button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Button_cancel.setBounds(90, 130, 93, 23);
		contentPane.add(Button_cancel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("输入格式，如：SH600519");
		lblNewLabel_1.setBounds(100, 95, 180, 21);
		contentPane.add(lblNewLabel_1);
		
		frame.setVisible(true);//窗口可见
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Download();
	}
}
