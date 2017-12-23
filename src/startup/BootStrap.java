package startup;


import javax.swing.SwingUtilities;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;
/**
 * 启动入口
 * @author 于修彦
 *
 */
public class BootStrap {

	public static void main(String[] args) throws Exception {
		GUIUtil.useLNF();
		
		SwingUtilities.invokeAndWait(new Runnable(){

			@Override
			public void run() {
				MainFrame.instance.setVisible(true);
				MainPanel.instance.workingPanel.show(SpendPanel.instance);
				
			}
			
		});

	}

}
