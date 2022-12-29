package com.blueice.information.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.service.InquiryService;
import com.blueice.information.model.vo.Inquiry;

/**
 * Servlet implementation class AdminInquiryListSearchId
 */
@WebServlet("/adminMemId.in")
public class AdminInquiryListSearchId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquiryListSearchId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // post 방식으로 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        
        String searchId = request.getParameter("memId");
        
        // 페이징 처리
        int listCount; // 현재 총 게시글 개수
        int currentPage; // 현재 요청한 페이지(즉, 사용자가 요청한 페이지수)
        int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
        int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수
        int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지 수)
        int startPage; // 페이지 하단에 보여질 페이징바의 시작수
        int endPage; // 페이지 하단에 보여질 페이징바의 끝수
        
        listCount = new InquiryService().selectListCount();
        
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
        
        pageLimit = 10;
        
        boardLimit = 10;
        
        maxPage = (int)Math.ceil((double)listCount / boardLimit);
        
        startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
        
        endPage = startPage + pageLimit - 1;
        
        if(endPage > maxPage) {
            endPage = maxPage;
        }
        
        PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
                                    , maxPage, startPage, endPage);
        
         ArrayList<Inquiry> IdsearchList = new InquiryService().adminIdSearchList(pi, searchId);
         
        
        request.setAttribute("pi", pi);
        request.setAttribute("pageList", IdsearchList);
        
        request.getRequestDispatcher("views/information/adminInquirySearchIdList.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
