package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.Donation;
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class OneReceiptListFormController
 */
@WebServlet("/oneReceiptList.do")
public class OneReceiptListFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneReceiptListFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt(request.getParameter("type")); // 1이면 정기, 2면 일시
		
		if(type == 1) { // 정기결제면
			String doRegId = request.getParameter("dId");
			DonationRegular dr = new DonationService().SelectRegDonationSumBill(doRegId);
			
			int memNo = Integer.parseInt(dr.getRegDonator());
			Member m = new MemberService().selectMemberNo(memNo);
			request.setAttribute("dr", dr);
			request.setAttribute("m", m);
		} 
		else { // 일시결제면
			String doId = request.getParameter("dId");
			// System.out.println(doId);
			Donation d = new DonationService().SelectDonation(doId);
			
			int memNo = Integer.parseInt(d.getDonator());
			Member m = new MemberService().selectMemberNo(memNo);
			request.setAttribute("d", d);
			request.setAttribute("m", m);
			// System.out.println(d);
			// System.out.println(m);
		}
		
		request.getRequestDispatcher("views/donation/donationReceipt.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
