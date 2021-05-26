package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import data.Logs;
import view.Main;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;



public class MainView extends JFrame {

	public JPanel contentpanel,tagpanel,chartpanel;
	private DefaultListModel model;
    private JList list;	
    JFrame frame=new JFrame("第八组数据标记软件");
	public MainView() throws IOException {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Icons/icon.jpg")));
		frame.setBounds(100, 100, 900,500);

		
////////菜单栏部分		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//菜单栏导入选项
		JMenu Import=new JMenu();
		Import.setText("导入");
		Import.setForeground(Color.BLACK);
		menuBar.add(Import);
		//菜单栏下拉内容项,分别为"从本地文件导入"和"下载股票"
		JMenuItem ImportItem_1 = new JMenuItem();
		ImportItem_1.setText("从本地文件导入");
		Import.add(ImportItem_1);
		ImportItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用文件选择函数
				new SelectDoc();
				frame.dispose();
			}
		});
		
		JMenuItem ImportItem_2 = new JMenuItem();
		ImportItem_2.setText("下载股票");
		Import.add(ImportItem_2);
		ImportItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用下载函数
				new Download();
				frame.dispose();
			}
		});
		
		JMenuItem ImportItem_3 = new JMenuItem();
		ImportItem_3.setText("多标注文件合并");
		Import.add(ImportItem_3);
		ImportItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用下载函数
				new MergeDoc();
				frame.dispose();
			}
		});
		
		//菜单栏视图选项
		JMenu View=new JMenu();
		View.setText("视图");
		View.setForeground(Color.BLACK);
		menuBar.add(View);
		JMenuItem ViewItem_1 = new JMenuItem();
		ViewItem_1 .setText("评论标注");
		View.add(ViewItem_1);
		ViewItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用查看统计图函数
				frame.remove(chartpanel);
				frame.add(tagpanel);
						//contentpanel=new TagView();
						//frame.add(contentpanel);
						//frame.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});
		JMenuItem ViewItem_2 = new JMenuItem();
		ViewItem_2 .setText("统计图");
		View.add(ViewItem_2);
		ViewItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 调用查看统计图函数
				frame.remove(tagpanel);
				try {
					chartpanel=new ChartView();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.add(chartpanel);
						//contentpanel=new TagView();
						//frame.add(contentpanel);
						//frame.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});

////////		
	
////////主视图对象
		//contentpanel=new JPanel();
		tagpanel=new TagView();
		//chartpanel=new ChartView();
		//contentpanel.add(tagpanel);
		frame.add(tagpanel);
		
		
		
		/*	// 插入图片
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/Icons/unhappy.jpg"));
		JLabel labelImage = new JLabel(imageIcon);
		contentPane.add(labelImage);
		labelImage.setBounds(323, 150, 101,90);

		
		//显示区：评论标注，统计图
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);	
		MenuItem_3 = new JMenuItem("\u8BC4\u8BBA\u6807\u6CE8");
		panel.add(MenuItem_3);
		MenuItem_3.setBounds(172, 10, 115, 27);
		
		MenuItem_4 = new JMenuItem("\u7EDF\u8BA1\u56FE");
		panel.add(MenuItem_4);
		MenuItem_4.setBounds(449, 10, 115, 27);
*/
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}