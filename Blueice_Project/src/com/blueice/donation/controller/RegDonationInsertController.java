package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class RegDonationInsertController
 */
@WebServlet("/regUpdate.do")
public class RegDonationInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegDonationInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 빌링키를 조회해서 정기결제 요청을 하고 테이블의 가정보를 실결제로 UPDATE하는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request.setCharacterEncoding("UTF-8");
		
		Member m = (Member)request.getSession().getAttribute("loginMember");
		
		String method = request.getParameter("payMethod");
		String receiptRegId = request.getParameter("rId");
		String doRegId = request.getParameter("oId");
		// String phone = request.getParameter("phone");
		int doRegBill = Integer.parseInt(request.getParameter("bill"));
		
		// 빌링키 조회하기
		String billingkey = BootpayController.lookupBillingKey(receiptRegId);
		// System.out.println("빌링키: " + billingkey);
		
		DonationRegular beforeDr = new DonationRegular();
		beforeDr.setReceiptRegId(receiptRegId);
		beforeDr.setDoRegId(doRegId);
		beforeDr.setDoRegBill(doRegBill);
		beforeDr.setBillingkey(billingkey);
		
		System.out.println(beforeDr);
		
		// 1차 결제 구분을 위한 변수
		String orderId = doRegId;

		if(billingkey != null) {
			// DonationRegular dr = BootpayController.requestSubscribe(billingKey, doRegId, doRegBill, m);
			
			DonationRegular dr = BootpayController.reserveSubscribe(beforeDr, orderId, m);
			// DonationRegular dr = BootpayController.reserveSubscribe(beforeDr, orderId, m, request, response, method); // m도
			
			
			int result = new DonationService().UpdateRegDonation(dr);
			
			if(result > 0) {
				// System.out.println("정기결제 실결제 정보 등록 성공");
				
				DonationRegular dr2 = new DonationService().SelectRegDonation(doRegId);
				
				// System.out.println("실결제 등록 성공 후" + dr2);
				
				request.setAttribute("way", "정기");
				request.setAttribute("dr", dr2);
				request.setAttribute("method", method);
				request.getRequestDispatcher("views/donation/donationThanksForm.jsp").forward(request, response);
				
			}
			else {
				// 실패 시 에러페이지로 포워딩
				request.setAttribute("errorMsg", "결제에 실패하였습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
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
