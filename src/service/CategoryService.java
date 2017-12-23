package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {
	CategoryDAO categoryDao = new CategoryDAO();
	RecordDAO recordDao = new RecordDAO();
	
	/**
	 * 获取消费分类列表
	 * @return
	 */
	public List<Category> list(){
		//获取分类信息的列表
		List<Category> cs = categoryDao.list();
		//根据每一个分类id查询对应的消费记录列表
		for(Category c : cs){
			List<Record> rs = recordDao.list(c.getId());
			c.setRecordNumber(rs.size()); //消费分类的笔数
		}
		//根据消费笔数由大到小排序
		Collections.sort(cs, (c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
		
		return cs;
		
	}
	
	/**
	 * 添加消费分类项目
	 * @param name
	 */
	public void add(String name){
		Category c = new Category();
		c.setName(name);
		categoryDao.add(c);
	}
	
	/**
	 * 更新消费分类项目
	 * @param id
	 * @param name
	 */
	public void update(int id,String name){
		Category c = new Category();
		c.setId(id);
		c.setName(name);
		categoryDao.update(c);
	}
	
	/**
	 * 删除消费分类项目
	 * @param id
	 */
	public void delete(int id){
		categoryDao.delete(id);
	}
	
	
}
