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
		String msg = "������ͶƱ";
		if(result) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else {
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//��ʼ����ҳ
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<VoteBean> list = voteService.finVotes();
		request.setAttribute("voteList", list);
		User user = (User)request.getSession().getAttribute("user");
		//�ж��Ƿ��ǹ���Ա
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
		//��ͶƱ����Ϣ��װ��VoteBean��
		VoteBean voteBean =  new VoteBean(voteSubject.getVsTitle(),userCount,optionCount);
		//��VoteBean������session��
		request.getSession().setAttribute("voteBean", voteBean);
		request.setAttribute("voteSubject", voteSubject);
		request.getRequestDispatcher("vote.jsp").forward(request, response);
	}
	//���ͶƱ��Ϣʱ�����ͶƱ�����Ƿ��ظ�
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
	//����ͶƱ����
	protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		Integer vuId = ((User)request.getSession().getAttribute("user")).getId();
		String[] voIds = request.getParameterValues("options");
		int result = voteService.vote(vsId,voIds,vuId);
		String msg = "ͶƱ";
		if(result>0) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else if(result==0){
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else if(result==-1){
			msg="���Ѿ�������˸����ݵ�ͶƱ���޷��ظ�ͶƱ,ͶƱ";
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//��ʾͶƱ��ͳ�ƽ��
	protected void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vsId = Integer.parseInt(request.getParameter("vsId"));
		List<OptionBean> optionBeans = voteService.view(vsId);
		request.setAttribute("optionBeans", optionBeans);
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}
	//�޸�ͶƱǰ��ʾͶƱȫ����Ϣ
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
		String msg = "�޸�ͶƱ����";
		if(result > 0) {
			response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}else {
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
		}
		
	}
	//����title��ѯ��Ϣ
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vsTitle = request.getParameter("vsTitle");
		List<VoteBean> list = voteService.query(vsTitle);
		request.setAttribute("voteList", list);
		request.setAttribute("vsTitle", vsTitle);
		User user = (User)request.getSession().getAttribute("user");
		//�ж��Ƿ��ǹ���Ա
		if(user != null && user.getFlag()==0) {
			request.getRequestDispatcher("manager.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
