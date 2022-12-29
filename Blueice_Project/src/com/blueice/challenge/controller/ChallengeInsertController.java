package com.blueice.challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.blueice.challenge.model.service.ChallengeService;
import com.blueice.challenge.model.vo.Challenge;
import com.blueice.challenge.model.vo.ChallengeAttachment;
import com.blueice.common.MyfileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ChallengeInsertController
 */
@WebServlet("/insert.challenge") // 챌린지 게시글 작성을 위한 서블릿
public class ChallengeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 요청이 multipart/form-data형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1. 전달된 파일에 대한 정보 먼저 저장(전송파일 용량제한, 저장할 파일의 물리적인 경로)
			// 1_1. 용량제한
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 저장할 파일의 물리적인 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/images/event/");
			
			// 2. 전달된 파일명 수정 작업 후 서버에 업로드 + MultipartRequest 타입으로 변환
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyfileRenamePolicy());
			
			// 3. DB에 전달할 값 뽑기
			
			// Challenge에 Insert -> ChalTitle, ChalConts
			Challenge c = new Challenge();
			c.setChalTitle(multiRequest.getParameter("title"));
			c.setChalConts(multiRequest.getParameter("content"));
			c.setChallengeOriginName(multiRequest.getOriginalFileName("upfile"));
			c.setChallengeChangeName(multiRequest.getFilesystemName("upfile"));
			c.setChallengeFilePath("resources/images/event/");
			
			int result = new ChallengeService().insertChallenge(c);
			
			if(result > 0) { // 성공일 경우 -> list.challenge로 url 재요청
				
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "성공적으로 삽입되었습니다.");
				response.sendRedirect(request.getContextPath() + "/list.challenge");
			}
			else { // 실패일 경우 -> 에러 페이지 재요청
				
				
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
