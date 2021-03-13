package cn.tp.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tp.pojo.OptionBean;
import cn.tp.pojo.User;
import cn.tp.pojo.VoteBean;
import cn.tp.pojo.VoteSubject;
import cn.tp.service.VoteService;
import cn.tp.service.impl.VoteServiceImpl;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/VoteServlet")
public class VoteServlet extends DispatchServlet {
	private static final long serialVersionUID = 1L;
	private VoteService voteService = new VoteServiceImpl();
     
	protected void addVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subjectTitle = request.getParameter("subjectTitle");
		Integer subjectType = Integer.parseInt(request.getParameter("subjectType"));
		String[] options = request.getParameterValues("options");
		boolean result = voteService.addVote(new VoteSubject(subjectTitle,subjectType),options);
		String msg = "发布新投票";
		if(result) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else {
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//初始化首页
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<VoteBean> list = voteService.finVotes();
		request.setAttribute("voteList", list);
		User user = (User)request.getSession().getAttribute("user");
		//判断是否是管理员
		if(user != null && user.getFlag()==0) {
			request.getRequestDispatcher("manager.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	protected void showVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		VoteSubject voteSubject = voteService.showSubject(vsId);
		Integer optionCount=Integer.parseInt(request.getParameter("optionCount"));
		Integer userCount=Integer.parseInt(request.getParameter("userCount"));
		//将投票的信息封装在VoteBean中
		VoteBean voteBean =  new VoteBean(voteSubject.getVsTitle(),userCount,optionCount);
		//将VoteBean保存在session中
		request.getSession().setAttribute("voteBean", voteBean);
		request.setAttribute("voteSubject", voteSubject);
		request.getRequestDispatcher("vote.jsp").forward(request, response);
	}
	//添加投票信息时，检查投票内容是否重复
	protected void checkTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subjectTitle = request.getParameter("subjectTitle");
		if(subjectTitle != null) {
			  boolean result = voteService.findByTitle(subjectTitle);
			if(result) {
				response.getWriter().print(true);;
			}else {
				response.getWriter().print(false);
			}
		}
	}
	//进行投票操作
	protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		Integer vuId = ((User)request.getSession().getAttribute("user")).getId();
		String[] voIds = request.getParameterValues("options");
		int result = voteService.vote(vsId,voIds,vuId);
		String msg = "投票";
		if(result>0) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else if(result==0){
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else if(result==-1){
			msg="你已经参与过了该内容的投票，无法重复投票,投票";
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//显示投票的统计结果
	protected void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		List<OptionBean> optionBeans = voteService.view(vsId);
		request.setAttribute("optionBeans", optionBeans);
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}
	//修改投票前显示投票全部信息
	protected void preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		VoteSubject voteSubject = voteService.showSubject(vsId);
		request.setAttribute("voteSubject", voteSubject);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		String subjectTitle = request.getParameter("subjectTitle");
		Integer subjectType = Integer.parseInt(request.getParameter("subjectType"));
		String[] options = request.getParameterValues("options");
		int result = voteService.edit(new VoteSubject(vsId,subjectTitle,subjectType),options);
		String msg = "修改投票内容";
		if(result > 0) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else {
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//根据title查询信息
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vsTitle = request.getParameter("vsTitle");
		List<VoteBean> list = voteService.query(vsTitle);
		request.setAttribute("voteList", list);
		request.setAttribute("vsTitle", vsTitle);
		User user = (User)request.getSession().getAttribute("user");
		//判断是否是管理员
		if(user != null && user.getFlag()==0) {
			request.getRequestDispatcher("manager.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
