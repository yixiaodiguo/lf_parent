package com.linfeng.front.dao.impl;

import java.math.BigInteger;

import org.lifeng.common.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import com.linfeng.front.dao.UserVoteDao;
import com.linfeng.front.model.UserVoteModel;
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
@Repository("userVoteDao")
public class UserVoteDaoImpl extends BaseDaoImpl<UserVoteModel,Long> implements UserVoteDao {

	@Override
	public int hasVoteToday(Long userId, Long ideaId) {
		String sql = "select count(*) from user_vote v where userIdeaId=? and userInfoId=? and to_days(createTime) = to_days(now())";
		return ((BigInteger)(getSession().createSQLQuery(sql).setLong(0, ideaId).setLong(1, userId).uniqueResult())).intValue();
	}
	
}
