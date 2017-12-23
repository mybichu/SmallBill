package gui.panel;

import gui.listener.ToolBarListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import util.CenterPanel;
import util.GUIUtil;

/**
 * 主面板
 * 
 * @author 于修彦
 *
 */
public class MainPanel extends JPanel {
	// 这句话必须是放在第一句才可以生效
	static {
		GUIUtil.useLNF();
	}

	public static MainPanel instance = new MainPanel();
	public JToolBar tb = new JToolBar();
	public JButton bSpend = new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
	public CenterPanel workingPanel;

	private MainPanel() {
		// 给按钮加图标
		GUIUtil.setImageIcon(this.bSpend, "home.png", "消费一览");
		GUIUtil.setImageIcon(this.bRecord, "record.png", "记一笔");
		GUIUtil.setImageIcon(this.bCategory, "category2.png", "消费分类");
		GUIUtil.setImageIcon(this.bReport, "report.png", "月消费报表");
		GUIUtil.setImageIcon(this.bConfig, "config.png", "设置");
		GUIUtil.setImageIcon(this.bBackup, "backup.png", "备份");
		GUIUtil.setImageIcon(this.bRecover, "restore.png", "恢复");
		// 把按钮添加到工具栏上
		this.tb.add(this.bSpend);
		this.tb.add(this.bRecord);
		this.tb.add(this.bCategory);
		this.tb.add(this.bReport);
		this.tb.add(this.bConfig);
		this.tb.add(this.bBackup);
		this.tb.add(this.bRecover);
		this.tb.setFloatable(false); // 工具栏固定位置

		this.workingPanel = new CenterPanel(0.8D);

		setLayout(new BorderLayout());
		add(this.tb, BorderLayout.NORTH);
		add(this.workingPanel, BorderLayout.CENTER);

		addListener();
	}

	/**
	 * 添加监听器
	 */
	private void addListener() {
		ToolBarListener listener = new ToolBarListener();

		this.bSpend.addActionListener(listener);
		this.bRecord.addActionListener(listener);
		this.bCategory.addActionListener(listener);
		this.bReport.addActionListener(listener);
		this.bConfig.addActionListener(listener);
		this.bBackup.addActionListener(listener);
		this.bRecover.addActionListener(listener);
	}
}