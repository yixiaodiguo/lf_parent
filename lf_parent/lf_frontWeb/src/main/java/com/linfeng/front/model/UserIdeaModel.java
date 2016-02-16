package com.linfeng.front.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.lifeng.common.model.AbstractModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 角色实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "user_idea")
public class UserIdeaModel extends AbstractModel {

	private static final long serialVersionUID = -6106864260693009257L;
	
	@Column(unique = true, columnDefinition = "varchar(100) NOT NULL COMMENT '创意名称'")
	private String title;

	@Column(columnDefinition = "int default 0 COMMENT '显示顺序'")
	@JsonIgnore
	private int priority = 1;

	@Column(columnDefinition = "varchar(100) COMMENT '创建图片'")
	private String picture;

	@Column(columnDefinition = "varchar(100) COMMENT '备注'")
	private String remark;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER, optional=true)
	@JoinColumn(name="userId")
	private UserInfoModel user;

	@Column(columnDefinition = "char(1) default 'Y' COMMENT '是否可用'")
	@Type(type = "yes_no")
	@JsonIgnore
	private boolean available = true;
	
	@Column(columnDefinition = "bigint default '1' COMMENT '赞的个数'")
	private Long great;
	
	@Transient
	private int praiased; 

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserInfoModel getUser() {
		return user;
	}

	public void setUser(UserInfoModel user) {
		this.user = user;
	}

	public Long getGreat() {
		return great;
	}

	public void setGreat(Long great) {
		this.great = great;
	}

	public int getPraiased() {
		return praiased;
	}

	public void setPraiased(int praiased) {
		this.praiased = praiased;
	}
	
}
