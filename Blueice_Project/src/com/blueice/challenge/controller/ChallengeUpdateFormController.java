package com.blueice.challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.ChallengeService;
import com.blueice.challenge.model.vo.Challenge;

/**
 * Servlet implementation class ChallengeUpdateFormController
 */
@WebServlet("/updateForm.challenge") // 챌린지 게시글 수정 페이지로 가기 위한 서블릿
public class ChallengeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeUpdateFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 수정할 챌린지 게시글 번호 가져오기
		int chalNo = Integer.parseInt(request.getParameter("cno"));
				
		Challenge ch = new ChallengeService().updateFormChallenge(chalNo);
		
		request.setAttribute("ch", ch);
		request.getRequestDispatcher("views/challenge/challengeNoticeUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
