package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		//检查输入的预算是不是整数
		if(!GUIUtil.checkNumber(p.tfBudget, "本月预算")){
			return;
		}
		String mysqlPath = p.tfMysqlPath.getText();
		//检查输入的路径是不是mysql的安装路径
		if(mysqlPath.length()!=0){
			File commandFile = new File(mysqlPath,"bin/mysql.exe");
			if(!commandFile.exists()){//如果不是
				JOptionPane.showMessageDialog(p, "MYSQL路径不正确！");
				p.tfMysqlPath.grabFocus();
				return;
			}
		}
		
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, mysqlPath);
		
		JOptionPane.showMessageDialog(p, "配置修改成功");
	}

}
