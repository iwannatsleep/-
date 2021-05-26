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
import data.Logs;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import javax.swing.JSplitPane;

public class Download extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public Crawler crawler;
	JFrame frame=new JFrame("下载股票");
	Logs logwrite=new Logs();

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
				if(IsInputRight(symbol)) {
					Downloading test=new Downloading();
					test.setSymbol(symbol);
					frame.dispose();
					String log="下载股评数据:"+ " " + symbol;
	        		try {
						logwrite.writeLog(log);
					} catch (IOException e3) {
						// TODO 自动生成的 catch 块
						e3.printStackTrace();
					}
					}
				else textField.setText("输入有误，请重新输入！");

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
	
	private boolean IsInputRight(String symbol) {
		
		String[] falseStr=new String[] {":",";","\"","'",",",".","/","<",">","?","[","]","{","}","\\","|","-","=","+","_"};
		boolean state1=symbol.contains("SH");
		boolean state2=symbol.contains("SZ");
		boolean state3=symbol.contains("HK");
		boolean state4=true;
		for(int i=0;i<falseStr.length;i++) {
			if(symbol.contains(falseStr[i])) {
				state4=false;
				break;
			}
		}
		int length=symbol.length();
		if((state1 || state2 || state3) && state4 && (length==8 || length==7)) {return true;}
		return false;
	}
	
	public boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		new Download();
	}
}
