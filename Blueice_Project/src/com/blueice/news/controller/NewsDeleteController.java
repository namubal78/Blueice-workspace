package com.blueice.news.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.news.model.service.NewsService;

/**
 * Servlet implementation class NewsDeleteController
 */
@WebServlet("/delete.news") // 뉴스 게시글 삭제를 위한 서블릿
public class NewsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 삭제할 뉴스 번호 가져오기
		int newsNo = Integer.parseInt(request.getParameter("nno"));
						
		int result = new NewsService().deleteNews(newsNo);

		if(result > 0) { // 두개 모두 성공적으로 삭제 시
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "삭제 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.news?currentPage=1");
		}
		else { // 두개 중 하나라도 실패 시
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
