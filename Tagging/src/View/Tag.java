package View;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import View.Main.MenuItemAction_3;
import xhr.group8.data.Comment;
import xhr.group8.databank.CommentsDatabank;
import zzx.group8.data.Tags;

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
import java.io.IOException;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.*;

public class Tag extends JFrame {

	private JPanel contentPane, panel, panel_1, panel_2, panel_3, panel_4, panel_5, panel_6;
	private DefaultListModel model;
	private JList list, list_1, list_2;
	private JMenu Menu;
	private JMenuItem MenuItem_1, MenuItem_2, MenuItem_3, MenuItem_4;
	JTextArea selected;
	JScrollPane scrollPane_1, scrollPane_2;
	public static JPanel panel_tag, panel_chart;
	
	private ArrayList<Comment> comments_list;
	ArrayList<String> comments=new ArrayList<String>();
	private int i=0;
	private CommentsDatabank commentsdatabank=new CommentsDatabank();
	
	private ArrayList<Tags> tags =new ArrayList<Tags>();

	JFrame frame = new JFrame("第八组数据标记软件");
	
	public ArrayList<Comment> getCommentslist() {
        return comments_list;
    }
    public void setCommentslist(ArrayList<Comment> comments_list) {this.comments_list=comments_list;}

	/**
	 * Create the frame.
	 */
	public Tag() {
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
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JList list_test = new JList();
		list_test.setBackground(SystemColor.text);
		panel.add(list_test);
		try {
			comments_list=commentsdatabank.readComments("Comment.data");
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		for(i=0;i<comments_list.size();i++) {
			comments.add(i, comments_list.get(i).getText());
		}
		list_test.setListData(comments.toArray());
		JScrollPane jsp=new JScrollPane(list_test);
		jsp.setBounds(37, 155, 463, 255);
		panel.add(jsp);
		panel.setLayout(null);
		
		MenuItem_3 = new JMenuItem("\u8BC4\u8BBA\u6807\u6CE8");
		panel.add(MenuItem_3);
		MenuItem_3.setBounds(172, 10, 115, 27);

		MenuItem_4 = new JMenuItem("\u7EDF\u8BA1\u56FE");
		panel.add(MenuItem_4);
		MenuItem_4.setBounds(449, 10, 115, 27);
		// 为MenuItem_4添加事件监听器
		MenuItem_4.addActionListener(new MenuItemAction_4());

		JTextPane txtname = new JTextPane();
		txtname.setBackground(SystemColor.inactiveCaptionBorder);
		txtname.setText("贵州茅台SH:600519\r");
		txtname.setBounds(37, 47, 137, 27);
		panel.add(txtname);

		panel_tag = new JPanel();
		panel_tag.setBounds(10, 76, 736, 354);
		panel.add(panel_tag);
		panel_tag.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(26, 20, 462, 51);
		panel_1.setLayout(null);
		panel_tag.add(panel_1);

		
		
		/*panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
		panel_2.setBounds(26, 89, 462, 243);
		panel_tag.add(panel_2);*/
		
		// 第一类标签区
		JTextPane text_1 = new JTextPane();
		text_1.setBackground(SystemColor.inactiveCaptionBorder);
		text_1.setBounds(527, 20, 178, 27);
		text_1.setText(" > 对股票的看法");
		panel_tag.add(text_1);

		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.text);
		panel_3.setBounds(527, 47, 178, 90);
		panel_tag.add(panel_3);
		panel_3.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 180, 60);
		panel_3.add(scrollPane_1);

		//String[] element = { "看跌", "看涨", "无关", "表达", "分析", "最新" };

		DefaultListModel model_1 = new DefaultListModel();
		//tags.add(0,new Tags("对股票的看法","看跌",new List()));
		
		ArrayList<String> alist1 = new ArrayList<String>();
		alist1.add("看跌");
		alist1.add("看涨");
		alist1.add("无关");
		alist1.add("表达");
		alist1.add("分析");
		alist1.add("最新");
		list_1 = new JList(model_1);
		list_1.setListData(alist1.toArray());
		scrollPane_1.setViewportView(list_1);

		// 选择标签进行标注
		list_1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					selected.append(list_1.getSelectedValue() + ",");
				}
			}
		});

		// 右键删除标签
		list_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				// 返回最接近列表的坐标系统中给定位置的单元索引
				int selectedindex = list_1.locationToIndex(evt.getPoint());
				// 这样右键点击也会选中一项
				list_1.setSelectedIndex(selectedindex);
				if (SwingUtilities.isRightMouseButton(evt)) {
					// 处理右键点击
					if (selectedindex != -1) {
						Object[] options = { "删除", "修改" }; // 自定义按钮上的文字
						int n = JOptionPane.showOptionDialog(null, "选择需要进行的操作", "选择操作", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if (n == JOptionPane.YES_OPTION) {
							// 删除标签
							alist1.remove(selectedindex);
							list_1.setListData(alist1.toArray());
						} else if (n == JOptionPane.NO_OPTION) {
							// 修改标签
							String edit = JOptionPane.showInputDialog(null, " 修改为：\n", "修改标签",
									JOptionPane.PLAIN_MESSAGE);
							if (edit == null)
								return;
							else {
								alist1.set(selectedindex, edit);
								list_1.setListData(alist1.toArray());
							}
						}
					}
				}
			}
		});

		selected = new JTextArea();
		selected.setBounds(10, 10, 426, 31);
		panel_1.add(selected);

		ImageIcon addIcon = new ImageIcon(Tag.class.getResource("/Icons/add.jpg"));
		JLabel addtag_1 = new JLabel(addIcon);
		panel_3.add(addtag_1);
		addtag_1.setBounds(0, 60, 30, 30);
		addtag_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String append = JOptionPane.showInputDialog(null, " 请输入要添加的标签：\n", "添加标签",
						JOptionPane.PLAIN_MESSAGE);
				if (append != null) {
					alist1.add(append);
					list_1.setListData(alist1.toArray());
				}
			}
		});

		// 第二类标签区
		JTextPane text_2 = new JTextPane();
		text_2.setBackground(SystemColor.inactiveCaptionBorder);
		text_2.setBounds(527, 136, 178, 27);
		text_2.setText(" > 表达观点或分析数据");
		panel_tag.add(text_2);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.text);
		panel_4.setBounds(527, 163, 178, 72);
		panel_tag.add(panel_4);
		panel_4.setLayout(null);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 180, 45);
		panel_4.add(scrollPane_2);

		DefaultListModel model_2 = new DefaultListModel();
		ArrayList<String> alist2 = new ArrayList<String>();
		alist2.add("看跌");
		alist2.add("看涨");
		alist2.add("无关");
		alist2.add("表达");
		alist2.add("分析");
		alist2.add("最新");
		list_2 = new JList(model_1);
		list_2.setListData(alist2.toArray());
		scrollPane_2.setViewportView(list_2);

		// 选择标签进行标注
		list_2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					selected.append(list_2.getSelectedValue() + ",");
				}
			}
		});

		// 右键删除标签
		list_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				// 返回最接近列表的坐标系统中给定位置的单元索引
				int selectedindex = list_2.locationToIndex(evt.getPoint());
				// 这样右键点击也会选中一项
				list_2.setSelectedIndex(selectedindex);
				if (SwingUtilities.isRightMouseButton(evt)) {
					// 处理右键点击
					if (selectedindex != -1) {
						Object[] options = { "删除", "修改" }; // 自定义按钮上的文字
						int n = JOptionPane.showOptionDialog(null, "选择需要进行的操作", "选择操作", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if (n == JOptionPane.YES_OPTION) {
							// 删除标签
							alist2.remove(selectedindex);
							list_2.setListData(alist2.toArray());
						} else if (n == JOptionPane.NO_OPTION) {
							// 修改标签
							String edit = JOptionPane.showInputDialog(null, " 修改为：\n", "修改标签",
									JOptionPane.PLAIN_MESSAGE);
							if (edit == null)
								return;
							else {
								alist2.set(selectedindex, edit);
								list_2.setListData(alist2.toArray());
							}
						}
					}
				}
			}
		});

		JLabel addtag_2 = new JLabel(addIcon);
		panel_4.add(addtag_2);
		addtag_2.setBounds(0, 44, 30, 30);
		addtag_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String append = JOptionPane.showInputDialog(null, " 请输入要添加的标签：\n", "添加标签",
						JOptionPane.PLAIN_MESSAGE);
				if (append != null) {
					alist2.add(append);
					list_2.setListData(alist2.toArray());
				}
			}
		});

		// 第三类标签区
		JTextPane text_3 = new JTextPane();
		text_3.setBackground(SystemColor.inactiveCaptionBorder);
		text_3.setBounds(527, 234, 178, 27);
		text_3.setText(" > 其他");
		panel_tag.add(text_3);

		panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.inactiveCaptionBorder);
		panel_6.setBounds(527, 261, 178, 30);
		panel_tag.add(panel_6);
		panel_6.setLayout(null);
		JLabel addclass = new JLabel(addIcon);
		panel_6.add(addclass);
		addclass.setBounds(0, 0, 30, 30);
		addclass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddClass();
			}
		});

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

	private String getSource(String string) {
		// TODO Auto-generated method stub
		return null;
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

	// MenuItem_4的响应事件
	class MenuItemAction_4 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 跳转到统计图界面
			new Chart();
			frame.dispose();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Tag();
	}
}