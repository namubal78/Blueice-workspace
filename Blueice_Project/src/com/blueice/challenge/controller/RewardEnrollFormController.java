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

/**
 * Servlet implementation class RewardEnrollFormController
 */
@WebServlet("/enrollForm.re")
public class RewardEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardEnrollFormController() {
        super();
    }

	/**
	 * 리워드 글 작성 페이지
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리워드 게시글 작성하는 페이지 띄우기			
		ArrayList<Challenge> chList = new ChallengeService().ChallengeRewardCommentNo();
		
		int chListSize = chList.size();
		
		int firstComNo = chList.get(0).getChalComNo();
		
		int chalComNo1 = 0;
		int chalComNo2 = 0;
		int chalComNo3 = 0;
					
		for(int i = 1; i <= 5; i++) {

			chalComNo1 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			chalComNo2 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			
			if(chalComNo1 == chalComNo2) {
				
				chalComNo2 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			}
			
			chalComNo3 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			
			if(chalComNo2 == chalComNo3) {
				
				chalComNo3 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			}
			
			if(chalComNo3 == chalComNo1) {
				
				chalComNo3 = (int)(Math.random() * ((chList.get(chListSize - 1).getChalComNo()) - (firstComNo - 1))) + firstComNo;
			}
		}
		
		ChallengeComment cno = new ChallengeComment();
		cno.setChalComNo1(String.valueOf(chalComNo1));
		cno.setChalComNo2(String.valueOf(chalComNo2));
		cno.setChalComNo3(String.valueOf(chalComNo3));
		
		ArrayList<ChallengeComment> rewardName = new ChallengeService().ChallengeRewardName(cno);
		
		request.setAttribute("chList", chList);
		request.setAttribute("rewardName", rewardName);
		request.getRequestDispatcher("views/challenge/rewardNoticeEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
