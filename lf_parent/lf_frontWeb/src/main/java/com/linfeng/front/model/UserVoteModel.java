package com.linfeng.front.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.lifeng.common.model.AbstractModel;
/**
 * 角色实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "user_vote")
public class UserVoteModel extends AbstractModel {

	private static final long serialVersionUID = -6106864260693009257L;
	
	@Column(columnDefinition = "varchar(16) COMMENT 'ip地址'")
	private String IP;

	@Column(columnDefinition = "varchar(100) COMMENT '创意ID'")
	private Long userIdeaId;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER, optional=true)
	@JoinColumn(name="userInfoId")
	private UserInfoModel user;

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Long getUserIdeaId() {
		return userIdeaId;
	}

	public void setUserIdeaId(Long userIdeaId) {
		this.userIdeaId = userIdeaId;
	}

	public UserInfoModel getUser() {
		return user;
	}

	public void setUser(UserInfoModel user) {
		this.user = user;
	}
}
