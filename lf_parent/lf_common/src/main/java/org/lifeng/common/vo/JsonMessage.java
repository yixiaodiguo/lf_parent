package org.lifeng.common.vo;

import java.util.Map;

/**
 * JSON 消息VO
 * @author wangxueming
 *
 */
public class JsonMessage {

	private int status;
	private int msg;
	
	private Map<String, Object> info;
	
	public JsonMessage() {
		super();
	}

	public JsonMessage(int status, int msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public JsonMessage(int status, int msg, Map<String, Object> info) {
		super();
		this.status = status;
		this.msg = msg;
		this.info = info;
	}

	public int getStatus() {
		return status;
	}

	public JsonMessage setStatus(int status) {
		this.status = status;
		return this;
	}

	public int getMsg() {
		return msg;
	}

	public JsonMessage setMsg(int msg) {
		this.msg = msg;
		return this;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public JsonMessage setInfo(Map<String, Object> info) {
		this.info = info;
		return this;
	}

	@Override
	public String toString() {
		return "JsonMessage [status=" + status + ", msg=" + msg + ", info=" + info + "]";
	}
	
}
