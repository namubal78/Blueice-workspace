package com.blueice.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.BillGraph;

/**
 * Servlet implementation class MemberGraph
 */
@WebServlet("/member.gr")
public class MemberCountGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCountGraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList list = new MemberService().countMemberList();
		ArrayList<BillGraph> list2 = new MemberService().countDonationBill();
		double result = new MemberService().countInquiry(); 
		int result2 = new MemberService().totalDonation();
		int result3 = new MemberService().totalChallengeCmt();

		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("result", result);
		request.setAttribute("result2", result2);
		request.setAttribute("result3", result3);
		
		request.getRequestDispatcher("views/member/adminStatsList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
