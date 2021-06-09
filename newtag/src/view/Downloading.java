package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import crawler.Crawler;
import data.Comment;
import data.Tag;
import control.CommentsDatabank;
import control.TagDeal;

import javax.swing.JSplitPane;

public class Downloading extends JFrame {

	private JPanel contentPane;
	public Crawler crawler;
	private String symbol="SH600519";
	private int page = 200;
	JFrame frame=new JFrame("下载中");
	Thread newThd;
	Process proc;
	Timer timer = new Timer();
	JProgressBar pro;
	JButton Button_cancel;
	JLabel lblNewLabel;
	
	public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {this.symbol = symbol;}
    
	/**
	 * Create the frame.
	 */
	public Downloading() {
	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 150, 300, 150);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
				
		lblNewLabel = new JLabel("下载中");
		lblNewLabel.setBounds(102, 23, 80, 29);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		Button_cancel = new JButton("取消");
		Button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proc.destroy();
				crawler=new Crawler(symbol,page);
				try {
					crawler.load();
				} catch (IOException e3) {
					// TODO 自动生成的 catch 块
					e3.printStackTrace();
				}
				CommentsDatabank comments_databank=new CommentsDatabank();
				ArrayList<Comment> comments_list=crawler.getCommentslist();
				if(comments_list.size()==0) {comments_list.add(new Comment(1,"无数据","SH000000",false,false));}
				comments_databank.setCommentslist(comments_list);
				try {
					comments_databank.saveComments(comments_list,"Comment.data");
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
					TagDeal.saveTags(tags_list,"Tag.txt");
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
				frame.dispose();
			}
		});
		Button_cancel.setBounds(170, 62, 93, 23);
		contentPane.add(Button_cancel, BorderLayout.SOUTH);
		
		pro=new JProgressBar();
        pro.setStringPainted(true);//信息被显示
        pro.setBounds(10, 70, 146, 11);
        contentPane.add(pro);
        Craw progress=new Craw();//线程对象
        progress.start();//启动线程
		ProBar progress_bar=new ProBar();//线程对象
        progress_bar.start();//启动线程
		
		frame.setVisible(true);//窗口可见
	}
	
	class Craw extends Thread {
        // TODO Auto-generated method stub
    	
		int i=0;
    	int position_comment=0;
        String filename = "data_comments.csv"; //py爬虫后评论存在这个文件里，java项目从该文件中读取评论
        String python_code;
        @Override
        public void run() {
        try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        python_code = "crawler.exe" + " "+symbol+" "+page; //构造执行python爬虫代码的命令
        //执行爬虫py文件进行爬取，爬取后的结果放在data_test_plus.csv
        try {
			proc = Runtime.getRuntime().exec(python_code);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        try {
			proc.waitFor();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
    }
	}
	
	class ProBar extends Thread{//根据始末文件大小实现下载进度条变化
        @Override
        public void run() {
        	int size=0;
        	lblNewLabel.setText("初始化下载程序……");
        	try {
				Thread.sleep(7000);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
        	lblNewLabel.setText("下载中……");
            for (int i=0;i<=100000;i++){
            	File file = new File("data_comments.csv");
            	size=(int) file.length();
            	pro.setValue((int) size/(2*1024));//让进度不断变化
            	if((int) size/(2*1024)>100) {
            		Button_cancel.setText("进入标注");
            		lblNewLabel.setText("已完成");
            	}
            	//pro.setValue(i);//让进度不断变化
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }  
	
	
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		new Downloading();
	}
}
