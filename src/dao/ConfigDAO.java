package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Config;
import util.DBUtil;

/**
 * 专门用于把Config实例与Config表进行ORM映射
 * 
 * @author 于修彦
 *
 */
public class ConfigDAO {
	/**
	 * 获取总数
	 * 
	 * @return
	 */
	public int getTotal() {
		int total = 0;
		String sql = "select count(*) from config";
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 添加配置
	 * 
	 * @param config
	 *            配置信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean add(Config config) {
		String sql = "insert into config(myKey,myValue) values(?,?)";
		boolean flag = false;
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, config.getMyKey());
			ps.setString(2, config.getMyValue());
			flag = ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				config.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除配置信息
	 * 
	 * @param config
	 *            配置
	 * @return 成功返回true，失败返回false
	 */
	public boolean delete(Config config) {
		String sql = "delete from config where id = ?";
		boolean flag = false;
		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, config.getId());
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 更新配置信息
	 * 
	 * @param config
	 *            配置信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean update(Config config) {
		boolean flag = false;
		String sql = "update config set myKey=?,myValue=? where id=?";
		Connection conn = DBUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, config.getMyKey());
			ps.setString(2, config.getMyValue());
			ps.setInt(3, config.getId());
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 通过id获取config实例
	 * 
	 * @param id
	 * @return config实例
	 */
	public Config get(int id) {
		Config config = null;
		String sql = "select * from config where id=?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				config = new Config();
				config.setId(id);
				config.setMyKey(rs.getString("myKey"));
				config.setMyValue(rs.getString("myValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}

	/**
	 * 用于分页查询
	 * 
	 * @param start
	 *            开始id
	 * @param count
	 *            每页条数
	 * @return config的列表
	 */
	public List<Config> list(int start, int count) {
		List<Config> configs = new ArrayList<Config>();
		String sql = "select * from config order by id desc limit ?,?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Config config = new Config();
				config.setId(rs.getInt("id"));
				config.setMyKey(rs.getString("myKey"));
				config.setMyValue(rs.getString("myValue"));

				configs.add(config);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return configs;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public List<Config> list() {
		return list(0,Short.MAX_VALUE);
	}

	/**
	 * 通过键获取Config实例，比如预算对应的Config实例，就会通过这种方式获取： new
	 * ConfigDAO().getByKey("budget");
	 * 
	 * @param key
	 * @return config实例
	 */
	public Config getByKey(String key) {
		Config config = null;
		String sql = "select * from config where myKey=?";

		try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				config = new Config();
				config.setId(rs.getInt("id"));
				config.setMyKey(key);
				config.setMyValue(rs.getString("myValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return config;
	}

}
