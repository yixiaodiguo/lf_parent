package com.linfeng.front.service;

import java.util.List;
import java.util.Map;

import org.lifeng.common.service.BaseService;

import com.linfeng.front.model.UserIdeaModel;
import com.linfeng.front.model.UserInfoModel;

/**
 * 
 * Description: 
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-31 下午6:23:55
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-31 wxm 1.0
 */
public interface UserInfoService extends BaseService<UserInfoModel, Long>{

	boolean checkIfExistUser(UserInfoModel user);

	UserInfoModel getUserInfoByUserName(String trim);

	Map<String, String> login(UserInfoModel user);

	List<UserIdeaModel> getMyIdeaList(Long id);

}
