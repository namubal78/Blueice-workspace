package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class MyPageMenuController
 * 마이페이지 포워딩용 서블릿
 */
@WebServlet("/myMenu.me")
public class MyPageMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageMenuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 게시판 상단 메뉴바의 마이페이지 버튼
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // alert 메세지 생성을 위한 session 생성
	    HttpSession session = request.getSession();
	    
	    // 로그인 유저 정보 가져오기
	    Member m = (Member)session.getAttribute("loginMember");
	    
	    // 비회원 접근시 로그인페이지로 이동
	    if(m == null) {
	        
	        session.setAttribute("alertMsg", "해당 서비스는 로그인후 이용해 주세요");
	        
	        // 로그인페이지로 이동
	        response.sendRedirect(request.getContextPath() + "/LoginForm.me");
	        
	    }
	    
        else if((m.getMemId().equals("blueice123"))) {
            
            // 관리자페이지로 이동
            response.sendRedirect(request.getContextPath() + "/adminMyPageLink.me");
        }
	    
        else {
            
            // 일반회원 접근시 마이페이지로 이동
            request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
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
