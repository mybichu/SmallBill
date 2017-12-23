package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import gui.panel.WorkingPanel;

/**
 * 居中面板
 * 
 * @author 于修彦
 *
 */
public class CenterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double rate; // 比例
	private JComponent c; // 面板上将要居中的组件
	private boolean stretch;// 是否缩放

	public CenterPanel(double rate, boolean stretch) {
		this.setLayout(null);
		this.rate = rate;
		this.stretch = stretch;
	}

	public CenterPanel(double rate) {
		this(rate, true);
	}

	/**
	 * 重写重绘方法，在面板居中显示组件
	 */
	public void repaint() {
		if (this.c != null) {
			Dimension containerSize = this.getSize();// 面板大小
			Dimension componentSize = this.c.getPreferredSize();// 获取组件调整之后的大小

			if (this.stretch) {
				// 设置这个组件大小
				this.c.setSize((int) (containerSize.width * this.rate), (int) (containerSize.height * this.rate));
			} else {
				this.c.setSize(componentSize);// 保持原来大小不变
			}
			// 将组件放到居中位置
			this.c.setLocation(containerSize.width / 2 - this.c.getWidth() / 2,
					containerSize.height / 2 - this.c.getHeight() / 2);

		}
		super.repaint();
	}

	/**
	 * 显示组件
	 * 
	 * @param p
	 *            组件
	 */
	public void show(JComponent p) {
		this.c = p;
		Component[] cs = this.getComponents();
		Component[] arrayOfComponent1;
		int j = (arrayOfComponent1 = cs).length;
		for (int i = 0; i < j; i++) { // 清除面板上原来的组件
			Component c = arrayOfComponent1[i];
			remove(c);
		}
		add(p);

		if ((p instanceof WorkingPanel)) {
			((WorkingPanel) p).updateData();
		}
		updateUI();
	}
}
