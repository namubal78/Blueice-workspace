package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.information.model.service.InquiryService;
import com.blueice.information.model.vo.Inquiry;

/**
 * Servlet implementation class InquiryEnrollController
 * 1:1 문의내역 작성하기 서블릿
 */
@WebServlet("/inquiryInsert.in")
public class InquiryEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 1:1 문의하기 등록 서블릿
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // post 방식으로 인코딩 설정
	    request.setCharacterEncoding("UTF-8");
	    
	    // VO로 가공
	    String memNo = request.getParameter("memNo");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    
	    Inquiry i = new Inquiry();
	    i.setMemNo(memNo);
	    i.setInquiryTitle(title);
	    i.setInquiryConts(content);
	    
	    int result = new InquiryService().insertInquiry(i);
	    
	    // alertMsg를 위한 session 정보 가져오기
	    HttpSession session = request.getSession();
	    
	    if(result > 0) {
	        
	        session.setAttribute("alertMsg", "1:1 문의하기 작성에 성공하였습니다.");
	        // 1:1문의하기 작성완료후 리스트로 이동
	        response.sendRedirect(request.getContextPath() + "/myInquiry.in?currentPage=1");
	    }
	    
	    else {
	        
	        session.setAttribute("alertMsg", "1:1 문의하기 작성에 실패하였습니다.");
	        // 1:1문의하기 작성실패후 리스트로 이동
	        response.sendRedirect(request.getContextPath());
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
