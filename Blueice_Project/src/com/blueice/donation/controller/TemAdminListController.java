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
@WebServlet("/temAdminlist.do")
public class TemAdminListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemAdminListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = 2; // 1이면 정기, 2면 일시결제
		DonationService ds = new DonationService();
		
		// 검색어를 담을 변수
		String searchText = null;
		if (request.getParameter("searchText") != null) {			
			searchText = request.getParameter("searchText").trim();
		}
		
		// 회원 유형을 담을 변수
		String memType = null; // 멤버 타입을 받을 변수
		
		// 일단 페이지를 넘어올 때 selection의 기본값은 null
		// 검색 버튼(submit)을 눌러야 value값이 담겨서 넘어옴
		String selection = request.getParameter("selection");
		// System.out.println(selection);
		
		// 페이징처리
		// 검색처리마다 게시글 개수가 달라져야 함
		int listCount = 0;  // 총 게시글 개수
		
		if (searchText == null && selection == null) {
			// 기본 전체 리스트 조회
			listCount = ds.selectListCount(type);
		} else {
			// 검색어 포함 전체 리스트 조회
			switch (selection) {
			case "name" : listCount = ds.selectListSearchNameCount(type, searchText); break;
			case "memId" : listCount = ds.selectListSearchIdCount(type, searchText); break;
			case "type" : 	
				memType = request.getParameter("typeSelection");
				if(memType.equals("personal")) { memType = "1";
				} else if(memType.equals("group")) { memType = "2";
				} else { memType = "3"; }
				listCount = ds.selectListSearchTypeCount(type, memType); break;
			}
		}
		
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
		ArrayList<Donation> list = null;
		ArrayList<Member> list2 = new ArrayList<>();
		
		if (searchText == null && selection == null) {
			// Donation 리스트 service 단에서 불러오기
			list = ds.selectDonaList(pi);
		} else {
			switch (selection) {
			case "name" : list = ds.selectTemSearchNameList(pi, searchText); break;
			case "memId" : list = ds.selectTemSearchIdList(pi, searchText); break;
			case "type" : list = ds.selectTemSearchTypeList(pi, memType); break;
			}
		}
		
		for(Donation d : list) {
			int memNo = Integer.parseInt(d.getDonator());
			Member m = new MemberService().selectMemberNo(memNo);
			
			list2.add(m);
		}
		
		// 값 넘기기
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("searchText", searchText);
		request.setAttribute("selection", selection);
		request.getRequestDispatcher("views/donation/adminDonation.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
