package gui.page;

/**
 * 用于SpendPanel面板的页面类
 * 
 * @author 于修彦
 *
 */
public class SpendPage {
	public String monthSpend; // 本月消费
	public String todaySpend; // 今日消费
	public String avgSpendPerDay; // 日均消费
	public String monthAvailable; // 本月剩余
	public String dayAvgAvailable; // 日均可用
	public String monthLeftDay; // 距离月末
	public int usagePercentage; // 使用比例
	public boolean isOverSpend = false; // 是否超支

	public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
			int monthLeftDay, int usagePercentage) {
		this.monthSpend = "￥" + monthSpend;
		this.todaySpend = "￥" + todaySpend;
		this.avgSpendPerDay = "￥" + avgSpendPerDay;

		if (monthAvailable < 0) {
			isOverSpend = true;
		}
		if (!isOverSpend) {
			this.monthAvailable = "￥" + monthAvailable;
			this.dayAvgAvailable = "￥" + dayAvgAvailable;
		} else {
			this.monthAvailable = "超支" + (0 - monthAvailable);
			this.dayAvgAvailable = "￥0";
		}

		this.monthLeftDay = monthLeftDay + "天";
		this.usagePercentage = usagePercentage;

	}

}
