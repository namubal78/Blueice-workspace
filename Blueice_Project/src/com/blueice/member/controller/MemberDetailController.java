package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailController
 * 회원정보변경 서블릿
 */
@WebServlet("/detail.me")
public class MemberDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 해당 회원 번호 먼저 뽑기
		int memNo = Integer.parseInt(request.getParameter("mno"));
		
		Member list = new MemberService().detailMember(memNo);
		
		request.setAttribute("memNo", memNo);
		request.setAttribute("list", list);

		request.getRequestDispatcher("views/member/adminUpdateUser.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
