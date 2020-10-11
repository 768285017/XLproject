package com.wtu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wtu.dao.RoleDao;
import com.wtu.entity.User;





public class ProjectFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsq = (HttpServletRequest)req;
		//取得SESSION中的loginUser对象
//		User user = (User)hsq.getSession().getAttribute("loginUser");
		HttpSession session = hsq.getSession();
		User user = (User) session.getAttribute("loginUser");
		// 获取请求资源路径
		String requestPath = hsq.getServletPath();
		if(null == user){
			//如果用户没有登录，那么将会直接跳转到登陆页面
			req.getRequestDispatcher("/login.html").forward(req, resp);
			
		}else{
			// 用户已经登陆了
			if(null != requestPath && requestPath.equals("/queryResource")){
				// 判断是否有权限查看资源
				RoleDao roleDao = new RoleDao();
				// 获取用户名和资源id
				String roleId = hsq.getParameter("roleId");
				int flag = roleDao.getRole(user.getUserName(), Integer.valueOf(roleId));
				// 返回值flag若为1则有权限 为0则没有权限
				if(flag == 1){
//					hsq.setAttribute("info","有权限但是页面正在建设中");
//					hsq.getRequestDispatcher("/error.jsp").forward(hsq, resp); 
					chain.doFilter(hsq, resp);	
				}else{
					// 无权限
					hsq.setAttribute("info","不好意思没有权限");
					hsq.getRequestDispatcher("/error.jsp").forward(hsq, resp); 
				}
				
			}else{
				chain.doFilter(hsq, resp);	
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
