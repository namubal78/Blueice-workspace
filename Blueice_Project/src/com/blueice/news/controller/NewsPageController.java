package com.blueice.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.news.model.service.NewsService;
import com.blueice.news.model.vo.News;

/**
 * Servlet implementation class NewsPageController
 */
@WebServlet("/page.news") // 뉴스 게시글(상세조회)를 보여주기 위한 서블릿
public class NewsPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsPageController() {
        super();
    }

	/**
	 * 뉴스 상세조회 페이지 조회, 조회수 증가 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 뉴스 게시글 번호를 가져와 상세보기 페이지 조회
		int newsNo = Integer.parseInt(request.getParameter("nno"));
		
		int result = new NewsService().countNewsPage(newsNo);
		
		if(result > 0) {
			
			News n = new NewsService().selectNewsPage(newsNo);
									
			request.setAttribute("n", n);
			request.getRequestDispatcher("views/news/newsPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}




























