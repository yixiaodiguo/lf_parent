package org.lifeng.common.dao;

import java.io.Serializable;

import org.lifeng.common.model.AbstractModel;

public interface CommonDao {
    
	public <T extends AbstractModel> T save(T model);

    public <T extends AbstractModel> void saveOrUpdate(T model);
    
    public <T extends AbstractModel> void update(T model);
    
    public <T extends AbstractModel> void merge(T model);

    public <T extends AbstractModel, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    public <T extends AbstractModel> void deleteObject(T model);

	public <T extends AbstractModel, PK extends Serializable> T get(Class<T> entityClass, PK id);
}
