package com.wtu.entity;

/**
 * 城市实体类
 * @author Administrator
 *
 */
public class City {

	/**
	 * 城市id
	 */
	private Long id;
	/**
	 * 城市名称
	 */
	private String name;
	/**
	 * 城市所属省份id
	 */
	private Long pId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	
	
}
