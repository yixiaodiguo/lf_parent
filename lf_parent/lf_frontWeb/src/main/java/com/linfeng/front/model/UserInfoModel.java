package com.linfeng.front.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.lifeng.common.model.AbstractModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Description: 系统用户实体类
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-10 上午2:04:35
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-10 wxm 1.0
 */
@Entity
@Table(name = "user_info")
public class UserInfoModel extends AbstractModel {
	private static final long serialVersionUID = -1499425054476665220L;
	
	@Column(unique = true, nullable = false, length = 100, columnDefinition = "varchar(50) COMMENT '用户名'")
	private String userName;

	@Column(unique = true, length = 100, columnDefinition = "varchar(50) COMMENT '密码'")
	private String userPwd;

	@Column(length = 100, columnDefinition = "varchar(50) COMMENT '真实姓名'")
	private String trueName;

	@Column(nullable = false, columnDefinition = "tinyint default '2' COMMENT '性别'")
	//@Enumerated(EnumType.ORDINAL)
	private Integer gender;
	
	@Column(columnDefinition = "tinyint default 0 COMMENT '显示顺序'")
	private int priority = 1;
	
	@Column(columnDefinition = "varchar(100) default 0 COMMENT '用户头像'")
	private String icon;
	
	@Column(columnDefinition = "date COMMENT '出生日期'")
	//@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@OneToMany(targetEntity=UserIdeaModel.class,mappedBy="user",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<UserIdeaModel> ideas = new HashSet<UserIdeaModel>();

	@Column(length = 1, columnDefinition = "tinyint default '0' COMMENT '0否 ,1是'")
	private Short locked;
	
	@Column(length = 1, columnDefinition = "varchar(11) default NULL COMMENT '手机'")
	private String phone;
	
	@Column(length = 1, columnDefinition = "varchar(16) default NULL COMMENT 'QQ'")
	private String qq;
	
	@Transient
	private String confirmPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<UserIdeaModel> getIdeas() {
		return ideas;
	}

	public void setIdeas(Set<UserIdeaModel> ideas) {
		this.ideas = ideas;
	}

	public Short isLocked() {
		return locked;
	}

	public void setLocked(Short locked) {
		this.locked = locked;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Short getLocked() {
		return locked;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
