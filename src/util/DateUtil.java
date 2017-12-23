package util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author 于修彦
 *
 */
public class DateUtil {
	public static long millisecondsOfOneDay = 1000 * 60 * 60 * 24L;

	/**
	 * 两个不同Date类型的转换 <b>sql.Date是util.Date的子类</b>
	 * 
	 * @param date
	 *            java.util.date
	 * @return java.sql.date
	 */
	public static java.sql.Date util2sql(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取今天的日期
	 * 
	 * @return java.util.date
	 */
	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	/**
	 * 获取本月的月初第一天
	 * 
	 * @return java.util.date
	 */
	public static Date getMonthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 1);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();

	}

	/**
	 * 获取月末
	 * 
	 * @return
	 */
	public static Date getMonthEnd() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);

		return c.getTime();
	}

	/**
	 * 获取本月一共多少天
	 * 
	 * @return
	 */
	public static int thisMonthTotalDay() {
		long lastDayMillisecs = getMonthEnd().getTime();
		long firstDayMillisecs = getMonthBegin().getTime();
		return (int) ((lastDayMillisecs - firstDayMillisecs) / millisecondsOfOneDay) + 1;
	}

	/**
	 * 获取本月还剩多少天（不算今天）
	 * 
	 * @return
	 */

	public static int thisMonthLeftDay() {
		long lastDayMilliSeconds = getMonthEnd().getTime();
		long todayMilliSeconds = getToday().getTime();
		return (int) ((lastDayMilliSeconds - todayMilliSeconds) / millisecondsOfOneDay);
	}

	/**
	 * 获取本月已经过了多少天（加上今天）
	 * 
	 * @return
	 */
	public static int thisMonthSpentDay() {
		long firstDayMillisecs = getMonthBegin().getTime();
		long todayMilliSecs = getToday().getTime();

		return (int) ((todayMilliSecs - firstDayMillisecs) / millisecondsOfOneDay) + 1;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.thisMonthSpentDay());
	}
}
