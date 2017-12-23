package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

public class CategoryListsner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		CategoryPanel p = CategoryPanel.instance;

		JButton b = (JButton) e.getSource();

		// 点击添加按钮
		if (b == p.bAdd) {
			String name = JOptionPane.showInputDialog(null);
			if (name == null) { // 取消添加，直接返回
				return;
			}
			if (name.length() == 0) { // 添加空项，弹窗再返回
				JOptionPane.showMessageDialog(p, "分类名称不能为空！");
				return;
			}
			new CategoryService().add(name);
		}

		// 点击编辑按钮
		if (b == p.bEdit) {
			Category c = p.getSelectedCategory();
			int id = c.getId();
			String name = JOptionPane.showInputDialog("修改分类名称", c.getName());
			if (name == null) { // 取消编辑，直接返回
				return;
			}
			if (name.length() == 0) { // 添加空项，弹窗再返回
				JOptionPane.showMessageDialog(p, "分类名称不能为空！");
				return;
			}
			new CategoryService().update(id, name);
		}

		// 点击删除按钮
		if (b == p.bDelete) {
			Category c = p.getSelectedCategory();
			if (c.getRecordNumber() > 0) {
				JOptionPane.showMessageDialog(p, "本分类下面有消费记录！");
				return;
			}
			if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除吗？")) {
				return;
			}

			int id = c.getId();
			new CategoryService().delete(id);
		}

		// 更新表格数据
		p.updateData();
	}

}
