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
        JLabel tclabel=new JLabel("标签类:");  
        JComboBox tclist=new JComboBox();    //创建JComboBox
        tclist.addItem("选择标签类别");
        for(int i=0;i<TagDeal.getAllTagClass(tags).size();i++)
        	tclist.addItem(TagDeal.getAllTagClass(tags).get(i));    //向下拉列表中添加一项
        add(tclabel);
        add(tclist);
		chart = new CreateChart(null,null).getChartPanel();
		add(chart);
		tclist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// 选取标签类后显示由此标签类元素组成的统计图
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