package org.lifeng.common.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.lifeng.common.dao.BaseDao;
import org.lifeng.common.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Description: 
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-10 上午4:31:08
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-10 wxm 1.0
 */
public abstract class BaseDaoImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements BaseDao<M, PK>{

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private String pkName;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass()
        		.getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getSuperclass().getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName();
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }
        
    @Autowired
    private SessionFactory sessionFactory;

    @Override
	public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@SuppressWarnings("unchecked")
	@Override
    public M save(M model) {
        return (M) getSession().save(model);
    }

	@Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }

	@Override
    public void delete(PK id) {
        getSession().delete(this.get(id));

    }

	@Override
    public void deleteObject(M model) {
        getSession().delete(model);
    }
    
    @Override
	public void delete(PK[] ids) {
    	String hql = "delete from " + entityClass.getName() + " as model where model."+pkName+" in(:ids)";
    	getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
	}
    
    
	@Override
    public void update(M model) {
        getSession().update(model);
    }

	@SuppressWarnings("unchecked")
	@Override
    public M merge(M model) {
        return (M) getSession().merge(model);
    }

	@Override
	@SuppressWarnings("unchecked")
    public M get(PK id) {
       return (M) getSession().get(this.entityClass, id);
    }
    
    @Override
	@SuppressWarnings("unchecked")
	public M load(PK id) {
		return (M) getSession().load(entityClass, id);
	}

	@Override
    public boolean exists(PK id) {
        return get(id) != null;
    }

    @Override
	@SuppressWarnings("unchecked")
	public List<M> get(PK... ids) {
		String hql = "from " + entityClass.getName() + " as model where model."+pkName+" in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}
    
    @Override
	@SuppressWarnings("unchecked")
	public M get(String propertyName, Object value) {
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (M) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
    
	@Override
	@SuppressWarnings("unchecked")
	public List<M> getList(String propertyName, Object value) {
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public M unique(final String hql, final Object... params) {
        Query query = getSession().createQuery(hql);
        setParameters(query, params);
        return (M) query.setMaxResults(1).uniqueResult();
    }
	
    @Override
	@SuppressWarnings("unchecked")
    public List<M> listAll(){
    	return getSession().createQuery(HQL_LIST_ALL).list();
    }
    
	@Override
	public Long countAll(){
		return (Long) getSession().createQuery(HQL_COUNT_ALL).uniqueResult();
	}
	
	@Override
	public Long countAll(final String hql, final Object... params) {
		return countAll(hql,null,params);
	}
	
	@Override
	public Long countAll(final String hql, final Map<String, Collection<?>> map, final Object... params) {
		String countHQL = "select count(*) " + removeSelect(hql);
		Query query = getSession().createQuery(countHQL);
        setParameters(query, params);
        if(null != map){
	        for (Entry<String, Collection<?>> e : map.entrySet()) {
	            query.setParameterList(e.getKey(), e.getValue());
	        }
        }
        return (Long) query.uniqueResult();
	}
	
	@Override
	public Long countAll(final Criteria criteria) {
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public M unique(Criteria criteria) {
		return (M) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<M> list(Criteria criteria) {
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public M unique(DetachedCriteria criteria) {
		return (M) unique(criteria.getExecutableCriteria(getSession()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> list(DetachedCriteria criteria) {
		return list(criteria.getExecutableCriteria(getSession()));
	}

	@Override
    public void flush() {
        getSession().flush();
    }

	@Override
    public void clear() {
        getSession().clear();
    }

    @Override
    public void listByPage(final Pagination<M> pagination) {
    	Query query = getSession().createQuery(HQL_LIST_ALL);
    	pagination.setTotalCount(countAll());
    	queryForPage(pagination, query);
    }
    
    @Override
	public void listByPage(final Pagination<M> pagination, String hql, final Object... params) {
    	listByPage(pagination, hql, null, params);
   }
    
    @Override
    public void listByPage(final Pagination<M> pagination, String hql, final Map<String, Collection<?>> map, final Object... params) {
    	Query query = getSession().createQuery(hql);
    	pagination.setTotalCount(countAll(hql,map,params));
    	setParameters(query, params);
    	for (Entry<String, Collection<?>> e : map.entrySet()) {
            query.setParameterList(e.getKey(), e.getValue());
        }
    	queryForPage(pagination, query);
    }

	@SuppressWarnings("unchecked")
	private void queryForPage(final Pagination<M> pagination, Query query) {
		query.setFirstResult((pagination.getCurrentPage() - 1) * pagination.getPageSize());
    	query.setMaxResults(pagination.getPageSize());
    	pagination.setItems(query.list());
	}
    
    /**
     * 执行批处理语句.如 之间insert, update, delete 等.
     */
    protected int execteBulk(final String hql, final Object... params) {
        Query query = getSession().createQuery(hql);
        setParameters(query, params);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    protected int execteNativeBulk(final String natvieSQL, final Object... params) {
        Query query = getSession().createSQLQuery(natvieSQL);
        setParameters(query, params);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    /**
     * 设置条件参数
     * @param query
     * @param params
     */
    protected void setParameters(Query query, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof Date) {
                    query.setTimestamp(i, (Date)params[i]);
                } else {
                    query.setParameter(i, params[i]);
                }
            }
        }
    }

    /**
	 * 去除hql的select 子句，未考虑union的情况用于pagedQuery.
	 * 
	 * @param hql
	 * 
	 * @return
	 */
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unused")
	private String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
