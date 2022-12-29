package com.blueice.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DonationInfoController
 */
@WebServlet("/enrollInfo.do")
public class DonationInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 후원 정보 입력 페이지를 member 정보를 조회(SELECT)해 띄워주는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 후원 정보 입력 페이지를 띄워주는 용도
		// 회원 정보는 세션의 로그인 유저로부터 곧바로 추출
		request.getRequestDispatcher("views/donation/donationFirstForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
