package com.blueice.news.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.news.model.service.NewsService;
import com.blueice.news.model.vo.Attachment;
import com.blueice.news.model.vo.News;

/**
 * Servlet implementation class NewsUpdateFormController
 */
@WebServlet("/updateForm.news") // 뉴스 게시글 수정할 페이지를 보여줄 서블릿
public class NewsUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsUpdateFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 수정할 뉴스 번호를 가져오기
		int newsNo = Integer.parseInt(request.getParameter("nno"));
		
		News n = new NewsService().updateFormNews(newsNo);
		
		Attachment at = new NewsService().updateFormAttachment(newsNo);
					
		request.setAttribute("n", n);
		request.setAttribute("at", at);
		request.getRequestDispatcher("views/news/newsNoticeUpdateForm.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
