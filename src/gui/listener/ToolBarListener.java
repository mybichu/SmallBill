package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.panel.BackupPanel;
import gui.panel.CategoryPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.RecoverPanel;
import gui.panel.ReportPanel;
import gui.panel.SpendPanel;
/**
 * 工具栏监听器
 * @author 于修彦
 * 
 */
public class ToolBarListener implements ActionListener{
	MainPanel p;
	JButton jb;
	@Override
	public void actionPerformed(ActionEvent e) {
		p = MainPanel.instance;
		jb = (JButton)e.getSource();  //获取点击的按钮
		
		if(jb == p.bReport){
			p.workingPanel.show(ReportPanel.instance);
		}
		if(jb == p.bBackup){
			p.workingPanel.show(BackupPanel.instance);
		}
		if(jb == p.bCategory){
			p.workingPanel.show(CategoryPanel.instance);
		}
		if(jb == p.bConfig){
			p.workingPanel.show(ConfigPanel.instance);
		}
		if(jb == p.bRecord){
			p.workingPanel.show(RecordPanel.instance);
		}
		if(jb == p.bRecover){
			p.workingPanel.show(RecoverPanel.instance);
		}
		if(jb == p.bSpend){
			p.workingPanel.show(SpendPanel.instance);
		}		
	}

}
