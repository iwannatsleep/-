package View;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import View.Main.MenuItemAction_3;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chart extends JFrame {

	private JPanel contentPane;
	private DefaultListModel model;
	private JList list;
	private JMenu Menu;
	private JMenuItem MenuItem_1, MenuItem_2, MenuItem_3, MenuItem_4;
	JFrame frame = new JFrame("第八组数据标记软件");

	/**
	 * Create the frame.
	 */
	public Chart() {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Icons/icon.jpg")));
		// setTitle("\u7B2C\u516B\u7EC4\u6570\u636E\u6807\u8BB0\u8F6F\u4EF6");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 500);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		Menu = new JMenu();
		Menu.setText("\u5BFC\u5165");
		Menu.setForeground(Color.BLACK);
		menuBar.add(Menu);

		MenuItem_1 = new JMenuItem();
		MenuItem_1.setText("\u4ECE\u672C\u5730\u6587\u4EF6\u5BFC\u5165");
		Menu.add(MenuItem_1);
		// 为MenuItem_1添加事件监听器
		MenuItem_1.addActionListener(new MenuItemAction_1());

		MenuItem_2 = new JMenuItem();
		MenuItem_2.setText("\u4E0B\u8F7D\u80A1\u7968");
		Menu.add(MenuItem_2);
		// 为MenuItem_2添加事件监听器
		MenuItem_2.addActionListener(new MenuItemAction_2());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		// 股票列表
		model = new DefaultListModel();
		list = new JList(model);
		contentPane.add(list, BorderLayout.WEST);
		list.setPreferredSize(new Dimension(130, 300));
		Vector name = new Vector();
		name.addElement("贵州茅台SH:600519");
		// model.addElement("SH:12345");
		list.setListData(name);

		// 显示区：评论标注，统计图
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		MenuItem_3 = new JMenuItem("\u8BC4\u8BBA\u6807\u6CE8");
		panel.add(MenuItem_3);
		MenuItem_3.setBounds(172, 10, 115, 27);
		// 为MenuItem_3添加事件监听器
		MenuItem_3.addActionListener(new MenuItemAction_3());

		MenuItem_4 = new JMenuItem("\u7EDF\u8BA1\u56FE");
		panel.add(MenuItem_4);
		MenuItem_4.setBounds(449, 10, 115, 27);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(37, 94, 462, 286);
		panel.add(panel_1);

		JTextPane txtname = new JTextPane();
		txtname.setBackground(SystemColor.inactiveCaptionBorder);
		txtname.setText("贵州茅台SH:600519\r");
		txtname.setBounds(37, 47, 137, 27);
		panel.add(txtname);

		// 第一类标签区
		JTextPane text_1 = new JTextPane();
		text_1.setBackground(SystemColor.inactiveCaptionBorder);
		text_1.setBounds(541, 94, 178, 27);
		text_1.setText(" > 对股票的看法");
		panel.add(text_1);
		// text_1的响应事件
		text_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon picture = new ImageIcon(Chart.class.getResource("/Icons/unhappy.jpg"));
				JLabel labelpic = new JLabel(picture);
				panel_1.add(labelpic);
				labelpic.setBounds(300, 100, 120,120);
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.text);
		panel_3.setBounds(541, 121, 178, 90);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 180, 90);
		panel_3.add(scrollPane_1);

		String[] element = { "看跌", "看涨", "无关", "表达", "分析", "最新" };
		JList list_1 = new JList(element);
		scrollPane_1.setViewportView(list_1);

		ImageIcon addIcon = new ImageIcon(Chart.class.getResource("/Icons/unhappy.jpg"));

		// 第二类标签区
		JTextPane text_2 = new JTextPane();
		text_2.setBackground(SystemColor.inactiveCaptionBorder);
		text_2.setBounds(541, 211, 178, 27);
		text_2.setText(" > 表达观点或分析数据");
		panel.add(text_2);
		// text_2的响应事件
		text_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//
				ImageIcon picture = new ImageIcon(Chart.class.getResource("/Icons/unhappy.jpg"));
				JLabel labelpic = new JLabel(picture);
				panel_1.add(labelpic);
				labelpic.setBounds(200, 100, 120,120);
			}
		});

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.text);
		panel_4.setBounds(541, 238, 178, 72);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 180, 70);
		panel_4.add(scrollPane_2);

		JList list_2 = new JList(element);
		scrollPane_2.setViewportView(list_2);

		// 第三类标签区
		JTextPane text_3 = new JTextPane();
		text_3.setBackground(SystemColor.inactiveCaptionBorder);
		text_3.setBounds(541, 310, 178, 27);
		text_3.setText(" > 其他");
		panel.add(text_3);// text_1的响应事件
		text_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//
				ImageIcon picture = new ImageIcon(Chart.class.getResource("/Icons/unhappy.jpg"));
				JLabel labelpic = new JLabel(picture);
				panel_1.add(labelpic);
				labelpic.setBounds(100, 100, 120,120);
			}
		});

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// MenuItem_1的响应事件
	class MenuItemAction_1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 调用文件选择函数
			new SelectDoc();
		}
	}

	// MenuItem_2的响应事件
	class MenuItemAction_2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 调用股票下载函数
			new Download();
		}
	}

	// MenuItem_3的响应事件
	class MenuItemAction_3 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 跳转到评论标注界面
			new Tag();
			frame.dispose();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Chart();
	}
}
