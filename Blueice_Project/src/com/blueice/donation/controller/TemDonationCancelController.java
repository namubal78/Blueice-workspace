package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;

/**
 * Servlet implementation class TemDonationCancelController
 */
@WebServlet("/temCancel.do")
public class TemDonationCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemDonationCancelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// token 발급
		String token = BootpayController.goGetToken();
		
		// receiptId 받아오기
		String receiptId = request.getParameter("rId");
		
		// 정기 결제 취소
		int result1 = BootpayController.receiptCancel(receiptId);
		
		// DB에서 STATUS 값 C로 바꾸기
		int result2 = new DonationService().CancelDonation(receiptId);
		
		if(result1 * result2 > 0) { // 성공 시
			
			// 일시 결제 리스트를 가져오는 ArrayList 쿼리문.. 세팅해서 보내기
			request.getSession().setAttribute("alertMsg", "성공적으로 결제가 취소되었습니다.");
			response.sendRedirect("temAdminlist.do?currentPage=1");
		}
		else {
			// 실패 시 에러페이지로 포워딩
			request.setAttribute("errorMsg", "자체 결제 취소에 실패하였습니다. 부트페이 홈페이지에서 취소 바랍니다.");
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
