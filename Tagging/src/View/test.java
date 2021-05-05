
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
    static final String[] CP = { "青椒土豆丝", "西红柿炒鸡蛋", "麻辣小龙虾", "鱼香肉丝", "西湖醋鱼", "红汤老火锅", "可乐", "茉莉花茶" };
 
    public test() {
        JPanel jpCenter = new JPanel(new GridLayout(1, 2));
        jlist1 = new JList<String>(CP);
        jlist1.addListSelectionListener(this);
        jlist1.addFocusListener(new FocusListener() {//给JList添加1个焦点响应事件
 
            @Override
            public void focusLost(FocusEvent e) {//失去焦点时执行的方法
                jlist1.setSelectedIndices(new int[] {});//设置选择的条目为没有. 用1个空白数组表示
                //jlist1.setSelectedIndices(new int[] {0,1});//这就表示选择的条目为第1和第2个
            }
 
            @Override
            public void focusGained(FocusEvent e) {//获得焦点时,执行的方法
                // TODO Auto-generated method stub
                 
            }
        });
        JScrollPane jsp1 = new JScrollPane(jlist1);// 滚动面板
        jsp1.setBorder(BorderFactory.createTitledBorder("全部菜品"));// 带标题的边框
        jpCenter.add(jsp1);
 
        jta = new JTextArea(5, 30);
        jta.setLineWrap(true);
        JScrollPane jsp2 = new JScrollPane(jta);
        jsp2.setBorder(BorderFactory.createTitledBorder("已选菜品"));
        jpCenter.add(jsp2);
 
        add(jpCenter);
        setTitle("点菜");
        setSize(300, 200);// 窗口大小
        setLocationRelativeTo(null);// 窗口居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new test();// 启动窗口
    }
 
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            jta.append(jlist1.getSelectedValue()+",");
        }
    }
}