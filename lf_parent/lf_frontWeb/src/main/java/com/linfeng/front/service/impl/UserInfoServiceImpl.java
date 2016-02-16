package com.linfeng.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.lifeng.common.controller.BaseController;
import org.lifeng.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linfeng.front.dao.UserInfoDao;
import com.linfeng.front.model.UserIdeaModel;
import com.linfeng.front.model.UserInfoModel;
import com.linfeng.front.service.UserInfoService;

/**
 * 
 * Description: 
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-31 下午6:37:15
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-31 wxm 1.0
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoModel, Long> implements UserInfoService {

	@Qualifier("userInfoDao")
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public boolean checkIfExistUser(UserInfoModel user) {
		if(StringUtils.isEmpty(user.getUserName())){
			return false;
		}
		return null != userInfoDao.getUserInfoByUserName(user.getUserName().trim());
	}

	@Override
	public UserInfoModel getUserInfoByUserName(String username) {
		return userInfoDao.getUserInfoByUserName(username);
	}

	@Override
	public Map<String, String> login(UserInfoModel user) {
		Map<String, String> result =new HashMap<String, String>();
		UserInfoModel userDB = userInfoDao.getUserInfoByUserName(user.getUserName().trim());
		if(null == userDB){
			result.put(BaseController.RETURN_CODE, "1");
			result.put(BaseController.RETURN_MSG, "用户不存在！");
			return result;
		}
		if(!userDB.getUserPwd().equals(user.getUserPwd())){
			result.put(BaseController.RETURN_CODE, "2");
			result.put(BaseController.RETURN_MSG, "密码不正确！");
			return result;
		}
		result.put(BaseController.RETURN_CODE, "0");
		result.put(BaseController.RETURN_MSG, "登陆成功！");
		return result;
	}

	@Override
	public List<UserIdeaModel> getMyIdeaList(Long id) {
		//return userInfoDao.get;
		return null;
	}
	
}
