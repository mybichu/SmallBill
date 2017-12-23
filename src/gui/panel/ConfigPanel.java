package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 设置面板
 * 
 * @author 于修彦
 *
 */
public class ConfigPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static ConfigPanel instance = new ConfigPanel();

	JLabel lBudget = new JLabel("本月预算(￥)");
	public JTextField tfBudget = new JTextField("0");

	JLabel lMysql = new JLabel("Mysql安装目录");
	public JTextField tfMysqlPath = new JTextField("");

	JButton bSubmit = new JButton("更新");

	public ConfigPanel() {
		GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		int gap = 40;
		pInput.setLayout(new GridLayout(4, 1, gap, gap));

		pInput.add(lBudget);
		pInput.add(tfBudget);
		pInput.add(lMysql);
		pInput.add(tfMysqlPath);
		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);

		pSubmit.add(bSubmit);
		this.add(pSubmit, BorderLayout.CENTER);
		
		//添加监听器
		addListener();

	}
	
	@Override
	public void addListener(){
		ConfigListener cl = new ConfigListener();
		bSubmit.addActionListener(cl);
	}

	@Override
	public void updateData() {
		ConfigService cs = new ConfigService();
		String budget = cs.get(ConfigService.budget);
		String mysqlPath = cs.get(ConfigService.mysqlPath);
		
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		
		tfBudget.grabFocus();
		
	}

}