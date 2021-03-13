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

public class ManagerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String op = request.getParameter("option");
		User user = (User) request.getSession().getAttribute("user");
		if (op != null) {
			if ("addVote".equals(op) || "checkTitle".equals(op) || "preEdit".equals(op) || "edit".equals(op)) {
				if (user.getFlag() == 0) {
					chain.doFilter(request, response);
				} else {
					response.sendRedirect("error.jsp?msg="+URLEncoder.encode("权限不足，无法操作。请联系管理员!", "UTF-8"));
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (user.getFlag() == 0) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode("权限不足，无法操作。请联系管理员!", "UTF-8"));
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}
