package com.linfeng.front.vo;

public enum MenuVO {
	index("首页","/index","_slef"),
	race("深圳校园服装大赛","/race","_slef"),
	bbs("CRAZY社区","/bbs","_slef"),
	contact("联系我们","/contact","_slef"),
	partner("合作伙伴","/partner","_slef");
	
	
	private MenuVO(String title, String url, String opener) {
		this.title = title;
		this.url = url;
		this.opener = opener;
	}
	private String title;
	private String url;
	private boolean isActive;
	private String opener;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getOpener() {
		return opener;
	}
	public void setOpener(String opener) {
		this.opener = opener;
	}
	
	
}
