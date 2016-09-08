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
				"ѡ�����ͳ��", 
				(PieDataset) dataset, 
				true, 
				true, 
				false); 
		  Font font = new Font("����", Font.BOLD, 16);  
		  TextTitle title = new TextTitle("ѡ�����ͳ��", font);  
		  chart.setTitle(title);//����  		
		//�Ӹ�������   
		//chart.addSubtitle(new TextTitle("ѡ��"));   
		PiePlot pieplot = (PiePlot) chart.getPlot();   
		pieplot.setLabelFont(new Font("����", 0, 11));   
		//���ñ�ͼ��Բ�ģ�true����������Բ�ģ�false����Ĭ��Ϊtrue   
		pieplot.setCircular(true);   
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator(
				"{0}:({1},{2})", 
				NumberFormat.getNumberInstance(), 
				NumberFormat.getPercentInstance());   
		pieplot.setLabelGenerator(standarPieIG);   
		  
		//û�����ݵ�ʱ����ʾ������   
		pieplot.setNoDataMessage("��������ʾ");   
		pieplot.setLabelGap(0.03D); 
		return chart;
	
	}
	

}
