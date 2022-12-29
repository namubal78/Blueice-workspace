package com.blueice.information.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.information.model.service.FaqService;

/**
 * Servlet implementation class FaqDeleteController
 */
@WebServlet("/faqDelete.in")
public class FaqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// faq테이블 FAQ_NO 컬럼값
		int faqNo = Integer.parseInt(request.getParameter("fno"));
		
		int result = new FaqService().deleteFaq(faqNo);
		
		System.out.println(result);
	
		if(result > 0) { // 성공
			
	        request.setAttribute("alertMsg", "FAQ 삭제에 성공하였습니다.");
			// faq 전체조회로 포워딩
			request.getRequestDispatcher("/faqSelect.in").forward(request, response);
			
		}
		else { // 실패
			
			request.setAttribute("errorMsg", "FAQ 삭제에 실패했습니다.");
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
