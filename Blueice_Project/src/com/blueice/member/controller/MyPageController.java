package com.blueice.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class myPageController
 */
@WebServlet("/myPage.me")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 마이페이지 띄우기 : 로그인한 상태일 경우만
		
		// 로그인 전 요청 시 : 알람메세지를 담아 메인페이지 요청
		// 로그인 후 요청 시 : 마이페이지를 포워딩
		
		// 현재 로그인한 상태인지 검사하는 방법 : session 의 loginUser 키값이 존재하는지 검사!!
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) { // 로그인 전
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스 입니다.");
			response.sendRedirect(request.getContextPath());
		}
		else { // 로그인 후
			// 포워딩 방식
			request.getRequestDispatcher("views/member/updateUser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
