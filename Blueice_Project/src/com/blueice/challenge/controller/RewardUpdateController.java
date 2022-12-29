package com.blueice.challenge.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.blueice.challenge.model.service.RewardService;
import com.blueice.challenge.model.vo.Reward;
import com.blueice.challenge.model.vo.RewardAttachment;
import com.blueice.common.RewardFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class RewardUpdateController
 */
@WebServlet("/update.re")
public class RewardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 리워드 게시글 수정
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
						
			int maxSize = 10 * 1024 * 1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/images/event/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath
																	, maxSize, "UTF-8", new RewardFileRenamePolicy());
			
			int chalRewardNo = Integer.parseInt(multiRequest.getParameter("rno"));
			String chalRewardTitle = multiRequest.getParameter("chalRewardTitle");	
			String chalRewardConts = multiRequest.getParameter("chalRewardConts");
			
			Reward r = new Reward();
			r.setChalRewardNo(chalRewardNo);
			r.setChalRewardTitle(chalRewardTitle);
			r.setChalRewardConts(chalRewardConts);
			
			RewardAttachment ra = null;
			
			// 첨부파일 유무 검사
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				// multiRequest.getoriginalfileName("키값")
				
				ra = new RewardAttachment();
				ra.setReOriginName(multiRequest.getOriginalFileName("reUpfile")); // 원본명
				ra.setReChangeName(multiRequest.getFilesystemName("reUpfile")); // 수정명
				
				ra.setReFilePath("resources/images/event/");
				
				if(multiRequest.getParameter("ReOriginFileNo") != null) {
					
					ra.setReFileNo(Integer.parseInt(multiRequest.getParameter("ReOriginFileNo")));
				}
				else {
					
					ra.setReRefCno(chalRewardNo);
				}
				
				// 서비스 요청
				int result = new RewardService().updateReward(r, ra);
				
				// 결과에 따른 응답페이지 지정
				if(result > 0) { // 성공
					
					if(multiRequest.getParameter("ReOriginFileName") != null
							&& savePath + multiRequest.getParameter("reUpfile") != null) {
						
						// 기존에 존재하던 첨부파일 삭제 구문
						new File(savePath + multiRequest.getParameter("ReOriginFileName")).delete();
					}
					
					request.getSession().setAttribute("alertMsg", "게시글 수정에 성공했습니다.");
					response.sendRedirect(request.getContextPath() + "/detail.re?rno=" + chalRewardNo);
				}
				else { // 실패
					
					request.setAttribute("errorMsg", "게시글 수정에 실패했습니다.");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				}
			}
			else {
				int result = new RewardService().updateReward(r, ra);
				
				HttpSession session = request.getSession();
				request.getSession().setAttribute("alertMsg", "게시글 수정에 성공했습니다.");
				response.sendRedirect(request.getContextPath() + "/detail.re?rno=" + chalRewardNo);
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
