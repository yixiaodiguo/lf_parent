package org.lifeng.common.service.impl;

import java.io.Serializable;

import org.lifeng.common.dao.CommonDao;
import org.lifeng.common.model.AbstractModel;
import org.lifeng.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CommonService")
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    @Qualifier("CommonDao")
    private CommonDao commonDao;

    @Override
    public <T extends AbstractModel> T save(T model) {
        return commonDao.save(model);
    }

    @Override
    public <T extends AbstractModel> void saveOrUpdate(T model) {
        commonDao.saveOrUpdate(model);
        
    }
    
    @Override
    public <T extends AbstractModel> void update(T model) {
        commonDao.update(model);
    }
    
    @Override
    public <T extends AbstractModel> void merge(T model) {
        commonDao.merge(model);
    }

    @Override
    public <T extends AbstractModel, PK extends Serializable> void delete(Class<T> entityClass, PK id) {
        commonDao.delete(entityClass, id);
    }

    @Override
    public <T extends AbstractModel> void deleteObject(T model) {
        commonDao.deleteObject(model);
    }

	@Override
	public <T extends AbstractModel, PK extends Serializable> T get(
			Class<T> entityClass, PK id) {
		return commonDao.get(entityClass, id);
	}
    
}
