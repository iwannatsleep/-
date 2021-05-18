package View;

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

import xhr.group8.crawler.Crawler;
import xhr.group8.data.Comment;
import xhr.group8.databank.CommentsDatabank;

import javax.swing.JSplitPane;

public class Download extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public Crawler crawler;
	JFrame frame=new JFrame("���ع�Ʊ");

	/**
	 * Create the frame.
	 */
	public Download() {
	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 150, 430, 220);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("�����Ʊ����");
		lblNewLabel.setBounds(60, 30, 80, 29);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setBounds(100, 70, 180, 21);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.setVisible(true);
		
		JButton Button_ok = new JButton("ȷ��");
		Button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���ȷ������Ӧ
				String symbol=textField.getText();
				int page=30;
				crawler=new Crawler(symbol,page);
				try {
					crawler.craw();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				CommentsDatabank comments_databank=new CommentsDatabank();
				ArrayList<Comment> comments_list=crawler.getCommentslist();
				comments_databank.setCommentslist(comments_list);
				try {
					comments_databank.saveComments(comments_list);
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				Tag a=new Tag();
			}
		});
		Button_ok.setBounds(230, 130, 93, 23);
		contentPane.add(Button_ok, BorderLayout.SOUTH);
		
		JButton Button_cancel = new JButton("ȡ��");
		Button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Button_cancel.setBounds(90, 130, 93, 23);
		contentPane.add(Button_cancel, BorderLayout.SOUTH);
		
		frame.setVisible(true);//���ڿɼ�
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Download();
	}

}