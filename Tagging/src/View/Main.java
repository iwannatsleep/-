package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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

public class Main extends JFrame {

	private JPanel contentPane;
	private DefaultListModel model;
    private JList list;	
    private JMenu Menu;
    private JMenuItem MenuItem_1,MenuItem_2,MenuItem_3,MenuItem_4;
    JFrame frame=new JFrame("�ڰ������ݱ�����");
	/**
	 * Create the frame.
	 */
	public Main() {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Icons/icon.jpg")));
		//setTitle("\u7B2C\u516B\u7EC4\u6570\u636E\u6807\u8BB0\u8F6F\u4EF6");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 500);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		Menu=new JMenu();
		Menu.setText("\u5BFC\u5165");
		Menu.setForeground(Color.BLACK);
		menuBar.add(Menu);
		
		MenuItem_1 = new JMenuItem();
		MenuItem_1.setText("\u4ECE\u672C\u5730\u6587\u4EF6\u5BFC\u5165");
		Menu.add(MenuItem_1);
	    //ΪMenuItem_1����¼�������
		MenuItem_1.addActionListener(new MenuItemAction_1());
		
		MenuItem_2 = new JMenuItem();
		MenuItem_2.setText("\u4E0B\u8F7D\u80A1\u7968");
		Menu.add(MenuItem_2);
		//ΪMenuItem_2����¼�������
		MenuItem_2.addActionListener(new MenuItemAction_2());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		//��Ʊ�б�
		model = new DefaultListModel();
        list = new JList(model);
		contentPane.add(list, BorderLayout.WEST);
		list.setPreferredSize(new Dimension(130, 300));
		Vector name=new Vector();
	    name.addElement("����ę́SH:600519");
		//model.addElement("SH:600519");
		list.setListData(name);
		
		//��ʾ�������۱�ע��ͳ��ͼ
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);	
		MenuItem_3 = new JMenuItem("\u8BC4\u8BBA\u6807\u6CE8");
		panel.add(MenuItem_3);
		MenuItem_3.setBounds(172, 10, 115, 27);
		//ΪMenuItem_3����¼�������
		MenuItem_3.addActionListener(new MenuItemAction_3());
		
		MenuItem_4 = new JMenuItem("\u7EDF\u8BA1\u56FE");
		panel.add(MenuItem_4);
		MenuItem_4.setBounds(449, 10, 115, 27);
		//ΪMenuItem_4����¼�������
		MenuItem_4.addActionListener(new MenuItemAction_4());
		
		//������ʾ��Ϣ
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("��δ�����κι�������");	
		textPane_1.setBackground(SystemColor.control);
		textPane_1.setBounds(308, 266, 137, 33);
		panel.add(textPane_1);
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("�������Ͻǵ��밴ť��ʼ����");	
		textPane_2.setBackground(SystemColor.control);
		textPane_2.setBounds(288, 294, 175, 33);
		panel.add(textPane_2);
		// ����ͼƬ
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/Icons/unhappy.jpg"));
		JLabel labelImage = new JLabel(imageIcon);
		panel.add(labelImage);
		labelImage.setBounds(323, 150, 101,90);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	//MenuItem_1����Ӧ�¼�
	class MenuItemAction_1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// �����ļ�ѡ����
			new SelectDoc();   
		}
	}
	
	//MenuItem_2����Ӧ�¼�
	class MenuItemAction_2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// ���ù�Ʊ���غ���
			new Download();
		}
	}
	
	//MenuItem_3����Ӧ�¼�
	class MenuItemAction_3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ת�����۱�ע����
			new Tag();
			// �ر�ԭ���Ľ���
			frame.dispose();
		}
	}
	
	//MenuItem_4����Ӧ�¼�
	class MenuItemAction_4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ת��ͳ��ͼ����
			new Chart();
			//�ر�ԭ���Ľ���
			frame.dispose();
		}
	}		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Main();
	}
}
