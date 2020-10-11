package com.wtu.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wtu.dao.DownloadListDao;
import com.wtu.entity.DownloadList;
import com.wtu.tools.CaptchaTool;

/**
 * 获取资源控制器
 * @author FQ
 *
 */
@WebServlet("/queryResource")
public class DownloadListController extends HttpServlet{
	
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("有权限执行了-----------------");

		// 获取资源类型 id ---- 1：资源下载 2：用户管理  3：资源管理 4：个人中心
		String roleId = request.getParameter("roleId");
		if("1".equals(roleId)){
			// 如果是资源下载
			DownloadListDao downloadListDao = new DownloadListDao();
			List<DownloadList> allDownloadList = downloadListDao.queryAllDownloadList();
			request.setAttribute("allDownloadList", allDownloadList);
			// 跳转到资源下载页面
			request.getRequestDispatcher("/downloadList.jsp").forward(request, response);
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
