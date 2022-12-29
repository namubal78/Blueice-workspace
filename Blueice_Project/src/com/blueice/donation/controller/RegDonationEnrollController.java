package com.blueice.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.vo.Member;


/**
 * Servlet implementation class RegDonationEnrollController
 */
@WebServlet("/regEnroll.do")
public class RegDonationEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegDonationEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 정기 결제 테이블에 가정보를 INSERT하고, 성공 시 가정보를 SELECT 해 결제페이지에 같이 띄워주는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		// 프론트로부터 받아와야 할 값들 추출
		// 뽑아야할 값들 : 결제금액, 후원자번호(회원번호)
		int doRegBill = Integer.parseInt(request.getParameter("doBill"));
		int regDonator = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		
		DonationRegular dr = new DonationRegular();
		dr.setDoRegBill(doRegBill);
		dr.setRegDonator(String.valueOf(regDonator));
		
		// 결제를 위한 가정보를 INSERT
		int result = new DonationService().insertRegular(dr);
		
		if(result > 0) {
			// INSERT 성공 시 입력된 가정보를 SELECT
			DonationRegular dr2 = new DonationService().SelectRegBeforeConfirm(doRegBill, regDonator);
			
			// 토큰 가져오기
			String token = BootpayController.goGetToken();
			// System.out.println(token);
			
			// 토큰과 가정보를 정기 결제 페이지로 포워딩
			request.setAttribute("dr", dr2);
			request.setAttribute("token", token);
			request.getRequestDispatcher("views/donation/donationRegEnrollForm.jsp").forward(request, response);
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
