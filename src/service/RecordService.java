package service;

import java.util.Date;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordService {
	RecordDAO dao = new RecordDAO();

	/**
	 * 添加具体的消费记录
	 * 
	 * @param spend
	 *            花费钱数
	 * @param c
	 *            消费分类
	 * @param comment
	 *            备注
	 * @param date
	 *            日期
	 */
	public void add(int spend, Category c, String comment, Date date) {
		Record r = new Record();
		r.setSpend(spend);
		r.setCid(c.getId());
		r.setComment(comment);
		r.setMyDate(date);

		dao.add(r);
	}
}
