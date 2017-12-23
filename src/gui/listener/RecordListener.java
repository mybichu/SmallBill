package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.CenterPanel;
import util.GUIUtil;

public class RecordListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		RecordPanel rp = RecordPanel.instance;
		CenterPanel cp = MainPanel.instance.workingPanel;
		
		//如果暂时还没有消费分类项，提示添加消费分类项
		if(rp.cbModel.cs.size()==0){
			JOptionPane.showMessageDialog(rp, "暂时还没有消费分类哦，请先添加消费分类~");
			cp.show(CategoryPanel.instance);
			return;
		}
		//检查消费金额是否非零
        if(!GUIUtil.checkZero(rp.tfSpend,"花费金额")){
        	return;
        }
        //获取面板上的信息
        int spend = Integer.parseInt(rp.tfSpend.getText());
        Category c = rp.getSelectedCategory();
        String comment = rp.tfComment.getText();
        Date date = rp.datepick.getDate();
        //添加
        new RecordService().add(spend,c,comment,date);
        JOptionPane.showMessageDialog(rp, "添加成功");
        //跳转
        cp.show(SpendPanel.instance);
	}

}
