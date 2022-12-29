package com.blueice.challenge.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.ChallengeService;
import com.blueice.challenge.model.vo.Challenge;
import com.blueice.challenge.model.vo.ChallengeComment;
import com.blueice.common.model.vo.PageInfo;

/**
 * Servlet implementation class ChallengeCommentController
 */
@WebServlet("/comment.challenge") // 챌린지 게시글 상세보기, 챌린지 게시글에 대한 댓글 확인을 위한 서블릿
public class ChallengeCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeCommentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 상세보기 할 챌린지 게시글 번호 가져오기
		int chalNo = Integer.parseInt(request.getParameter("cno"));
		
		// 조회수 증가를 위해 서비스로 전달
		int count = new ChallengeService().countListChallenge(chalNo);
		
		// 챌린지 게시글 정보를 가져오는 구문
		Challenge ch = new ChallengeService().selectChallengeComment(chalNo);
						
		// 페이징 처리
		int listCount; // 현재 총 게시글 개수
		int currentPage; // 현재 요청한 페이지(즉, 사용자가 요청한 페이지수)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수
		int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		listCount = new ChallengeService().selectListCount(chalNo);
				
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 5;
		
		boardLimit = 5;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
				
		if(endPage >= maxPage) {
			endPage = maxPage;
		}

		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
									, maxPage, startPage, endPage);
		
		// 해당 챌린지 번호의 댓글을 가져오기 위한 구문
		ArrayList<ChallengeComment> chList = new ChallengeService().selectChallengeCommentList(pi, chalNo);
		
		
		
		request.setAttribute("ch", ch);
		request.setAttribute("pi", pi);
		request.setAttribute("chList", chList);
		
		request.getRequestDispatcher("views/challenge/challengeComment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
