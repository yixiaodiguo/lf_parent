package org.lifeng.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.lifeng.common.entity.Pagination;

public interface BaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {

	public abstract Session getSession();

	public abstract M save(M model);

	public abstract void saveOrUpdate(M model);

	public abstract void delete(PK id);

	public abstract void deleteObject(M model);

	public abstract void delete(PK[] ids);

	public abstract void update(M model);
	
	public abstract M merge(M model);

	public abstract M get(PK id);

	public abstract M load(PK id);

	public abstract boolean exists(PK id);

	public abstract List<M> get(@SuppressWarnings("unchecked") PK... ids);

	public abstract M get(String propertyName, Object value);

	public abstract List<M> getList(String propertyName, Object value);
	
	public abstract <T> T unique(final String hql, final Object... params);
	
	public abstract List<M> listAll();

	public abstract Long countAll();

	public abstract Long countAll(final String hql, final Object... params);

	public abstract Long countAll(final String hql, final Map<String, Collection<?>> map, final Object... params);

	public abstract Long countAll(final Criteria criteria);

	public abstract <T> T unique(Criteria criteria);

	public abstract <T> List<T> list(Criteria criteria);

	public abstract <T> T unique(DetachedCriteria criteria);

	public abstract <T> List<T> list(DetachedCriteria criteria);

	public abstract void flush();

	public abstract void clear();

	public abstract void listByPage(final Pagination<M> pagination);
	
	public abstract void listByPage(final Pagination<M> pagination, String hql, final Object... params);
	
	public abstract void listByPage(final Pagination<M> pagination, String hql, final Map<String, Collection<?>> map, final Object... params);
}