package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Category;
import gui.listener.CategoryListsner;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel {
	// 初始化皮肤
	static {
		GUIUtil.useLNF();
	}
	public static CategoryPanel instance = new CategoryPanel();

	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");

	String[] columnNames = new String[] { "分类名称", "消费次数" };

	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable jt = new JTable(ctm);

	public CategoryPanel() {
		// 设置表格列居中显示
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("分类名称").setCellRenderer(render);
		jt.getColumn("消费次数").setCellRenderer(render);
		// 设置颜色
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);

		JScrollPane jsp = new JScrollPane(jt);
		// 下面的按钮面板
		JPanel pSubmit = new JPanel();
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);
		// 设置布局
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(pSubmit, BorderLayout.SOUTH);
		// 初始化时table为空，编辑和删除按钮置灰
		bEdit.setEnabled(false);
		bDelete.setEnabled(false);
		// 添加监听
		addListener();

		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				bEdit.setEnabled(true);
				bDelete.setEnabled(true);
			}
		});
	}

	/**
	 * 获取选中的一行消费分类
	 * 
	 * @return Category实例
	 */
	public Category getSelectedCategory() {
		int index = jt.getSelectedRow();
		return ctm.cs.get(index);
	}

	/**
	 * 更新表格数据
	 */
	public void updateData() {
		ctm.cs = new CategoryService().list(); // 重新获取列表
		jt.updateUI(); // 更新表格的显示
		// jt.getSelectionModel().setSelectionInterval(0, 0); //默认选中第0行

		// 如果消费列表里面没有东西，就不能编辑和删除
		if (ctm.cs.size() == 0) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		} else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}

	}

	public void addListener() {
		CategoryListsner cl = new CategoryListsner();
		bAdd.addActionListener(cl);
		bEdit.addActionListener(cl);
		bDelete.addActionListener(cl);
	}
}
