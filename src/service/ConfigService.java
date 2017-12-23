package service;

import dao.ConfigDAO;
import entity.Config;
/**
 * 用于config表的服务类，该类封装ConfigDAO类的一些方法
 * @author 于修彦
 *
 */
public class ConfigService {
	/**
	 * 预算
	 */
	public static final String budget = "budget";    //预算
	/**
	 * 数据库路径
	 */
	public static final String mysqlPath = "mysqlPath";  //数据库路径
	/**
	 * 默认预算为500元
	 */
	public static final String default_budget = "500";  //默认预算
	
	static ConfigDAO dao = new ConfigDAO();
	//初始化
	static{
		init();
	}
	
	private static void init(String key,String value){
		Config config = dao.getByKey(key);
		if(config==null){
			Config c = new Config();
			c.setMyKey(key);
			c.setMyValue(value);
			dao.add(c);
		}
	}
	
	public static void init(){
		init(budget,default_budget);
		init(mysqlPath,"");
	}
	
	/**
	 * 根据key获取value
	 * @param key
	 * @return value
	 */
	public String get(String key){
		Config config = dao.getByKey(key);
		return config.getMyValue();
	}
	
	/**
	 * 更新配置信息
	 * @param key 
	 * @param value
	 * @return 成功返回true，失败返回false
	 */
	public boolean update(String key,String value){
		Config config = dao.getByKey(key);
		config.setMyValue(value);
		return dao.update(config);		
	}
	
	/**
	 * 获取整数的预算
	 * @return （int）预算
	 */
	public int getIntBudget(){
		return Integer.parseInt(get(budget));
	}
}
