package com.blueice.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 * 회원 탈퇴 서블릿
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 회원 탈퇴 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String memPwd = request.getParameter("memPwd");
		
		HttpSession session = request.getSession();
		String memId = ((Member)session.getAttribute("loginMember")).getMemId();
		
		Member m = new Member();
		m.setMemId(memId);
		m.setMemPwd(memPwd);
		
		int result = new MemberService().deleteMember(m);
		
		if(result > 0) { // 성공 => 성공 문구를 담아서 메인페이지로 url 요청(로그아웃 되도록)
			session.setAttribute("alertMsg", "성공적으로 회원 탈퇴가 완료되었습니다. 그동안 블루아이스를 이용해 주셔서 감사합니다.");
			
			session.removeAttribute("loginMember");
			
			response.sendRedirect(request.getContextPath());
		}
		else {
			
			request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
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
