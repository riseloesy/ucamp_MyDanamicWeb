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
     * 새로고침? web.xml 참고 / <param-name>이름 이용해서 <param-value>가져옴  //init-param은 servlet에서만/
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
	/*사용자가 요청할때
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>doGet");
		//response(응답) 데이터를 utf-8로 인코딩
		response.setContentType("text/html;charset=UTF-8");
		//UserDao를 호출해서 DB데이터를 가져오기
		List<UserVo> userList = userDao.getUserList();
		//Request객체에 userList를 저장하기
		request.setAttribute("users", userList);
		//RequestDispatcher 생성
		RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
		//userList.jsp 페이지로 포워딩하기
		dispatcher.forward(request, response);
	}
	/*
	 * 메모리 제거될 때
	 */
	@Override
	public void destroy() {
		System.out.println(">> destroy");
		userDao.connectionCLose();
	}

}
