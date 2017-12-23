package gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

/**
 * 记录面板
 * 
 * @author 于修彦
 *
 */
public class RecordPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}

	public static RecordPanel instance = new RecordPanel();
	// 左侧描述信息
	JLabel lSpend = new JLabel("花费(￥)");
	JLabel lCategory = new JLabel("分类");
	JLabel lComment = new JLabel("备注");
	JLabel lDate = new JLabel("日期");
	// 花费
	public JTextField tfSpend = new JTextField("0");
	// 下拉列表
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<Category> cbCategory = new JComboBox<Category>(cbModel);
	// 备注
	public JTextField tfComment = new JTextField();
	// 日期
	public JXDatePicker datepick = new JXDatePicker(new Date());
	// 提交按钮
	public JButton bSubmit = new JButton("记一笔");

	public RecordPanel() {
		GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

		lSpend.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lCategory.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lComment.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		lDate.setFont(new Font("Microsoft Yahei", Font.ROMAN_BASELINE, 16));
		// 将整个记录面板分为两个子面板
		int gap = 40;
		JPanel pInput = new JPanel(new GridLayout(4, 2, gap, gap));
		JPanel pSubmit = new JPanel(new FlowLayout());

		pInput.add(lSpend);
		pInput.add(tfSpend);

		pInput.add(lCategory);
		pInput.add(cbCategory);

		pInput.add(lComment);
		pInput.add(tfComment);

		pInput.add(lDate);
		pInput.add(datepick);

		pSubmit.add(bSubmit);

		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		this.add(pSubmit, BorderLayout.SOUTH);
		
		addListener();

	}
	
	public Category getSelectedCategory(){
		return (Category)cbCategory.getSelectedItem();		
	}

	
	@Override
	public void updateData() {
		cbModel.cs = new CategoryService().list(); //重新获取消费分类项目列表
		cbCategory.updateUI();  //刷新UI 
		resetInput();  //重置输入内容
		tfSpend.grabFocus();  //输入框获取焦点
		
	}
	
	private void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");
        if(cbModel.cs.size()!=0){
        	 cbCategory.setSelectedIndex(0);
        }         
        datepick.setDate(new Date());
	}

	@Override
	public void addListener() {
		RecordListener rl = new RecordListener();
		bSubmit.addActionListener(rl);
		
	}

}
