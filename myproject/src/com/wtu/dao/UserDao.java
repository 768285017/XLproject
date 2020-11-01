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
	// 数据库的登陆账号密码
	static final String USER = "root";
	static final String PASS = "123456";
	// 用户登陆
	public User login(String userName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			String sql;
			sql = "select * from user where user_name = \'" + userName + "\'";
			rs = stmt.executeQuery(sql);
			 //4.处理数据库的返回结果(使用ResultSet类)
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
			//关闭资源
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
	 * 用户注册
	 * @param user
	 */
	public void registerUser(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			String sql;
			sql = "insert into user(user_name, name, email, pId, cId, password) values( ?, ?, ?, ?, ?, ?)";
//			rs = stmt.executeQuery(sql);
			// 设置值
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
			//关闭资源
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
	 * 根据条件查询用户List
	 * @param userName : 用户名
	 * @param name : 真实姓名
	 * @param email : 邮箱
	 * @param provinceId : 省份id
	 * @param sortField : 排序字段
	 * @param sortMode : 排序方式
	 * @param pageNumber : 页数
	 * @param pageSize : 每页展示记录数
	 * @return
	 */
	public Page queryUserList(String userName, String name, String email, String provinceId, String sortField,
			String sortMode, String pageNumber, String pageSize) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 如果没有传入每页记录数默认为10条
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
			// 执行sql查询
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
			// 设置值
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
			// 设置分页参数
			Page userPage = new Page();
			userPage.setContentList(users);
			// 设置总页数
			userPage.setCount(users.size() / Long.valueOf(pageNumber));
			// 设置当前页
			userPage.setPageNumber(Long.valueOf(pageNumber));
			// 设置每页展示数据
			userPage.setPageSize(Long.valueOf(pageSize).intValue());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
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
	 * 根据用户id删除用户
	 */
	public void deleteUserByIds(String ids[]) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			// 批量删除用户
			for (int i = 0; i < ids.length; i++) {
				String sql = "delete from user where id = " + ids[i];
				rs = stmt.executeQuery(sql);
			}
			// 设置值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
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
	 * 修改用户信息
	 */
	public void updateUser(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			// 修改用户
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
			// 设置值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
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
	 * 根据名称查询用户信息
	 */
	public User queryUserByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			// 修改用户
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
			// 设置值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
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
	 * 根据名称查询用户信息
	 */
	public List<User> queryUserList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			// 修改用户
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
			// 设置值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
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

