package org.lifeng.common.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.lifeng.common.dao.BaseDao;
import org.lifeng.common.entity.Pagination;
import org.lifeng.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements BaseService<M, PK> {
    
	@Autowired
    protected BaseDao<M, PK> baseDao;
    
    @Override
    public M save(M model) {
        baseDao.save(model);
        return model;
    }
    
    @Override
    public void merge(M model) {
        baseDao.merge(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        baseDao.saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        baseDao.update(model);
    }
    
    @Override
    public void delete(PK id) {
        baseDao.delete(id);
    }

    @Override
    public void deleteObject(M model) {
        baseDao.deleteObject(model);
    }
    
	@Override
	public void deleteMore(@SuppressWarnings("unchecked") PK... ids) {
		baseDao.delete(ids);
	}

    @Override
    public M get(PK id) {
        return baseDao.get(id);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<M> getMore(PK...ids) {
        return baseDao.get(ids);
    }

    @Override
    public long countAll() {
        return baseDao.countAll();
    }

    @Override
    public List<M> listAll() {
        return baseDao.listAll();
    }

	@Override
	public void listByPage(Pagination<M> pagination) {
		baseDao.listByPage(pagination);
	}

	@Override
	public void listByPage(Pagination<M> pagination, String hql,
			Object... params) {
		baseDao.listByPage(pagination, hql, params);
	}

	@Override
	public void listByPage(Pagination<M> pagination, String hql,
			Map<String, Collection<?>> map, Object... params) {
		baseDao.listByPage(pagination, hql, map, params);
	}

}
