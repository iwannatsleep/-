package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddTag extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JFrame frame=new JFrame("��ӱ�ǩ");

	/**
	 * Create the frame.
	 */
	public AddTag() {
	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(360, 250, 300, 150);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(66, 23, 159, 21);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.setVisible(true);
		
		JButton Button_ok = new JButton("����");
		Button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���ȷ������Ӧ
			}
		});
		Button_ok.setBounds(158, 63, 67, 21);
		contentPane.add(Button_ok, BorderLayout.SOUTH);
		
		JButton Button_cancel = new JButton("ȡ��");
		Button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Button_cancel.setBounds(66, 63, 67, 21);
		contentPane.add(Button_cancel, BorderLayout.SOUTH);
		
		frame.setVisible(true);//���ڿɼ�
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new AddTag();
	}

}
