package com.wtu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wtu.entity.User;


public class RoleDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/myproject?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
	// 数据库的登陆账号密码
	static final String USER = "root";
	static final String PASS = "123456";
	/**
	 * 返回1则有权限  0则没有权限
	 * @param userName
	 * @return
	 */
	public int getRole(String userName, Integer roleId){
		int i = 0; 
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
			sql = "select * from `t_resource` where `id` in (select t_resource_id from `t_role_resource` where t_role_id in "
					+ "(select id from `t_role` where id in (select t_role_id from `t_role_user` as ru where ru.user_name = \'"+ userName +"\'))) "
					+ "and id = " +roleId+ "";
			rs = stmt.executeQuery(sql);
//	        while(rs.next()){
//
//	        }
			if(rs.next()){
				// 若是查询出数据 则有权限查看该资源
				i = 1;
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
		return i;
	}
	
}
