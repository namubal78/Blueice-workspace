package com.blueice.news.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.news.model.service.NewsService;
import com.blueice.news.model.vo.News;
import com.blueice.common.model.vo.PageInfo;

/**
 * Servlet implementation class NewsSearchController
 */
@WebServlet("/search.news") // 뉴스 제목으로 검색 후 보여질 화면을 위한 서블릿
public class NewsSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsSearchController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 사용자가 검색할 키워드 가져오기
		String keyword = request.getParameter("keyword");
		
		// 페이징 처리
		int listCount; // 현재 총 게시글 개수
		int currentPage; // 현재 요청한 페이지(즉, 사용자가 요청한 페이지수)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수
		int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		listCount = new NewsService().selectSearchCount(keyword);
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		pageLimit = 5;
		
		boardLimit = 6;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
									, maxPage, startPage, endPage);
		
		// 검색할 키워드를 입력하지 않늗다면
		if(keyword == null || keyword.isEmpty()) {
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "검색어를 입력해주세요.");
			request.getRequestDispatcher("/list.news?currentPage=1").forward(request, response);
		}
		// 검색할 키워드가 있다면
		else {
			
			ArrayList<News> searchList = new NewsService().searchNews(keyword, pi);
			
			request.setAttribute("searchList", searchList);
			request.setAttribute("pi", pi);
			request.setAttribute("keyword", keyword);
			request.getRequestDispatcher("views/news/newsNoticeSearch.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
