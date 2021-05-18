package control;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class CreateChart extends  JPanel{
	JPanel chartp;
	public CreateChart(String[] tag,int[] number){
		  DefaultPieDataset data=new DefaultPieDataset();;
	      if (tag!=null) {
	          for(int i=0;i<number.length;i++) {
	              data.setValue(tag[i],number[i]);
	          }
	      }
	      JFreeChart chart = ChartFactory.createPieChart3D("����ǩ����",data,true,false,false);
	    //���ðٷֱ�
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	      NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	      pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	  
	  //û�����ݵ�ʱ����ʾ������
	      pieplot.setNoDataMessage("��������ʾ");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	      pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	      chartp=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
	      PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	      piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
	      chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
	}
	//���ݴ������������ͳ��ͼ
   public static DefaultPieDataset getDataSet(String[] tag,int[] number) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        //��δ�����ǩ��,����ʾ
        if (tag.length==0) {
        	return dataset;
        }
        //�������ǩ��,����ʾ�ɴ˱�ǩ�������б�ǩ���ɵ�ͳ��ͼ
       for(int i=0;i<number.length;i++) {
            //dataset.setValue("element",CommentToTag.getCommentnum(element));
            dataset.setValue(tag[i],number[i]);     
        }
		return dataset;
}
    public JPanel getChartPanel(){
    	return chartp;
    }
}
