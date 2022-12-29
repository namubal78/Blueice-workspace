package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.Donation;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class TemDonationEnrollController
 */
@WebServlet("/temEnroll.do")
public class TemDonationEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemDonationEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request.setCharacterEncoding("UTF-8"); => 필터 써서 없어도 됨
		
		// 프론트로부터 받아와야 할 값들 추출
		// 뽑아야할 값들 : 금액, 후원자번호(회원번호)
		int doBill = Integer.parseInt(request.getParameter("doBill"));
		int donator = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		
		Donation d = new Donation();
		d.setDoBill(doBill);
		d.setDonator(String.valueOf(donator));
		
		// 결제를 위한 가정보를 INSERT
		int result = new DonationService().insertDonation(d);
		
		if(result > 0) {
			// INSERT 성공 시 입력된 가정보를 SELECT => 후원번호(PK), 금액
			Donation d2 = new DonationService().SelectBeforeConfirm(d);
			
			// 성공 시 일시 결제 페이지로 포워딩
			request.setAttribute("d", d2);
			request.getRequestDispatcher("views/donation/donationTemEnrollForm.jsp").forward(request, response);
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
