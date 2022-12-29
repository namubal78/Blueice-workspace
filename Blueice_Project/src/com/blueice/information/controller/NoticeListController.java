package com.blueice.information.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.service.NoticeService;
import com.blueice.information.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	// 공지사항 게시판 리스트 조회 서블릿 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeService ns = new NoticeService();
		
		// 검색어를 담을 변수
		String searchText = null;
		if (request.getParameter("searchText") != null) {	// 검색어가 있을경우 
			searchText = request.getParameter("searchText");
		}

		// 페이징 처리
		int listCount = 0;  // 총 게시글 개수
		// 검색 처리마다 게시글 개수가 달라져야 함		
		if (searchText == null) { // 검색 안했을시 
			listCount = ns.selectListCount(); // 기본 게시글 개수 조회
		} else { // 검색시 
			listCount = ns.selectListSearchCount(searchText); // 검색 게시글 개수 조회  
		}
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재 요청한 페이지(즉, 사용자가 요청한 페이지수)
		int pageLimit = 5; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 개수
		int maxPage = (int)Math.ceil((double)listCount/boardLimit); // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지 수)
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage = startPage + pageLimit - 1; // 페이지 하단에 보여질 페이징바의 끝수
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 1. PageInfo 객체로 가공 (조회할때, 페이징바 만들때 필요)
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
							   	   , maxPage, startPage, endPage);
		
		
		
		// pi 를 넘기면서 서비스로 요청 
		// 2. list에는 해당 페이지에서 보여져야할 게시글들의 목록들 (목록 만들때 필요)
		ArrayList<Notice> list = ns.selectList(pi);
		
		if (searchText == null) { // 검색 안했을시 
			// DonationRegular 리스트 service 단에서 불러오기
			list = ns.selectList(pi);
		} else {
			list = ns.selectSearchList(pi, searchText); 
		}
		/*
			System.out.println("Controller if/else문 다음" + list);
			검색 안했을시 조회 ok 
		 */
		
		
		// 값 넘기기 
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("searchText", searchText); // 검색어
		
		request.getRequestDispatcher("views/information/noticeListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}











