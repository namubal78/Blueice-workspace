package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.information.model.service.InquiryService;
import com.blueice.member.model.vo.Member;

/**
 * 마이페이지 1:1문의내역 상세조회시 삭제하기 + 관리자 1:1문의내역 상세조회시 삭제하기
 * Servlet implementation class MyInquiryDeleteInquiry
 */
@WebServlet("/deleteInquiry.in")
public class MyInquiryDeleteInquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInquiryDeleteInquiry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // 삭제시 필요한 글번호 추출
	    int iNo = Integer.parseInt(request.getParameter("ino")); 
	    
	    int result = new InquiryService().deleteInquiry(iNo);
	    
	    // alertMsg 및 로그인 유저 정보 가져오기
	    HttpSession session = request.getSession();
	    // 로그인 유저 정보 가져오기
        Member m = (Member)session.getAttribute("loginMember");
	    
	    if(result > 0) { // 삭제 성공
	        
	        session.setAttribute("alertMsg", "문의내역 삭제에 성공하였습니다.");
	        
	        // 관리자 계정시 관리자 리스트 페이지로
	        if(m.getMemId().equals("blueice123")) {
	            
	            response.sendRedirect(request.getContextPath() + "/adminList.in?currentPage=1");
	        }
	        
	        // 사용자 계정시 사용자 리스트 페이지로
	        else {
	        
	            response.sendRedirect(request.getContextPath() + "/myInquiry.in?currentPage=1");
	        }
	        	        
	    }
	    
	    else { // 삭제 실패
	        
	        request.setAttribute("errorMsg", "문의내역 삭제에 실패하였습니다.");
	        
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
