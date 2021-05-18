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
	      JFreeChart chart = ChartFactory.createPieChart3D("各标签比例",data,true,false,false);
	    //设置百分比
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
	      NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
	      pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	  
	  //没有数据的时候显示的内容
	      pieplot.setNoDataMessage("无数据显示");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	      chartp=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码
	      chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
	}
	//根据传入的数据生成统计图
   public static DefaultPieDataset getDataSet(String[] tag,int[] number) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        //若未传入标签类,则不显示
        if (tag.length==0) {
        	return dataset;
        }
        //若传入标签类,则显示由此标签类中所有标签生成的统计图
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
