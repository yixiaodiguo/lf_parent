package org.lifeng.common.entity;

/**
 * 	
 * Title: JsonMessage.java
 * Description: 封装返回JSON格式的提示信息
 * @author wxm
 * @version 1.0
 * @created 2014-7-29 下午2:39:02
 */
public class JsonMessage {

	private boolean status;
	private String msg;
	private String info;
	
	private JsonMessage(){}
	
	public static JsonMessage getInstance(){
		return new JsonMessage();
	}
	
	public boolean isStatus() {
		return status;
	}
	public JsonMessage setStatus(boolean status) {
		this.status = status;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public JsonMessage setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public String getInfo() {
		return info;
	}
	public JsonMessage setInfo(String info) {
		this.info = info;
		return this;
	}
	
}
