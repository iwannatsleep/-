package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.CreateChart;
import control.TagDeal;
import data.Tag;

public class ChartView extends  JPanel{
	ArrayList<Tag> tags;
	JPanel chart;
	ChartView() throws IOException{
		tags=TagDeal.readTags();
        JLabel tclabel=new JLabel("��ǩ��:");  
        JComboBox tclist=new JComboBox();    //����JComboBox
        tclist.addItem("ѡ���ǩ���");
        for(int i=0;i<TagDeal.getAllTagClass(tags).size();i++)
        	tclist.addItem(TagDeal.getAllTagClass(tags).get(i));    //�������б������һ��
        add(tclabel);
        add(tclist);
		chart = new CreateChart(null,null).getChartPanel();
		add(chart);
		tclist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// ѡȡ��ǩ�����ʾ�ɴ˱�ǩ��Ԫ����ɵ�ͳ��ͼ
				if(e.getStateChange()==ItemEvent.SELECTED){
					String selectedtagcla=(String) e.getItem();
					int tagnum = TagDeal.getAllTag(tags, selectedtagcla).size();
					String[] stag=new String[tagnum];
					int[] number=new int[tagnum];
					for(int i=0;i<tagnum;i++){
						stag[i]=TagDeal.getAllTag(tags, selectedtagcla).get(i).getTagName();
						number[i]=TagDeal.getAllTag(tags, selectedtagcla).get(i).getIDnumber();
					}
					remove(chart);
					chart=new CreateChart(stag,number).getChartPanel();
					add(chart);
					revalidate();
					repaint();
				}
			}
		});
	}
}