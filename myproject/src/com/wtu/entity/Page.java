package com.wtu.entity;

import java.util.List;

public class Page {
	/**
	 * ҳ��
	 */
	private Long pageNumber;
	/**
	 * ÿҳչʾ��
	 */
	private int pageSize;
	/**
	 * ����List
	 */
	private List<?> contentList;
	/**
	 * ��ҳ��
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
