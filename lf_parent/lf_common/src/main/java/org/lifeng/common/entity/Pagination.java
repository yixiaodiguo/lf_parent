package org.lifeng.common.entity;

import java.util.List;

import org.lifeng.common.Constants;

/**
 * 
 * Description: 分页封装
 * Copyright: Copyright (c)2012
 * @author: wxm
 * @version: 1.0
 * Create at: 2014-5-10 上午4:18:43
 *
 * Modification History:
 * Date Author Version Description
 * ---------------------------修改历史---------------------------------------
 * 2014-5-10 wxm 1.0
 */
public class Pagination<T>{
	//页面记录条数
	private int pageSize = Constants.DEFAULT_PAGE_SIZE;
	//总记录数
	private long totalCount;
	//总页面数
	private int totalPages;
	//当前页面
	private int currentPage = 1;
	//上一页
	private int prevPage;
	//下一页
	private int nextPage;
	//模糊查询字段
	private String propertyName;
	//查询关键字
	private String keyword;
	//排序字段
	private String orderBy;
	//排序
	private OrderType orderType = OrderType.ASC;
	//分页查询结果集
	private List<T> items;
	
	
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Pagination() {
		super();
	}
	
	public Pagination(int currentPage) {
		super();
		this.currentPage = currentPage;
	}
	
	public Pagination(int currentPage,int pageSize) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}
	
	public Pagination(int currentPage,int pageSize,int totalItems) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		setTotalCount(totalCount);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public boolean hasPrevPage() {
		return currentPage > 1;
	}

	public int getPrevPage() {
		if (hasPrevPage()) prevPage = currentPage - 1;
		else prevPage = 1;
		return prevPage;
	}

	public int getNextPage() {
		if(hasNextPage()) nextPage = currentPage + 1;
		else nextPage = totalPages;
		return nextPage;
	}
	
	public boolean hasNextPage() {
		return currentPage < totalPages;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 排列顺序
	 * @author wxm
	 */
	public enum OrderType{
		ASC,DESC
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
