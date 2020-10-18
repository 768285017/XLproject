package com.wtu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * @param user2
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

}

