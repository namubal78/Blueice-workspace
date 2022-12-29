package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.information.model.service.InquiryService;

/**
 * 관리자 1:1문의내역 답변 수정 서블릿
 * Servlet implementation class AdminInquiryAnswerUpdateController
 */
@WebServlet("/adminUpdate.in")
public class AdminInquiryAnswerUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquiryAnswerUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		//POST방식으로 인코딩 설정
	    request.setCharacterEncoding("UTF-8");
	    
	    // 넘겨야할값 답변내용, 글번호 2개
	    String answerUpdateConts = request.getParameter("answerUpdateConts");
	    int iNo = Integer.parseInt(request.getParameter("updateIno"));
	    
	    int result = new InquiryService().adminInsertInquiry(answerUpdateConts,iNo);
	    
	    // 수정완료시 alertMsg
	    // alertMsg를 위한 session 정보 가져오기
	    HttpSession session = request.getSession();
	    
	    if(result > 0) {
	        
	        // 1:1문의하기  답변 수정완료후 해당 게시글로 이동
	        response.sendRedirect(request.getContextPath() + "/adminDetail.in?ino=" + iNo);
	        
	    }
	    
	    else {
	        
            session.setAttribute("alertMsg", "1:1 문의내역 답변 수정에 실패하였습니다.");
            
            // 1:1문의하기 답변 수정실패후 해당 게시글로 이동
            response.sendRedirect(request.getContextPath() + "/adminDetail.in?ino=" + iNo);
	        
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
