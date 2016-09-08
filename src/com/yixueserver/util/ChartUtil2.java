package com.yixueserver.util;

import java.awt.Font;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartUtil2 {

	public static JFreeChart createChart2(DefaultPieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart(
				"选择题答案统计", 
				(PieDataset) dataset, 
				true, 
				true, 
				false); 
		  Font font = new Font("黑体", Font.BOLD, 16);  
		  TextTitle title = new TextTitle("选择题答案统计", font);  
		  chart.setTitle(title);//标题  		
		//加个副标题   
		//chart.addSubtitle(new TextTitle("选择"));   
		PiePlot pieplot = (PiePlot) chart.getPlot();   
		pieplot.setLabelFont(new Font("黑体", 0, 11));   
		//设置饼图是圆的（true），还是椭圆的（false）；默认为true   
		pieplot.setCircular(true);   
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator(
				"{0}:({1},{2})", 
				NumberFormat.getNumberInstance(), 
				NumberFormat.getPercentInstance());   
		pieplot.setLabelGenerator(standarPieIG);   
		  
		//没有数据的时候显示的内容   
		pieplot.setNoDataMessage("无数据显示");   
		pieplot.setLabelGap(0.03D); 
		return chart;
	
	}
	

}
