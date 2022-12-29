package com.blueice.challenge.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.RewardService;
import com.blueice.challenge.model.vo.Reward;

/**
 * Servlet implementation class RewardListController
 */
@WebServlet("/list.re")
public class RewardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 게시판 전체 리스트 조회
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리워드 전체 리스트 조회 후 조회결과를 담아서 응답페이지로 포워딩
		
		// Service 단으로 요청 보낸 후 결과 받기
		ArrayList<Reward> list = new RewardService().selectRewardList();
		
		// 응답페이지에서 필요하는 데이터를 request 영역에 담기
		request.setAttribute("list", list);
		
		// 리워드 페이지 포워딩
		request.getRequestDispatcher("views/challenge/rewardNotice.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
