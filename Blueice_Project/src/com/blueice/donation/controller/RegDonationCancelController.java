package com.blueice.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.DonationRegular;

/**
 * Servlet implementation class RegDonationCancelController
 */
@WebServlet("/regCancel.do")
public class RegDonationCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegDonationCancelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// token 발급
		String token = BootpayController.goGetToken();
		
		// reserveId 받아오기
		String reserveId = request.getParameter("rId");
		// System.out.println(reserveId);
		
		// 정기 결제 취소
		int cancel = BootpayController.reserveCancelSubscribe(reserveId);
		
		// DB에서 REG_STATUS C로 바꾸기
		int result = new DonationService().CancelRegDonation(cancel, reserveId);
		
		if(cancel * result > 0) { // 둘 다 성공 시
			
			String billingkey = request.getParameter("bk");
			BootpayController.destroyBillingKey(billingkey);
			
			request.getSession().setAttribute("alertMsg", "성공적으로 결제가 취소되었습니다.");
			response.sendRedirect("regAdminlist.do?currentPage=1");
			// request.getRequestDispatcher("views/donation/adminDonationRegular.jsp").forward(request, response);
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
