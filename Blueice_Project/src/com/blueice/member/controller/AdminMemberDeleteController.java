package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberDeleteController
 * 관리자 회원 삭제용 서블릿
 */
@WebServlet("/adminMemberDelete.me")
public class AdminMemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// 인코딩 설정
			request.setCharacterEncoding("UTF-8");
			int memNo = Integer.parseInt(request.getParameter("mno"));
			
			System.out.println(memNo);
			
			int result = new MemberService().deleteAdminMember(memNo);
		
			// 돌려받은 처리 결과로 사용자가 보게 될 응답 페이지를 지정
			if(result > 0) { // 성공
				
				// request.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다."); 왜 안 뜨지?
				
				// admin_menubar.jsp 로 포워딩하는 controller 로 연결
				request.getRequestDispatcher("/adminMyPageLink.me").forward(request, response);
				
			}
			else { // 실패
				
				request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
				request.getRequestDispatcher("/adminMyPageLink.me").forward(request, response);
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
