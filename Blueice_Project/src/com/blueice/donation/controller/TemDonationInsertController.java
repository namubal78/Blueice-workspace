package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.Donation;

/**
 * Servlet implementation class TemDonationInsertController
 */
@WebServlet("/temUpdate.do")
public class TemDonationInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemDonationInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request.setCharacterEncoding("UTF-8");
		
		// 클라이언트단에서 받아올 값
		// 결제ID, 후원ID(PK), 결제영수증 URL, 결제종류
		String receiptId = request.getParameter("rId");
		String doId = request.getParameter("oId");
		String receiptURL = request.getParameter("rUrl");
		String method = request.getParameter("payMethod");
		
		// UPDATE할 결제ID, 결제영수증 URL 담기
		Donation d = new Donation();
		d.setDoId(doId);
		d.setReceiptId(receiptId);
		d.setReceiptURL(receiptURL);
		
		// 실결제 정보 UPDATE
		int result = new DonationService().UpdateDonation(d);
		
		if(result > 0) {
			
			// UPDATE 성공 시 결제 한 건에 대한 SELECT
			Donation d2 = new DonationService().SelectDonation(doId);
			
			request.setAttribute("way", "일시");
			request.setAttribute("d", d2);
			request.setAttribute("method", method);
			request.getRequestDispatcher("views/donation/donationThanksForm.jsp").forward(request, response);

		}
		else {
			// 실패 시 에러페이지로 포워딩
			request.setAttribute("errorMsg", "결제에 실패하였습니다.");
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
