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
				  "选择题答案统计", 
				  "选项", 
				  "选项人数",  
				  dataset, 
				  PlotOrientation.VERTICAL, 
				  true, 
				  true, 
				  false);  

				  Font font = new Font("黑体", Font.BOLD, 16);  
				  TextTitle title = new TextTitle("选择题答案统计", font);  
				  chart.setTitle(title);//标题  
				  CategoryPlot plot = chart.getCategoryPlot();  
				  NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
				  CategoryAxis domainAxis = plot.getDomainAxis();  
				  //设置X轴坐标上的文字  
				  domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
				  //设置X轴的标题文字  
				  domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
				  //设置Y轴坐标上的文字  
				  numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
				  //设置Y轴的标题文字  
				  numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
				  //解决底部汉字乱码的问题  
				  chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN,  12)); //由底部文字才加，不然会出错  
				  // 以上为文字设置  
				  //以下为背景设置  
				  //设置网格背景颜色  
				  plot.setBackgroundPaint(Color.white);  
				  //设置网格竖线颜色  
				  plot.setDomainGridlinePaint(Color.pink);  
				  //设置网格横线颜色  
				  plot.setRangeGridlinePaint(Color.pink);  
				  //显示每个柱的数值，并修改该数值的字体属性  
				  BarRenderer3D renderer = new BarRenderer3D();  
				  renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
				  renderer.setBaseItemLabelsVisible(true);  
				  //默认的数字显示在柱子中，通过如下两句可调整数字的显示  
				  //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字 没有显示出来的问题  
				  renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT));  
				  renderer.setItemLabelAnchorOffset(10D);    
				  plot.setRenderer(renderer);  
		return chart;
	}
}
