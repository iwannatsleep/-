
package View;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.*;
 
public class test extends JFrame implements ListSelectionListener {
    JTextField jtf;
    JList<String> jlist1;
    JTextArea jta;
    static final String[] CP = { "�ཷ����˿", "������������", "����С��Ϻ", "������˿", "��������", "�����ϻ��", "����", "���򻨲�" };
 
    public test() {
        JPanel jpCenter = new JPanel(new GridLayout(1, 2));
        jlist1 = new JList<String>(CP);
        jlist1.addListSelectionListener(this);
        jlist1.addFocusListener(new FocusListener() {//��JList���1��������Ӧ�¼�
 
            @Override
            public void focusLost(FocusEvent e) {//ʧȥ����ʱִ�еķ���
                jlist1.setSelectedIndices(new int[] {});//����ѡ�����ĿΪû��. ��1���հ������ʾ
                //jlist1.setSelectedIndices(new int[] {0,1});//��ͱ�ʾѡ�����ĿΪ��1�͵�2��
            }
 
            @Override
            public void focusGained(FocusEvent e) {//��ý���ʱ,ִ�еķ���
                // TODO Auto-generated method stub
                 
            }
        });
        JScrollPane jsp1 = new JScrollPane(jlist1);// �������
        jsp1.setBorder(BorderFactory.createTitledBorder("ȫ����Ʒ"));// ������ı߿�
        jpCenter.add(jsp1);
 
        jta = new JTextArea(5, 30);
        jta.setLineWrap(true);
        JScrollPane jsp2 = new JScrollPane(jta);
        jsp2.setBorder(BorderFactory.createTitledBorder("��ѡ��Ʒ"));
        jpCenter.add(jsp2);
 
        add(jpCenter);
        setTitle("���");
        setSize(300, 200);// ���ڴ�С
        setLocationRelativeTo(null);// ���ھ���
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new test();// ��������
    }
 
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            jta.append(jlist1.getSelectedValue()+",");
        }
    }
}