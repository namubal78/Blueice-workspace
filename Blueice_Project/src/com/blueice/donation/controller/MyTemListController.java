package com.blueice.donation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.Donation;
import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class TemAdminListController
 */
@WebServlet("/temList.do")
public class MyTemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTemListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 회원의 일시후원 리스트를 조회하여 띄워주는 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = 2; // 1이면 정기, 2면 일시결제
		DonationService ds = new DonationService();
		
		int memNo = ((Member)(request.getSession().getAttribute("loginMember"))).getMemNo();
		
		// 페이징처리
		int listCount = ds.selectMyListCount(type, memNo);		
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재 요청한 페이지
		int pageLimit = 5; // 페이징바의 페이지 최대 개수
		int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 개수
		int maxPage = (int)Math.ceil((double)listCount / boardLimit); // 마지막 페이지 (총 페이지)
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; // 각 페이징바의 시작 페이지
		int endPage = startPage + pageLimit - 1; // 페이지 하단에 보여질 페이지의 끝 수
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		// PageInfo 객체로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// VO를 담을 리스트
		ArrayList<Donation> list = ds.selectMyDonaList(pi, memNo);
		// ArrayList<Member> list2 = new ArrayList<>();
		
		/*
		for(Donation d : list) {
			int memNo = Integer.parseInt(d.getDonator());
			Member m = new MemberService().selectMemberNo(memNo);
			
			list2.add(m);
		}
		*/
		
		// 값 넘기기
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("listCount", listCount);
		// request.setAttribute("list2", list2);
		// request.setAttribute("searchText", searchText);
		// request.setAttribute("selection", selection);
		request.getRequestDispatcher("views/donation/myDonationList.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
