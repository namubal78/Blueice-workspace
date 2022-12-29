package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class FindPasswordController
 * 패스워드 찾기 서블릿
 */
@WebServlet("/findPassword.me")
public class FindPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		String email = request.getParameter("email");
		
		Member m = new Member();
		m.setMemId(memId);
		m.setMemName(memName);
		m.setEmail(email);
		
		Member findPasswordMember = new MemberService().findPasswordMember(m);
				
		if(findPasswordMember != null) { // 성공 => 비밀번호찾기성공 페이지로 포워딩
			
			 request.setAttribute("findPasswordMember", findPasswordMember);
			 request.getRequestDispatcher("views/member/memberFindPasswordSuccess.jsp").forward(request, response);
			 
		}
		else { // 실패 => 실패메세지를 담아서 에러페이지로 포워딩
			
			request.getRequestDispatcher("views/member/memberFindPasswordFail.jsp").forward(request, response);
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
