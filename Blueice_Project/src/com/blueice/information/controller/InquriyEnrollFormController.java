package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class InquriyEnrollFormController
 * 1:1 문의창을 띄워주는 서블릿
 */
@WebServlet("/inquiryInsertForm.in")
public class InquriyEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquriyEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	           
	       // alert 메세지 생성을 위한 session 생성
	       HttpSession session = request.getSession();
	       
           // 로그인 유저 정보 가져오기
           Member m = (Member)session.getAttribute("loginMember");
	       
	       // 비회원 접근시 로그인페이지로 이동
	        if(m == null) {
	            
	            session.setAttribute("alertMsg", "해당 서비스는 로그인후 이용해 주세요.");
	            
	            // 로그인페이지로 이동
	            response.sendRedirect(request.getContextPath() + "/LoginForm.me");
	            
	        }
	        
	        // 관리자 계정 접근시 1:1 문의내역으로 갈수있게
	        else if((m.getMemId().equals("blueice123"))) {
	            
	            session.setAttribute("alertMsg", "관리자는 접근권한이 없습니다.");

	            // 1:1 문의내역 리스트로 이동
	            response.sendRedirect(request.getContextPath() + "/myMenu.me");
	        }
	        
	        // 일반계정 접근시 1:1문의하기 페이지로 이동
	        else {
	            
	            request.getRequestDispatcher("views/information/inquiryEnrollForm.jsp").forward(request, response);
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
