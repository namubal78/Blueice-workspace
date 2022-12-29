package com.blueice.challenge.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.ChallengeService;
import com.blueice.challenge.model.vo.Challenge;

/**
 * Servlet implementation class ChallengeNoticeListController
 */
@WebServlet("/list.challenge") // 챌린지 게시판 전체 조회를 위한 서블릿
public class ChallengeNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeNoticeListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		ArrayList<Challenge> list = new ChallengeService().selectChallengeList();
		
		LocalDate now = LocalDate.now();
				
		request.setAttribute("now", now);
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/challenge/challengeNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
