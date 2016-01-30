package org.lifeng.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.lifeng.common.service.CommonService;
import org.lifeng.common.util.SpringContextUtil;

/**
 * 
 * Description: POJO类的父类
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-3-22 下午1:34:30
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-3-22 wxm 1.0
 */
@MappedSuperclass
public abstract class AbstractModel implements Serializable{

	private static final long serialVersionUID = 2388945108127461852L;

	@Id
	/*@GenericGenerator(name = "uuidGenerator", strategy = "com.lf.common.entity.Base64UuidGenerator")
	@Type(type="java.lang.String")
	@GeneratedValue(generator = "uuidGenerator")*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition="bigint COMMENT '编号主键'")
	public Long id;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'",updatable = false)
	public Date lastUpdate;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间'")
	public Date createTime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == this.id) return false;
		if(obj instanceof AbstractModel){
			Long id = ((AbstractModel)obj).getId();
			if(null == id) return false;
			return this.id.equals(id);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
	public void save() {
		CommonService commonService = SpringContextUtil.getBean("CommonService");
		commonService.save(this);
	}
	
	public void saveOrUpdate() {
		CommonService commonService = SpringContextUtil.getBean("CommonService");
		commonService.saveOrUpdate(this);
	}
	
	public void merge() {
        CommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.merge(this);
    }
    
    public void delete() {
        CommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.deleteObject(this);
    }
    
    public void update() {
        CommonService commonService = SpringContextUtil.getBean("CommonService");
        commonService.update(this);
    }
    
    public <T extends AbstractModel, PK extends Serializable> void delete(Class<T> entityClass, PK id){
    	 CommonService commonService = SpringContextUtil.getBean("CommonService");
    	 commonService.delete(entityClass, id);
    }

    public <T extends AbstractModel, PK extends Serializable> T get(Class<T> entityClass, PK id){
    	CommonService commonService = SpringContextUtil.getBean("CommonService");
   	    return commonService.get(entityClass, id);
    }
}
