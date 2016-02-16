package com.linfeng.front.vo;

public enum UserMenuVO {
	userInfo("基本资料","/user/info","_slef"),
	myWork("我的作品","/user/myWork","_slef"),
	uploadWork("上传作品","/user/uploadWork","_slef");
	
	private UserMenuVO(String title, String url, String opener) {
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
