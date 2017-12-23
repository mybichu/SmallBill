package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

public class BackupListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		BackupPanel p = BackupPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		
		//如果事前没有配置MySQL路径
		if(mysqlPath.length()==0){
			JOptionPane.showMessageDialog(p, "备份前请先配置MySQL的路径");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
			
		}
		
		//打开一个文件选择器
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("bill.sql"));
		fc.setFileFilter(new FileFilter(){

			@Override
			public boolean accept(File f) {				
				return f.getName().toLowerCase().endsWith(".sql");
			}
			@Override
			public String getDescription() {				
				return ".sql";
			}
			
		});
		
		int returnval = fc.showSaveDialog(p);
		File file = fc.getSelectedFile();
		
		//当点击了保存文件按钮时
		if(returnval == JFileChooser.APPROVE_OPTION){
			//如果保存的文件名没有以.sql结尾，自动加上.sql
			if(!file.getName().toLowerCase().endsWith(".sql")){
				file = new File(file.getParent(),file.getName()+".sql");   //创建该文件
			}
			
			 try {
	                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
	                JOptionPane.showMessageDialog(p, "备份成功,备份文件位于:\r\n"+file.getAbsolutePath());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(p, "备份失败\r\n,错误:\r\n"+e1.getMessage());   
	            }
		}
	}

}
