package com.blueice.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;

/**
 * Servlet implementation class TemDonationErrorController
 */
@WebServlet("/deleteError.do")
public class TemDonationErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemDonationErrorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 일시결제 도중 오류 혹은 취소 시 가정보를 삭제(DELETE)해주는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String doId = request.getParameter("oId");
		
		int result = new DonationService().DeleteError(doId);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "후원이 중단되었습니다.");	        
	        response.sendRedirect(request.getContextPath()+"/enrollInfo.do");
	        
		} else {
			// 실패 시 에러페이지로 포워딩
			request.setAttribute("errorMsg", "후원 시도 중 오류가 발생하였습니다. 관리자에게 문의해주세요.");
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
