package com.wtu.entity;

import java.util.List;

public class Page {
	/**
	 * 页数
	 */
	private Long pageNumber;
	/**
	 * 每页展示数
	 */
	private int pageSize;
	/**
	 * 内容List
	 */
	private List<?> contentList;
	/**
	 * 总页数
	 * @return
	 */
	private Long count;
	public Long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<?> getContentList() {
		return contentList;
	}
	public void setContentList(List<?> contentList) {
		this.contentList = contentList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	

}
