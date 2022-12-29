package com.blueice.news.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.blueice.common.MyfileRenamePolicy;
import com.blueice.news.model.service.NewsService;
import com.blueice.news.model.vo.Attachment;
import com.blueice.news.model.vo.News;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NewsUpdateController
 */
@WebServlet("/update.news") // 뉴스 게시글 수정을 위한 서블릿
public class NewsUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// multipart/form-data 형식으로 요청이 들어왔는지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1. 전송된 파일에 대한 정보들 지정(전송 파일 용량 제한, 전달된 파일을 저장할 서버의 실 경로)
			// 1_1. 전송파일 용량 제한
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 전달된 파일을 저장할 서버의 실제 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/images/news/");
			
			// 2. 전달된 파일명 수정 작업 후 서버에 업로드 & MultipartRequest타입으로 변환
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy());
			
			// 3. 요청 시 전달값을 뽑을 수 있음!!
			// 변수 및 객체에 기록
			
			// News 테이블 Update에 필요한 데이터들 먼저 가공
			int newsNo = Integer.parseInt(multiRequest.getParameter("nno"));
			String newsTitle = multiRequest.getParameter("title");
			String newsConts = multiRequest.getParameter("content");
						
			News n = new News();
			n.setNewsNo(newsNo);
			n.setNewsTitle(newsTitle);
			n.setNewsConts(newsConts);
						
			Attachment at = null;
			
			// 요청 시 전달 값 중에 넘어온 첨부파일이 있는지 먼저 검사
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				// 넘어온 첨부파일이 있을 경우
				// -> Attachment테이블에서 Update 또는 Insert 해야함
				
				// 2개의 쿼리문 중 공통적으로 필요한 항목 먼저 세팅
				// originName, changeName
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				
				// Insert구문에서 추가적으로 필요로 하는 FilePath컬럼값도 세팅
				at.setFilePath("resources/images/news/");
				
				// 기존 파일이 있는지 없는지 검사
				// -> 기존 파일이 있다면 : Attachment 테이블에 Update 구문 실행
				// -> 기존 파일이 없다면 : Attachment 테이블에 Insert 구문 실행
				if(multiRequest.getParameter("originFileNo") != null) {
					// 기존 첨부 파일이 있을 경우
					
					// Update 구문에서 추가적으로 필요로 하는 기존 파일의 고유 번호 담기
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				}
				else {
					// 기존 첨부 파일이 없을 경우
					
					// Insert 구문에서 추가적으로 필요로 하는 참조 게시글 번호 담기
					at.setRefBno(newsNo);
				}
				
				// 이 시점을 기준으로 각 케이스 별로 필요한 데이터들이 at에 담겨있음!
				
				// case1. 기존 첨부파일X, 새로운 첨부파일X -> News테이블 Update만 실행
				// case2. 기존 첨부파일O, 새로운 첨부파일O -> News Update, Attachment Update 실행
				// case3. 기존 첨부파일X, 새로운 첨부파일O -> News Update, Attachment Insert 실행
				
				int result = new NewsService().updateNews(n, at);
				
				if(result > 0) { // 수정 성공 시
					
					if(multiRequest.getParameter("originFileName") != null
							&& savePath + multiRequest.getParameter("reUpfile") != null) {
						
						// 기존에 존재하던 첨부파일 삭제 구문
						new File(savePath + multiRequest.getParameter("originFileName")).delete();
					}

					HttpSession session = request.getSession();
					session.setAttribute("alertMsg", "성공적으로 수정되었습니다.");
					response.sendRedirect(request.getContextPath() + "/page.news?nno=" + newsNo);
				}
				else {
					
					
				}
			}
			else { // 새로 첨부할 파일이 없다면(내용만 수정이 필요하다면)
				
				int result = new NewsService().updateNews(n, at);
				
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/page.news?nno=" + newsNo);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
