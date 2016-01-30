package org.lifeng.common;
/**
 * Description: 系统常量
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2013-12-1 上午10:12:07
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2013-12-1 wxm 1.0
 */
public class Constants {
	
	public static final String PROJECT_URL = "projectUrl";
	
	public static final String PROJECT_NAME = "projectName";
	
	//保存用户session属性值
	public static final String LOGIN_USER = "com.lf.LOGIN_USER";
	
	//保存全局信息类com.lf.system.common.bean.GlobalInfo存在application中
	public static final String KEY_GLOBALINFO = "com.lf.KEY_GLOBALINFO";
	
	//系统相关配置
	public static final String SYSTEM_CONFIG = "com.lf.KEY_SYSTEMCONFIG";
	
	//系统相关配置中的application的key
	public static final String APPLICATION = "com.lf.KEY_APPLICATION";
	
	//用户失败登陆次数限制,存在application中
	public static final String User_LOGIN_TIMES = "com.lf.KEY_LOGIN_TIMES";
	
	//用户登陆后初始化资源，保存在session中
	public static final String User_RESOURCE = "com.lf.KEY_RESOURCE";
	
	//分页默认大小
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	//分页保存request中的分页器
	public static final String PAGE_BEAN = "com.lf.KEY_PAGE_BEAN";
	
	public static final String CURRENT_USER = "user";
	
	//当前登陆用户所拥有的菜单信息
	public static final String USER_MENUS = "userMenus";
}
