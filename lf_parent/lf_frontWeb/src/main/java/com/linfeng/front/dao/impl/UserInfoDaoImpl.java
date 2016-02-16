package com.linfeng.front.dao.impl;
import java.util.List;

import org.lifeng.common.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.linfeng.front.dao.UserInfoDao;
import com.linfeng.front.model.UserInfoModel;

/**
 * 
 * Description: 
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-30 下午10:26:37
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-30 wxm 1.0
 */
@SuppressWarnings("unchecked")
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfoModel,Long> implements UserInfoDao {

	@Override
	public UserInfoModel getUserInfoByUserName(String userName) {
		List<UserInfoModel> users = getList("userName", userName);
		if(!CollectionUtils.isEmpty(users)){
			return users.get(0);
		}
		return null;
	}

}
