package com.wtu.entity;


/**
 * �û�ʵ����
 * @author XL
 *
 */
public class User {

	/**
	 * �û���½��
	 */
	private String userName;
	/**
	 * �û�����
	 */
	private String password;
	/**
	 * �û���ʵ����
	 */
	private String name;
	/**
	 * �û���ɫ
	 */
	private String role;
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
	
	
	
	
	
}
