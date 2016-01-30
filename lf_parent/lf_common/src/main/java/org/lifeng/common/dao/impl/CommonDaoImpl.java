package org.lifeng.common.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lifeng.common.dao.CommonDao;
import org.lifeng.common.model.AbstractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CommonDao")
public class CommonDaoImpl implements CommonDao {
    
	@Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    } 
    
    
    public <T extends AbstractModel> T save(T model) {
        getSession().save(model);
        return model;
    }

    public <T extends AbstractModel> void saveOrUpdate(T model) {
        getSession().saveOrUpdate(model);
        
    }
    
    public <T extends AbstractModel> void update(T model) {
        getSession().update(model);
    }
    
    public <T extends AbstractModel> void merge(T model) {
        getSession().merge(model);
    }

    public <T extends AbstractModel, PK extends Serializable> void delete(Class<T> entityClass, PK id) {
        getSession().delete(get(entityClass, id));
    }

    public <T extends AbstractModel> void deleteObject(T model) {
        getSession().delete(model);
    }

    @SuppressWarnings("unchecked")
	public <T extends AbstractModel, PK extends Serializable> T get(Class<T> entityClass, PK id) {
        return (T) getSession().get(entityClass, id);
    }
    
}
