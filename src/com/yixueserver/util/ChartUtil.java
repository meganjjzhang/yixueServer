package com.yixueserver.util;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class ChartUtil {

	public static JFreeChart createChart(DefaultCategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				  "ѡ�����ͳ��", 
				  "ѡ��", 
				  "ѡ������",  
				  dataset, 
				  PlotOrientation.VERTICAL, 
				  true, 
				  true, 
				  false);  

				  Font font = new Font("����", Font.BOLD, 16);  
				  TextTitle title = new TextTitle("ѡ�����ͳ��", font);  
				  chart.setTitle(title);//����  
				  CategoryPlot plot = chart.getCategoryPlot();  
				  NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
				  CategoryAxis domainAxis = plot.getDomainAxis();  
				  //����X�������ϵ�����  
				  domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
				  //����X��ı�������  
				  domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
				  //����Y�������ϵ�����  
				  numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
				  //����Y��ı�������  
				  numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
				  //����ײ��������������  
				  chart.getLegend().setItemFont(new Font("����", Font.PLAIN,  12)); //�ɵײ����ֲżӣ���Ȼ�����  
				  // ����Ϊ��������  
				  //����Ϊ��������  
				  //�������񱳾���ɫ  
				  plot.setBackgroundPaint(Color.white);  
				  //��������������ɫ  
				  plot.setDomainGridlinePaint(Color.pink);  
				  //�������������ɫ  
				  plot.setRangeGridlinePaint(Color.pink);  
				  //��ʾÿ��������ֵ�����޸ĸ���ֵ����������  
				  BarRenderer3D renderer = new BarRenderer3D();  
				  renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
				  renderer.setBaseItemLabelsVisible(true);  
				  //Ĭ�ϵ�������ʾ�������У�ͨ����������ɵ������ֵ���ʾ  
				  //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ��������� û����ʾ����������  
				  renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT));  
				  renderer.setItemLabelAnchorOffset(10D);    
				  plot.setRenderer(renderer);  
		return chart;
	}
}
