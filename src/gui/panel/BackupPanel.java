package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 备份面板
 * 
 * @author 于修彦
 *
 */
public class BackupPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}

	public static BackupPanel instance = new BackupPanel();
	JButton bBackup = new JButton("备份");

	public BackupPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bBackup);
		this.add(bBackup);
		addListener();
	}

	@Override
	public void updateData() {
		
	}

	@Override
	public void addListener() {
		BackupListener bl = new BackupListener();
		bBackup.addActionListener(bl);
		
	}

}