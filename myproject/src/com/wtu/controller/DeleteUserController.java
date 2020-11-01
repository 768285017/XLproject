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
 * �û����Ʋ�
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
		// ��ѯ�����û�����list
		UserDao userDao = new UserDao();
		// ��ѯ�������û����������������ַ��ʡ��id�������ֶΡ�����ʽ��ҳ�롢ÿҳչʾ��������
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
		// ��������
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
		// ɾ������idsɾ��ids
		UserDao userDao = new UserDao();
		// ��Stingidsת��Ϊids����
		String[] intIds = ids.split(",");
		userDao.deleteUserByIds(intIds);
		doGet(request, response);
	}

}
