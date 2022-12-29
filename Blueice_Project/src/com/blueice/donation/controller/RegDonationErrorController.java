package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;

/**
 * Servlet implementation class RegDonationErrorController
 */
@WebServlet("/deleteErrorReg.do")
public class RegDonationErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegDonationErrorController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void toDelete() {
    	
    }

	/**
	 * 정기결제 도중 오류 혹은 취소 시 가정보를 삭제(DELETE)해주는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String doRegId = request.getParameter("oId");
		
		int result = new DonationService().DeleteErrorReg(doRegId);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "후원이 중단되었습니다. 후원을 원하시면 올바른 카드 정보로 다시 후원을 시도해주세요.");	        
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
