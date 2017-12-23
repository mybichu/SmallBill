package gui.panel;

import javax.swing.JPanel;

public abstract class WorkingPanel extends JPanel{
	/**
	 * 更新面板数据
	 */
	public abstract void updateData();
	
	/**
	 * 面板添加监听
	 */
	public abstract void addListener();
}
