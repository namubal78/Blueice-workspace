package com.blueice.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class GroupMemberListDateController
 */
@WebServlet("/groupMemberListDate.me")
public class GroupMemberListDateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMemberListDateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String enrollDate = request.getParameter("enrollDate");
		
		int listCount; // 현재 총 회원수
		int currentPage; // 현재 요청한 페이지 (즉, 사용자가 요청한 페이지수)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit; // 한 페이지에 보여질 최대 회원수 (몇개 단위씩 리스트가 보여질껀지)
		
		int maxPage; // 가장 마지막 페이지가 몇번페이지인지 (총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		listCount = new MemberService().selectListCountGroupDate(enrollDate);

		// System.out.println(listCount);
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));

		pageLimit = 5;

		boardLimit = 10;

		maxPage = (int)Math.ceil((double)listCount / boardLimit);

		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이지 정보들 (7개의 변수)을 하나의 공간에 담아서 보내자
		// => 페이지 정보들을 담을 VO 클래스를 하나 더 만들것!!
		//	  (공지사항이나 사진게시판에서도 쓰일것이므로 common 패키지에 만들것임)
		
		// 1. PageInfo 객체로 가공 (조회할때, 페이징바 만들때 필요)
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
								 , maxPage, startPage, endPage);

		// pi 를 넘기면서 서비스로 요청
		// 2. list 에는 해당 페이지에서 보여져야할 게시글들의 목록들 (목록 만들때 필요)
		ArrayList<Member> list = new MemberService().selectGroupMemberListDate(enrollDate, pi);
		
		request.setAttribute("list", list);
		request.setAttribute("enrollDate", enrollDate);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/member/adminGroupMemberDate.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
