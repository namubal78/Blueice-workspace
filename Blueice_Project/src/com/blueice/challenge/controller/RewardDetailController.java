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
import com.blueice.challenge.model.vo.RewardAttachment;
import com.sun.xml.internal.ws.api.message.Attachment;

/**
 * Servlet implementation class RewardDetailController
 */
@WebServlet("/detail.re")
public class RewardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 게시글 상세보기
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reward = Integer.parseInt(request.getParameter("rno"));
		int chalRewardNo = reward;
		
		// 조회수 증가용 서비스 요청 후 성공 시 상세조회 요청
		int result = new RewardService().increaseCount(chalRewardNo);
		
		if(result > 0) {
		
			// reward 해당 게시글 정보 뽑아오기
			Reward r = new RewardService().selectReward(reward);
			
			// RewardAttachment 해당 게시글에 있는 첨부파일들 조회
			RewardAttachment ra = new RewardService().selectRewardAttachment(chalRewardNo);
			request.setAttribute("r", r);
			
			if(ra != null) {
				request.setAttribute("ra", ra);
			}
			
			request.getRequestDispatcher("views/challenge/rewardDetailView.jsp").forward(request, response);

			
		}
		else {
			
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
