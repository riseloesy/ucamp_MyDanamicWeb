package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.UserVo;

/**
 * Servlet implementation class UserListServlet
 */
//@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
    }

    /*
     * ���ΰ�ħ? web.xml ���� / <param-name>�̸� �̿��ؼ� <param-value>������  //init-param�� servlet������/
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println(">> init");
    	String driver = config.getInitParameter("driverClass");
    	String url = config.getInitParameter("dbUrl");
    	String username = config.getInitParameter("dbUsername");
    	String password = config.getInitParameter("dbPassword");
    	
    	userDao = new UserDao(driver, url, username, password);
    }
	/*����ڰ� ��û�Ҷ�
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>doGet");
		//response(����) �����͸� utf-8�� ���ڵ�
		response.setContentType("text/html;charset=UTF-8");
		//UserDao�� ȣ���ؼ� DB�����͸� ��������
		List<UserVo> userList = userDao.getUserList();
		//Request��ü�� userList�� �����ϱ�
		request.setAttribute("users", userList);
		//RequestDispatcher ����
		RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
		//userList.jsp �������� �������ϱ�
		dispatcher.forward(request, response);
	}
	/*
	 * �޸� ���ŵ� ��
	 */
	@Override
	public void destroy() {
		System.out.println(">> destroy");
		userDao.connectionCLose();
	}

}
