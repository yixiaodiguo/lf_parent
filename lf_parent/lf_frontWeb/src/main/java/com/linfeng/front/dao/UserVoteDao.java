package com.linfeng.front.dao;

import org.lifeng.common.dao.BaseDao;

import com.linfeng.front.model.UserVoteModel;

/**
 * 
 * Description: 
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-30 下午10:26:25
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-30 wxm 1.0
 */
public interface UserVoteDao extends BaseDao<UserVoteModel,Long>{

	int hasVoteToday(Long userId, Long ideaId);
}
