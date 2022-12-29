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
 * Servlet implementation class ChallengeDeleteController
 */
@WebServlet("/delete.challenge") // 챌린지 게시글 삭제를 위한 서블릿
public class ChallengeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 삭제할 챌린지 게시글 번호 가져오기
		int chalNo = Integer.parseInt(request.getParameter("cno"));
		
		int result = new ChallengeService().deleteChallenge(chalNo);

		if(result > 0) { // 삭제 성공 시
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "삭제 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.challenge");
		}
		else { // 삭제 실패 시
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
