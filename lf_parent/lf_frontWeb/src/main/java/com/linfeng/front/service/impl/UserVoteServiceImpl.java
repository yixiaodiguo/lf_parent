package com.linfeng.front.service.impl;

import org.lifeng.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linfeng.front.dao.UserVoteDao;
import com.linfeng.front.model.UserVoteModel;
import com.linfeng.front.service.UserVoteService;

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
@Service("userVoteService")
public class UserVoteServiceImpl extends BaseServiceImpl<UserVoteModel, Long> implements UserVoteService {

	@Autowired
	private UserVoteDao userVoteDao;
	
	@Override
	public boolean hasVoteToday(Long userId, Long ideaId) {
		return userVoteDao.hasVoteToday(userId, ideaId) > 0;
	}

}
