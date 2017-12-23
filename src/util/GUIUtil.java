package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * UI工具
 * 
 * @author 于修彦
 *
 */
public class GUIUtil {
	// 设置图片文件夹
	private static String imageFolder = getAbsPath() + "/img";

	/**
	 * 获取工程绝对路径
	 * 
	 * @return String 工程路径
	 */
	public static String getAbsPath() {
		File dir = new File(".");
		try {
			String path = dir.getCanonicalPath();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		} // 获得当前路径
		return null;
	}

	/**
	 * 给按钮设置图标
	 * 
	 * @param b
	 *            按钮组件
	 * @param fileName
	 *            图片文件名
	 * @param tip
	 *            按钮文字
	 */
	public static void setImageIcon(JButton b, String fileName, String tip) {
		File f = new File(imageFolder, fileName);
		ImageIcon i = null;
		if (f.exists()) {
			i = new ImageIcon(f.getAbsolutePath());
		} else {
			URL url = ClassLoader.getSystemResource("img/" + fileName);
			i = new ImageIcon(url);
		}
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61, 81));
		b.setToolTipText(tip); // 当光标放在组件上时显示的文字
		b.setVerticalTextPosition(3); // 设置竖直文字对齐方式
		b.setHorizontalTextPosition(0); // 设置水平文字对齐方式
		b.setText(tip); // 按钮上的文字
	}

	/**
	 * 给可变数量的组件设置前景色
	 * 
	 * @param color
	 * @param cs
	 */
	public static void setColor(Color color, JComponent... cs) { // 可变数量参数
		JComponent[] arrayOfJComponent;
		int j = (arrayOfJComponent = cs).length;
		for (int i = 0; i < j; i++) {
			JComponent c = arrayOfJComponent[i];
			c.setForeground(color);
		}
	}

	/**
	 * 换肤
	 */
	public static void useLNF() {
		try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			System.out.println("水晶皮肤没有找到这个包。");
			e.printStackTrace();
		}
	}

	/**
	 * 显示面板
	 */
	public static void showPanel(JPanel p, double stretchRate) {
		GUIUtil.useLNF();
		JFrame f = new JFrame();
		f.setSize(500, 500);
		// f.setBackground(ColorUtil.backgroundColor);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(stretchRate);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
		cp.show(p);
	}

	public static void showPanel(JPanel p) {
		showPanel(p, 0.85d);
	}

	/**
	 * 检查是否是整数
	 * 
	 * @param tf
	 * @param input
	 * @return 是整数返回true，否则返回false
	 */
	public static boolean checkNumber(JTextField tf, String input) {
		if (!checkEmpty(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, input + "需要的是整数！");
			tf.grabFocus();
		}
		return false;
	}

	/**
	 * 检查文本框是否为非空
	 * 
	 * @param tf
	 *            文本框组件
	 * @param input
	 *            提示信息
	 * @return 空返回false，非空返回true
	 */
	public static boolean checkEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if (text.length() == 0) {
			JOptionPane.showMessageDialog(null, input + "不能为空！");
			tf.grabFocus();
			return false;
		}
		return true;
	}

	/**
	 * 检查是否为零
	 * 
	 * @param tf
	 *            文本框组件
	 * @param input
	 *            提示信息
	 * @return 零返回false，非零数字返回true
	 */
	public static boolean checkZero(JTextField tf, String input) {
		if (!checkNumber(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		if (Integer.parseInt(text) == 0) {
			JOptionPane.showMessageDialog(null, input + "不能为零！");
			tf.grabFocus();
			return false;
		}
		return true;
	}

	/**
	 * 获取文本框中的整数
	 * 
	 * @param tf
	 *            文本框
	 * @return 文本框数字
	 */
	public static int getInt(JTextField tf) {
		return Integer.parseInt(tf.getText().trim());
	}

}
