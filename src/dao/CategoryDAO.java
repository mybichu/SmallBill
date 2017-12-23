package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

/**
 * catebory表的ORM映射
 * 
 * @author 于修彦
 *
 */
public class CategoryDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "select count(*) from category";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public void add(Category category) {

		String sql = "insert into category(name) values(?)";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, category.getName());

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				category.setId(rs.getInt(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void update(Category category) {

		String sql = "update category set name= ? where id = ?";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {

		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "delete from category where id = " + id;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Category get(int id) {
		Category category = null;

		try (Connection c = DBUtil.getConn(); Statement s = c.createStatement();) {

			String sql = "select * from category where id = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				category = new Category();
				String name = rs.getString(2);
				category.setName(name);
				category.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return category;
	}

	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Category> list(int start, int count) {
		List<Category> categorys = new ArrayList<Category>();

		String sql = "select * from category order by id desc limit ?,? ";

		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				category.setId(id);
				category.setName(name);
				categorys.add(category);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return categorys;
	}

}