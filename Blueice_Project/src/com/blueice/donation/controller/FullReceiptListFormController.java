package com.blueice.donation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.dao.DonationDao;
import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.Donation;
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class FullReceiptListFormController
 */
@WebServlet("/fullReceiptList.do")
public class FullReceiptListFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FullReceiptListFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String donatorNo = String.valueOf(((Member)request.getSession().getAttribute("loginMember")).getMemNo());
		String regDonatorNo = donatorNo;
		
		int type = Integer.parseInt(request.getParameter("type")); // 1이면 정기, 2면 일시
		
		if(type == 1) { // 정기 결제면
			
			ArrayList<DonationRegular> listRegFull = new DonationService().selectRegDonationFull(regDonatorNo);
			Member m = new MemberService().selectMemberNo(Integer.parseInt(regDonatorNo));
			
			request.setAttribute("m", m);
			request.setAttribute("listRegFull", listRegFull);
		} 
		
		else { // 일시 결제면
			
			Donation dFull = new DonationService().selectDonationFull(donatorNo);
			Member m = new MemberService().selectMemberNo(Integer.parseInt(donatorNo));
			System.out.println(m);
			request.setAttribute("m", m);
			request.setAttribute("dFull", dFull);
		}
		request.getRequestDispatcher("views/donation/fullDonationReceipt.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
