package cn.tp.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tp.pojo.User;



public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		User user = (User) request.getSession().getAttribute("user");
		String op = request.getParameter("option");
		
		if ("index".equals(op) || "showView".equals(op) || "showVote".equals(op)) {
			chain.doFilter(request, response);
		} else {
			
			if (user != null) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode("您还未登录，无法进行此操作，", "UTF-8"));
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
