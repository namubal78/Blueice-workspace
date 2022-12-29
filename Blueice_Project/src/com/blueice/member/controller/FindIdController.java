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
 * Servlet implementation class FindIdController
 * 아이디찾기 서블릿
 */
@WebServlet("/findId.me")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memName = request.getParameter("memName");
		String email = request.getParameter("email");
		
		Member m = new Member();
		m.setMemName(memName);
		m.setEmail(email);
		
		Member findIdMember = new MemberService().findIdMember(m);
				
		if(findIdMember != null) { // 성공 => 아이디찾기성공 페이지로 포워딩
			
			 request.setAttribute("findIdMember", findIdMember);
			 request.getRequestDispatcher("views/member/memberFindIdSuccess.jsp").forward(request, response);
			 
		}
		else { // 실패 => 실패메세지를 담아서 에러페이지로 포워딩
			
			request.getRequestDispatcher("views/member/memberFindIdFail.jsp").forward(request, response);
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
