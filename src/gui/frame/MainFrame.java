package gui.frame;

import javax.swing.JFrame;

import gui.panel.MainPanel;

public class MainFrame extends JFrame {
	public static MainFrame instance = new MainFrame();

	private MainFrame() {
		this.setSize(500, 450);
		this.setTitle("小小记账本");
		this.setContentPane(MainPanel.instance);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
	}

//	public static void main(String[] args) {
//		instance.setVisible(true);
//	}
}
