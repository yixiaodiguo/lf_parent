package com.linfeng.front.service;

import org.lifeng.common.service.BaseService;

import com.linfeng.front.model.UserVoteModel;

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
public interface UserVoteService extends BaseService<UserVoteModel, Long>{

	boolean hasVoteToday(Long userId, Long ideaId);

}
