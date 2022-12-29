package com.blueice.information.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.information.model.service.InquiryService;
import com.blueice.information.model.vo.Inquiry;

/**
 * 관리자 1:1 문의내역 상세조회 서블릿
 * Servlet implementation class AdminInquiryDetailController
 */
@WebServlet("/adminDetail.in")
public class AdminInquiryDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquiryDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // 보내야할 값 = 글번호
	    // 가져와야할 값 = 전부
	    // 게시글 번호 추출
	    int iNo = Integer.parseInt(request.getParameter("ino"));
	        
	    Inquiry i = new InquiryService().selectInquiry(iNo);
	        
	    request.setAttribute("i", i);
	    
	    request.getRequestDispatcher("views/information/adminInquiryDetailView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
