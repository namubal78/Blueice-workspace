package com.blueice.challenge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.RewardService;
import com.blueice.challenge.model.vo.Reward;
import com.blueice.challenge.model.vo.RewardAttachment;

/**
 * Servlet implementation class RewardUpdateFormController
 */
@WebServlet("/updateForm.re")
public class RewardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 리워드 게시글 수정
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int chalRewardNo = Integer.parseInt(request.getParameter("rno"));
		
		Reward r = new RewardService().updateFormReward(chalRewardNo);
		
		RewardAttachment ra = new RewardService().updateFormRewardAttachment(chalRewardNo);
		
		if(r != null) {
			
			request.setAttribute("r", r);
			request.setAttribute("ra", ra);
			
			request.getRequestDispatcher("views/challenge/rewardNoticeUpdateForm.jsp").forward(request, response);
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
