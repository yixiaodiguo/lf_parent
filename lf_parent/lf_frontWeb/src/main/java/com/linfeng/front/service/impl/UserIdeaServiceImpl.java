package com.linfeng.front.service.impl;

import java.util.List;

import org.lifeng.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linfeng.front.dao.UserIdeaDao;
import com.linfeng.front.model.UserIdeaModel;
import com.linfeng.front.service.UserIdeaService;

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
@Service("userIdeaService")
public class UserIdeaServiceImpl extends BaseServiceImpl<UserIdeaModel, Long> implements UserIdeaService {

	@Qualifier("userIdeaDao")
	@Autowired
	private UserIdeaDao userIdeaDao;

	@Override
	public List<UserIdeaModel> getMyIdeaList(Long id) {
		return userIdeaDao.getMyIdeaList(id);
	}
	

	
}
