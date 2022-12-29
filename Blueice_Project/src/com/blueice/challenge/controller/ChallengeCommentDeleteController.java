package com.blueice.challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.challenge.model.service.ChallengeService;

/**
 * Servlet implementation class ChallengeCommentDeleteController
 */
@WebServlet("/delete.challengeComment") // 챌린지 댓글 삭제를 위한 서블릿
public class ChallengeCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeCommentDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// url을 위한 챌린지 게시글 번호 가져오기
		int chalNo = Integer.parseInt(request.getParameter("cno"));
		
		// 삭제할 챌린지 댓글 번호 가져오기
		int ccno = Integer.parseInt(request.getParameter("ccno"));
		
		int result = new ChallengeService().deleteChallengeComment(ccno);
		
		if(result > 0) { // 챌린지 댓글 삭제 성공 시
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "삭제 되었습니다.");
			request.getRequestDispatcher("/comment.challenge?cno=" + chalNo + "&currentPage=1").forward(request, response);			
		}
		else { // 챌린지 댓글 삭제 실패 시
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
