package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

/**
 * record表的映射
 * 
 * @author 于修彦
 *
 */
public class RecordDAO {
	/**
	 * 获取总数
	 * 
	 * @return 记录条数
	 */
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "select count(*) from record";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 增加记录
	 * 
	 * @param record
	 */
	public void add(Record record) {

		String sql = "insert into record values(null,?,?,?,?)";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getMyDate()));

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				record.setId(id);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 更新记录
	 * 
	 * @param record
	 */
	public void update(Record record) {

		String sql = "update record set spend= ?, cid= ?, comment =?, myDate = ? where id = ?";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getMyDate()));
			ps.setInt(5, record.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 删除记录
	 * 
	 * @param id
	 */
	public void delete(int id) {

		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "delete from record where id = " + id;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 根据id获取记录
	 * 
	 * @param id
	 * @return record实例
	 */
	public Record get(int id) {
		Record record = null;

		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "select * from record where id = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				record = new Record();
				int spend = rs.getInt("spend");
				int cid = rs.getInt("cid");
				String comment = rs.getString("comment");
				Date date = rs.getDate("myDate");

				record.setSpend(spend);
				record.setCid(cid);
				record.setComment(comment);
				record.setMyDate(date);
				record.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return record;
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public List<Record> list() {
		return list(0, Short.MAX_VALUE);
	}

	/**
	 * 用于分页查询
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Record> list(int start, int count) {
		List<Record> records = new ArrayList<Record>();

		String sql = "select * from record order by id desc limit ?,? ";

		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");
				int cid = rs.getInt("cid");

				String comment = rs.getString("comment");
				Date date = rs.getDate("myDate");

				record.setSpend(spend);
				record.setCid(cid);
				record.setComment(comment);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}

	/**
	 * 获取某一项分类的记录列表
	 * 
	 * @param cid
	 * @return
	 */
	public List<Record> list(int cid) {
		List<Record> records = new ArrayList<Record>();

		String sql = "select * from record where cid = ?";

		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");

				String comment = rs.getString("comment");
				Date date = rs.getDate("myDate");

				record.setSpend(spend);
				record.setCid(cid);
				record.setComment(comment);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}

	/**
	 * 获取今天的消费记录列表
	 * 
	 * @return
	 */
	public List<Record> listToday() {
		return list(DateUtil.getToday());
	}

	/**
	 * 根据日期获取某一天的消费记录列表
	 * 
	 * @param day
	 * @return
	 */
	public List<Record> list(Date day) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where myDate =?";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, DateUtil.util2sql(day));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");

				String comment = rs.getString("comment");
				Date date = rs.getDate("myDate");

				record.setSpend(spend);
				record.setCid(cid);
				record.setComment(comment);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}

	/**
	 * 获取本月份消费记录
	 * 
	 * @return
	 */
	public List<Record> listThisMonth() {
		return list(DateUtil.getMonthBegin(), DateUtil.getMonthEnd());
	}

	/**
	 * 获取从开始日期到结束日期的消费记录
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Record> list(Date start, Date end) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where myDate >=? and myDate <= ?";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, DateUtil.util2sql(start));
			ps.setDate(2, DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");

				String comment = rs.getString("comment");
				Date date = rs.getDate("myDate");

				record.setSpend(spend);
				record.setCid(cid);
				record.setComment(comment);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}

}