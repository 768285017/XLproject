package com.wtu.entity;


/**
 * 用户实体类
 * @author XL
 *
 */
public class User {

	/**
	 * 用户登陆名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户真实姓名
	 */
	private String name;
	/**
	 * 用户角色
	 */
	private String role;
	/**
	 * 用户邮箱
	 */
	private String eamil;
	/**
	 * 用户所属省份id
	 * 
	 */
	private Long pId;
	/**
	 * 用户所属城市id
	 */
	private Long cId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEamil() {
		return eamil;
	}
	public void setEamil(String eamil) {
		this.eamil = eamil;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}
	
	
	
	
	
}
