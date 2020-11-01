package com.wtu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wtu.dao.UserDao;
import com.wtu.entity.Page;
import com.wtu.entity.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/queryUserList")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		String userName = request.getParameter("userName");
		String name = request.getParameter("chrName");
		String eamil = request.getParameter("eamil");
		String pId = request.getParameter("pId");
		String sortField = request.getParameter("sortField");
		String sortMode = request.getParameter("sortMode");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		Page page = userDao.queryUserList(userName, name, eamil, pId, sortField, sortMode, pageNumber, pageSize);
		PrintWriter printWriter = response.getWriter();
		// 返回前端数据
		printWriter.print(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
