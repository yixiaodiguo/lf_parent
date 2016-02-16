package com.linfeng.front.dao.impl;

import java.util.List;

import org.lifeng.common.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import com.linfeng.front.dao.UserIdeaDao;
import com.linfeng.front.model.UserIdeaModel;
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
@Repository("userIdeaDao")
public class UserIdeaDaoImpl extends BaseDaoImpl<UserIdeaModel,Long> implements UserIdeaDao {

	@Override
	public List<UserIdeaModel> getMyIdeaList(Long id) {
		String hql = "from UserIdeaModel i where i.user.id=?";
		return getSession().createQuery(hql).setLong(0, id).list();
	}
	
}
