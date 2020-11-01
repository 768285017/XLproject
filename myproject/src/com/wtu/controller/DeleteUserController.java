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
 * 用户控制层
 */
@WebServlet("/deleteUserController")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *
	 **/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查询所有用户返回list
		UserDao userDao = new UserDao();
		// 查询条件：用户名、姓名、邮箱地址、省份id、排序字段、排序方式、页码、每页展示数据条数
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String provinceId = request.getParameter("provinceId");
		String sortField = request.getParameter("sortField");
		String sortMode = request.getParameter("sortMode");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		Page userPage = userDao.queryUserList(userName, name, email, provinceId, 
				sortField, sortMode, pageNumber, pageSize);
		// 返回数据
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.write(userPage.toString());
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("ids");
		// 删除根据ids删除ids
		UserDao userDao = new UserDao();
		// 将Stingids转化为ids数组
		String[] intIds = ids.split(",");
		userDao.deleteUserByIds(intIds);
		doGet(request, response);
	}

}
