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

	public JPanel contentpanel,tagpanel,chartpanel,tagpanel2;
	private DefaultListModel model;
    private JList list;	
    int flag=0;
    JFrame frame=new JFrame("�ڰ������ݱ�����");
	public MainView() throws IOException {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Icons/icon.jpg")));
		frame.setBounds(100, 100, 900,500);

		
////////�˵�������		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//�˵�������ѡ��
		JMenu Import=new JMenu();
		Import.setText("����");
		Import.setForeground(Color.BLACK);
		menuBar.add(Import);
		//�˵�������������,�ֱ�Ϊ"�ӱ����ļ�����"��"���ع�Ʊ"
		JMenuItem ImportItem_1 = new JMenuItem();
		ImportItem_1.setText("�ӱ����ļ�����");
		Import.add(ImportItem_1);
		ImportItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �����ļ�ѡ����
				new SelectDoc();
				frame.dispose();
			}
		});
		
		JMenuItem ImportItem_2 = new JMenuItem();
		ImportItem_2.setText("���ع�Ʊ");
		Import.add(ImportItem_2);
		ImportItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������غ���
				new Download();
				frame.dispose();
			}
		});
		
		JMenuItem ImportItem_3 = new JMenuItem();
		ImportItem_3.setText("���ע�ļ��ϲ�");
		Import.add(ImportItem_3);
		ImportItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������غ���
				new MergeDoc();
				frame.dispose();
			}
		});
		
		JMenuItem ImportItem_4 = new JMenuItem();
		ImportItem_4.setText("���ݵ���");
		Import.add(ImportItem_4);
		ImportItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������غ���
				new ExportDoc();
			}
		});
		
		//�˵�����ͼѡ��
		JMenu View=new JMenu();
		View.setText("��ͼ");
		View.setForeground(Color.BLACK);
		menuBar.add(View);
		JMenuItem ViewItem_1 = new JMenuItem();
		ViewItem_1 .setText("���۱�ע");
		View.add(ViewItem_1);
		ViewItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �������۱�ע����
				if(flag==2) {frame.remove(chartpanel);}
				if(flag==3) {frame.remove(tagpanel2);}
				try {
					tagpanel=new TagView();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				frame.add(tagpanel);
				flag=1;
						//contentpanel=new TagView();
						//frame.add(contentpanel);
						//frame.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});
		JMenuItem ViewItem_2 = new JMenuItem();
		ViewItem_2 .setText("ͳ��ͼ");
		View.add(ViewItem_2);
		ViewItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ò鿴ͳ��ͼ����
				if(flag==1) {frame.remove(tagpanel);}
				if(flag==3) {frame.remove(tagpanel2);}
				try {
					chartpanel=new ChartView();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				frame.add(chartpanel);
				flag=2;
						//contentpanel=new TagView();
						//frame.add(contentpanel);
						//frame.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});
		JMenuItem ViewItem_3 = new JMenuItem();
		ViewItem_3 .setText("�ϲ�ҳ��");
		View.add(ViewItem_3);
		ViewItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ò鿴ͳ��ͼ����
				if(flag==1) {frame.remove(tagpanel);}
				if(flag==2) {frame.remove(chartpanel);}
				try {
					tagpanel2=new TagView2();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				frame.add(tagpanel2);
				flag=3;
						//contentpanel=new TagView();
						//frame.add(contentpanel);
						//frame.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});

////////		
	
////////����ͼ����
		//contentpanel=new JPanel();
		tagpanel=new TagView();
		//chartpanel=new ChartView();
		//contentpanel.add(tagpanel);
		frame.add(tagpanel);
		flag=1;
		
		
		
		/*	// ����ͼƬ
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/Icons/unhappy.jpg"));
		JLabel labelImage = new JLabel(imageIcon);
		contentPane.add(labelImage);
		labelImage.setBounds(323, 150, 101,90);

		
		//��ʾ�������۱�ע��ͳ��ͼ
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