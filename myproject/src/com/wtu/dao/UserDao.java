package com.wtu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wtu.entity.Page;
import com.wtu.entity.User;


public class UserDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/myproject?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	// ���ݿ�ĵ�½�˺�����
	static final String USER = "root";
	static final String PASS = "123456";
	// �û���½
	public User login(String userName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			String sql;
			sql = "select * from user where user_name = \'" + userName + "\'";
			rs = stmt.executeQuery(sql);
			 //4.�������ݿ�ķ��ؽ��(ʹ��ResultSet��)
	        while(rs.next()){
	        	user.setUserName(rs.getString("user_name"));
	        	user.setRole(rs.getString("role"));
	        	user.setName(rs.getString("name"));
	        	user.setPassword(rs.getString("password"));
	        }
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	/**
	 * �û�ע��
	 * @param user
	 */
	public void registerUser(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			String sql;
			sql = "insert into user(user_name, name, email, pId, cId, password) values( ?, ?, ?, ?, ?, ?)";
//			rs = stmt.executeQuery(sql);
			// ����ֵ
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, user.getUserName());
			ptmt.setString(2, user.getName());
			ptmt.setString(3, user.getEamil());
			ptmt.setLong(4, user.getpId());
			ptmt.setLong(5, user.getcId());
			ptmt.setString(6, user.getPassword());
			ptmt.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ����������ѯ�û�List
	 * @param userName : �û���
	 * @param name : ��ʵ����
	 * @param email : ����
	 * @param provinceId : ʡ��id
	 * @param sortField : �����ֶ�
	 * @param sortMode : ����ʽ
	 * @param pageNumber : ҳ��
	 * @param pageSize : ÿҳչʾ��¼��
	 * @return
	 */
	public Page queryUserList(String userName, String name, String email, String provinceId, String sortField,
			String sortMode, String pageNumber, String pageSize) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// ���û�д���ÿҳ��¼��Ĭ��Ϊ10��
		if(null == pageNumber || pageNumber == "") {
			pageNumber = "0";
		}
		if(null == pageSize || pageSize == "") {
			pageSize = "10";
		}
		if(null == sortMode || sortMode == "") {
			sortMode = "desc";
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			String sql = "select * from `user` where 1=1";
			if(null != userName && userName != "") {
				sql += "and user_name like %" + userName + "%";
			}
			if(null != name && name != "") {
				sql += "and `name` like %" + name + "%";
			}
			if(null != email && email != "") {
				sql += "and email like %" + email + "%";
			}
			if(null != provinceId && provinceId != "") {
				sql += "and pId = " + provinceId;
			}
			if(null != sortField && sortField != "") {
				sql += "order by " + sortField + "" + sortMode;
			}
			sql += "limit " + pageNumber + " " + pageSize;
			rs = stmt.executeQuery(sql);
			// ����ֵ
//			PreparedStatement ptmt = conn.prepareStatement(sql);
			List<User> users = new ArrayList<User>();
			while(rs.next()){
				User user = new User();
	        	user.setUserName(rs.getString("user_name"));
	        	user.setRole(rs.getString("role"));
	        	user.setName(rs.getString("name"));
	        	user.setPassword(rs.getString("password"));
	        	user.setEamil(rs.getString("email"));
	        	user.setcId(rs.getLong("cId"));
	        	user.setpId(rs.getLong("pId"));
	        	users.add(user);
	        }
			// ���÷�ҳ����
			Page userPage = new Page();
			userPage.setContentList(users);
			// ������ҳ��
			userPage.setCount(users.size() / Long.valueOf(pageNumber));
			// ���õ�ǰҳ
			userPage.setPageNumber(Long.valueOf(pageNumber));
			// ����ÿҳչʾ����
			userPage.setPageSize(Long.valueOf(pageSize).intValue());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * �����û�idɾ���û�
	 */
	public void deleteUserByIds(String ids[]) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			// ����ɾ���û�
			for (int i = 0; i < ids.length; i++) {
				String sql = "delete from user where id = " + ids[i];
				rs = stmt.executeQuery(sql);
			}
			// ����ֵ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �޸��û���Ϣ
	 */
	public void updateUser(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			// �޸��û�
//			String sql = "update user(user_name, name, email, pId, cId, password) values( ?, ?, ?, ?, ?, ?)";
			String sql = "update user set user_name = ?, name = ?, email = ?, pId = ?, cId = ?, password = ? where id = ?";
//			rs = stmt.executeQuery(sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, user.getUserName());
			ptmt.setString(3, user.getEamil());
			ptmt.setString(4, user.getpId().toString());
			ptmt.setString(5, user.getcId().toString());
			ptmt.setString(6, user.getPassword());
			ptmt.setString(7, user.getName());
			ptmt.executeQuery();
			// ����ֵ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �������Ʋ�ѯ�û���Ϣ
	 */
	public User queryUserByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			// �޸��û�
			String sql = "select * from user where name = ?";
//			rs = stmt.executeQuery(sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, name);
			rs = ptmt.executeQuery();
			while(rs.next()){
				user.setcId(rs.getLong("cId"));
				user.setEamil(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setpId(rs.getLong("pId"));
				user.setRole(rs.getString("role"));
				user.setUserName(rs.getString("user_name"));
			}
			// ����ֵ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	/**
	 * �������Ʋ�ѯ�û���Ϣ
	 */
	public List<User> queryUserList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			// �޸��û�
			String sql = "select * from user";
//			rs = stmt.executeQuery(sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setcId(rs.getLong("cId"));
				user.setEamil(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setpId(rs.getLong("pId"));
				user.setRole(rs.getString("role"));
				user.setUserName(rs.getString("user_name"));
				userList.add(user);
			}
			// ����ֵ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}
}

