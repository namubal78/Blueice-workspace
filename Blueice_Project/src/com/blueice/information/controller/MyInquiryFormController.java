package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class MyInquiryFormController
 * 마이페이지 - 1:1 문의내역 클릭시 1:1 문의내역 창을 띄워주는 서블릿
 */
@WebServlet("/myInquiryForm.in")
public class MyInquiryFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInquiryFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // alertMsg를 위한 세션 생성
	    HttpSession session = request.getSession();
	    
	    // 로그인 유저 정보 가져오기
	    Member m = (Member)session.getAttribute("loginMember");
	    
	    // 비회원 접근시 로그인 페이지로 이동
	    if(m == null) {
	        
	        session.setAttribute("alertMsg", "해당 서비스는 로그인후 이용해 주세요.");
	        
	        response.sendRedirect(request.getContextPath() + "/LoginForm.me");
	        
	    }
	    
        // 관리자 계정으로 비정상적인 경로 접근시 1:1 문의내역 리스트 관리페이지로 이동
        else if((m.getMemId().equals("blueice"))) {
            
            session.setAttribute("alertMsg", "1:1문의내역 리스트 관리페이지로 이동합니다.");

            // 1:1 문의내역 리스트 관리페이지로 이동
            response.sendRedirect(request.getContextPath());
        }

	    
	    // 회원 접근시 1:1 문의내역 리스트 조회로 이동
	    else {
	        
	        request.getRequestDispatcher("views/information/myInquiry.jsp").forward(request, response);
	        
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
