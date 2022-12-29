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
 * Servlet implementation class MemberUpdatePwdController
 * 회원 비밀번호 변경 서블릿
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 비밀번호 변경
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// 기존 비밀번호와 바꿀 비밀번호
		String memPwd = request.getParameter("memPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		// 로그인한 회원의 아이디값
		String memId = request.getParameter("memId");
		
		Member m = new Member();
		m.setMemId(memId);
		m.setMemPwd(memPwd);
		
		Member updateMem = new MemberService().updatePwdMember(m, updatePwd);
		
		HttpSession session = request.getSession();
		
		
		// 결과에 따른 응답페이지 지정
		if(updateMem == null) { // 실패
			session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
		}
		else { // 성공
			session.setAttribute("loginMember", updateMem);
			session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/myPage.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
