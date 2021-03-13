package cn.tp.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tp.pojo.User;
import cn.tp.service.UserService;
import cn.tp.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends DispatchServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
       
    /**
     * @see DispatchServlet#DispatchServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //ע��
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if(username != null && password != null && confirmPassword != null) {
			boolean  result = userService.register(new User(username,password));
			String msg = "�û�ע��";
			if(result) {
				response.sendRedirect("success.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
			}else {
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode(msg, "UTF-8"));
			}
		}
	}
	//��½��֤
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username != null) {
			User  user = userService.login(new User(username,password));
			if(user == null) {
				//�û���������
				request.getSession().setAttribute("msg", "�û���������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(user.getPassword() != null) {
				//�û��������붼��ȷ
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("VoteServlet?option=index").forward(request, response);
		
			}else {
				//�������
				request.getSession().setAttribute("msg", "�������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
		}
	}
	//�û�����֤
	protected void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		if(username != null) {
			User  user = userService.findByName(username);
			if(user == null) {
				response.getWriter().print(true);;
			}else {
				response.getWriter().print(false);
			}
		}
	}
	
	//�˳�
	public void exit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		   Enumeration<String> em = request.getSession().getAttributeNames();  //�õ�session�����е�������
		   while (em.hasMoreElements()) {
		       request.getSession().removeAttribute(em.nextElement().toString()); //����ɾ��session�е�ֵ
		   }
		   response.sendRedirect("VoteServlet?option=index");
		}
}
