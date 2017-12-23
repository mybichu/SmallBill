package service;

import java.util.List;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

public class SpendService {

	/**
	 * 获取页面数据
	 * 
	 * @return SpendPage实例
	 */
	public SpendPage getSpendPage() {
		RecordDAO dao = new RecordDAO();
		// 本月数据
		List<Record> thisMonthRecords = dao.listThisMonth();
		// 今日数据
		List<Record> todayRecords = dao.listToday();
		// 本月总天数
		int thisMOnthTotalDays = DateUtil.thisMonthTotalDay();
		// 本月已经度过的天数（包括本日）
		int thisMonthSpentDays = DateUtil.thisMonthSpentDay();
		// 月消费
		int monthSpend = 0;
		// 日消费
		int todaySpend = 0;
		// 日均消费
		int avgSpendPerDay = 0;
		// 本月可用
		int monthAvailable = 0;
		// 日均可用
		int dayAvgAvailable = 0;
		// 本月剩余天数
		int monthLeftDay = 0;
		// 已使用比例
		int usagePercentage = 0;

		// 预算
		int monthBudget = new ConfigService().getIntBudget();

		// 统计本月消费
		for (Record r : thisMonthRecords) {
			monthSpend += r.getSpend();
		}

		// 统计今日消费
		for (Record r : todayRecords) {
			todaySpend += r.getSpend();
		}

		// 计算日均消费
		avgSpendPerDay = monthSpend / thisMonthSpentDays;

		// 计算本月剩余
		monthAvailable = monthBudget - monthSpend;

		// 距离月末还有几天
		monthLeftDay = DateUtil.thisMonthLeftDay();

		// 计算日均可用
		dayAvgAvailable = monthAvailable / monthLeftDay;

		// 使用比例
		usagePercentage = monthSpend * 100 / monthBudget;

		return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay,
				usagePercentage);

	}
}
