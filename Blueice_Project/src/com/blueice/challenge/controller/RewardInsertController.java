package com.blueice.challenge.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.blueice.challenge.model.service.RewardService;
import com.blueice.challenge.model.vo.Reward;
import com.blueice.challenge.model.vo.RewardAttachment;
import com.blueice.common.RewardFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class RewardInsertController
 */
@WebServlet("/insert.re")
public class RewardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 리워드 게시글 작성 후 DB에 추가 요청보내기
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리워드 게시글 추가 기능
		// 1. reward 테이블에 insert
		
		request.setCharacterEncoding("UTF-8");
		
		
		// multipart/form-data 형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			// => request 이 multipart/form-data 형식이라면 true 반환
			
			// 전송파일의 용량 제한
			int maxSize = 10 * 1024 * 1024;
			
			// 파일을 저장할 서버의 경로지정
			String savePath = request.getSession().getServletContext().getRealPath("/resources/images/event/");
			
			
			// 전달된 파일명 수정 및 서버에 업로드 작업
			MultipartRequest multiRequest = new MultipartRequest(request, savePath
																	, maxSize, "UTF-8", new RewardFileRenamePolicy());
			
			// DB 에 기록할 데이터를 뽑아서 VO 객체에 담기 : 제목, 내용
			String chalRewardTitle = multiRequest.getParameter("chalRewardTitle");	
			String chalRewardConts = multiRequest.getParameter("chalRewardConts");
		
			Reward r = new Reward();
			r.setChalRewardTitle(chalRewardTitle);
			r.setChalRewardConts(chalRewardConts);
			
			// 첨부파일도 VO 객체에 담기
			// upfile 키값으로 뽑기
			RewardAttachment ra = null;
			
			// 첨부파일 유무 검사
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// multiRequest.getoriginalfileName("키값")
				
				ra = new RewardAttachment();
				ra.setReOriginName(multiRequest.getOriginalFileName("upfile")); // 원본명
				ra.setReChangeName(multiRequest.getFilesystemName("upfile")); // 수정명
				
				ra.setReFilePath("resources/images/event/");
			}
			
			// 첨부파일이 없다면 ra == null
			
			// 서비스 요청
			int result = new RewardService().insertReward(r, ra);
			
			// 결과에 따른 응답페이지 지정
			if(result > 0) { // 성공
				
				request.getSession().setAttribute("alertMsg", "게시글 작성에 성공했습니다.");
				response.sendRedirect(request.getContextPath() + "/list.re?currentPage=1");
			}
			else { // 실패
				
				// 파일 삭제
				if(ra != null) {
					
					// 삭제하고자 하는 파일 객체를 생성 후 delete() 메소드 호출
					new File(savePath + ra.getReChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "게시글 작성에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
