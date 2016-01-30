package org.lifeng.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.lifeng.common.entity.Pagination;

public interface BaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public M save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);
    
    public void deleteObject(M model);

    public void deleteMore(@SuppressWarnings("unchecked") PK...ids);

    public M get(PK id);
    
	public List<M> getMore(@SuppressWarnings("unchecked") PK...ids);
    
    public long countAll();
    
    public List<M> listAll();
    
    public abstract void listByPage(final Pagination<M> pagination);
	
	public abstract void listByPage(final Pagination<M> pagination, String hql, final Object... params);
	
	public abstract void listByPage(final Pagination<M> pagination, String hql, final Map<String, Collection<?>> map, final Object... params);
    
}
