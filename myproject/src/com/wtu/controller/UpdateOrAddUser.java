package com.wtu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wtu.dao.UserDao;
import com.wtu.entity.User;

/**
 * Servlet implementation class UpdateOrAddUser
 */
@WebServlet("/updateOrAddUser")
public class UpdateOrAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrAddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 修改用户或者删除用户
		String userName = request.getParameter("userName");
		String name = request.getParameter("chrName");
		String password = request.getParameter("password");
		String eamil = request.getParameter("eamil");
		String pId = request.getParameter("pId");
		String cId = request.getParameter("cId");
		// 通过action判断修改或者增加
		String action = request.getParameter("action");
		User user = new User();
		user.setName(name);
		user.setpId(Long.parseLong(pId));
		user.setEamil(eamil);
		user.setcId(Long.parseLong(cId));
		user.setUserName(userName);
		user.setPassword(password);
		UserDao userDao = new UserDao();
		if("update".equals(action)) {
			// 修改
			userDao.updateUser(user);
		}else {
			// 新增
			userDao.registerUser(user);
		}
	}

	
	
	
	
	
	
	
	
	
}
