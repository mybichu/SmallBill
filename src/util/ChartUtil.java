package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import entity.Record;

/**
 * 生成柱状图
 * 
 * @author 于修彦
 *
 */
public class ChartUtil {
	/**
	 * 横轴下面的文字
	 * 
	 * @param rs
	 *            数据集
	 * @return 依据数据集生成的文字数组
	 */
	private static String[] sampleLabels(List<Record> rs) {
		String[] sampleLabels = new String[rs.size()];
		for (int i = 0; i < sampleLabels.length; i++) {
			if (0 == i % 5)
				sampleLabels[i] = String.valueOf(i + 1 + "日");
		}

		return sampleLabels;

	}

	/**
	 * 柱状图的柱的数据高度
	 * 
	 * @param rs
	 *            数据集
	 * @return 依据数据集生成的数据数组
	 */
	public static double[] sampleValues(List<Record> rs) {
		double[] sampleValues = new double[rs.size()];
		for (int i = 0; i < sampleValues.length; i++) {
			sampleValues[i] = rs.get(i).getSpend();
		}

		return sampleValues;
	}

	/**
	 * 获取柱状图的图片
	 * 
	 * @param rs
	 *            数据集
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return 结果图
	 */
	public static Image getImage(List<Record> rs, int width, int height) {
		// 根据消费记录得到的样本数据
		double[] sampleValues = sampleValues(rs);
		// 根据消费记录得到的下方日期文本
		String[] sampleLabels = sampleLabels(rs);
		// 样本中的最大值
		int max = max(sampleValues);

		// 数据颜色
		Color[] sampleColors = new Color[] { ColorUtil.blueColor };

		// 柱状图
		BarChart chart = new BarChart();

		// 设置样本个数
		chart.setSampleCount(sampleValues.length);
		// 设置样本数据
		chart.setSampleValues(0, sampleValues);
		// 设置文字
		chart.setSampleLabels(sampleLabels);
		// 设置样本颜色
		chart.setSampleColors(sampleColors);
		// 设置取值范围
		chart.setRange(0, max * 1.2);
		// 显示背景横线
		chart.setValueLinesOn(true);
		// 显示文字
		chart.setSampleLabelsOn(true);
		// 把文字显示在下方
		chart.setSampleLabelStyle(Chart.BELOW);

		// 样本值的字体
		chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
		// 显示图例说明
		chart.setLegendOn(true);
		// 把图例说明放在左侧
		chart.setLegendPosition(Chart.LEFT);
		// 图例说明中的文字
		chart.setLegendLabels(new String[] { "月消费报表" });
		// 图例说明的字体
		chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
		// 下方文字的字体
		chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
		// 图表中间背景颜色
		chart.setChartBackground(Color.white);
		// 图表整体背景颜色
		chart.setBackground(ColorUtil.backgroundColor);
		// 把图表转换为Image类型
		Image im = chart.getImage(width, height);
		return im;
	}

	public static int max(double[] sampleValues) {
		int max = 0;
		for (double v : sampleValues) {
			if (v > max)
				max = (int) v;
		}
		return max;

	}

	private static String[] sampleLabels() {
		String[] sampleLabels = new String[30];

		for (int i = 0; i < sampleLabels.length; i++) {
			if (0 == i % 5)
				sampleLabels[i] = String.valueOf(i + 1 + "日");
		}
		return sampleLabels;
	}

	public static Image getImage(int width, int height) {
		// 模拟样本数据
		double[] sampleValues = sampleValues();
		// 下方显示的文字
		String[] sampleLabels = sampleLabels();
		// 样本中的最大值
		int max = max(sampleValues);

		// 数据颜色
		Color[] sampleColors = new Color[] { ColorUtil.blueColor };

		// 柱状图
		BarChart chart = new BarChart();

		// 设置样本个数
		chart.setSampleCount(sampleValues.length);
		// 设置样本数据
		chart.setSampleValues(0, sampleValues);
		// 设置文字
		chart.setSampleLabels(sampleLabels);
		// 设置样本颜色
		chart.setSampleColors(sampleColors);
		// 设置取值范围
		chart.setRange(0, max * 1.2);
		// 显示背景横线
		chart.setValueLinesOn(true);
		// 显示文字
		chart.setSampleLabelsOn(true);
		// 把文字显示在下方
		chart.setSampleLabelStyle(Chart.BELOW);

		// 样本值的字体
		chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
		// 显示图例说明
		chart.setLegendOn(true);
		// 把图例说明放在左侧
		chart.setLegendPosition(Chart.LEFT);
		// 图例说明中的文字
		chart.setLegendLabels(new String[] { "月消费报表" });
		// 图例说明的字体
		chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
		// 下方文字的字体
		chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
		// 图表中间背景颜色
		chart.setChartBackground(Color.white);
		// 图表整体背景颜色
		chart.setBackground(ColorUtil.backgroundColor);
		// 把图表转换为Image类型
		Image im = chart.getImage(width, height);
		return im;
	}

	private static double[] sampleValues() {

		double[] result = new double[30];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int) (Math.random() * 300);
		}
		return result;

	}

	// public static void main(String[] args) {
	// JPanel p = new JPanel();
	// JLabel l = new JLabel();
	// Image img = ChartUtil.getImage(400, 300);
	// Icon icon = new ImageIcon(img);
	// l.setIcon(icon);
	// p.add(l);
	// GUIUtil.showPanel(p);
	// }

}