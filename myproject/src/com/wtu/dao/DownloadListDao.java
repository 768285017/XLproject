package com.wtu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wtu.entity.DownloadList;
import com.wtu.entity.User;

/**
 * 文件下载dao
 * @author XL
 *
 */
public class DownloadListDao {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/myproject?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
	// 数据库的登陆账号密码
	static final String USER = "root";
	static final String PASS = "123456";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	/**
	 * 查询所有的文件
	 */
	public List<DownloadList> queryAllDownloadList(){
		List allDownloadList = new ArrayList();
		int i = 0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			String sql;
			sql = "select * from `t_downloadlist`";
			rs = stmt.executeQuery(sql);
	        while(rs.next()){
	        	DownloadList downloadList = new DownloadList();
	        	downloadList.setId(rs.getLong("id"));
	        	downloadList.setName(rs.getString("name"));
	        	downloadList.setPath(rs.getString("path"));
	        	downloadList.setDescription(rs.getString("description"));
	        	downloadList.setSize(rs.getLong("size"));
	        	downloadList.setStar(rs.getInt("star"));
	        	downloadList.setImage(rs.getString("image"));
	        	downloadList.setTime(rs.getDate("time"));
	        	allDownloadList.add(downloadList);
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
		return allDownloadList;
	}
	
	/**
	 * 通过文件id获取文件详细信息
	 */
	public DownloadList queryDownloadById(Long id){
		DownloadList downloadList = new DownloadList();
		int i = 0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 执行sql查询
			stmt = conn.createStatement();
			String sql;
			sql = "select * from `t_downloadlist` where id = " + id + "";
			rs = stmt.executeQuery(sql);
	        while(rs.next()){
	        	downloadList.setId(rs.getLong("id"));
	        	downloadList.setName(rs.getString("name"));
	        	downloadList.setPath(rs.getString("path"));
	        	downloadList.setDescription(rs.getString("description"));
	        	downloadList.setSize(rs.getLong("size"));
	        	downloadList.setStar(rs.getInt("star"));
	        	downloadList.setImage(rs.getString("image"));
	        	downloadList.setTime(rs.getDate("time"));
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
		return downloadList;
	}
	
}
