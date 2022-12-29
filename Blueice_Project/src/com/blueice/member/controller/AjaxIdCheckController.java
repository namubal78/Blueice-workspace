package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxIdCheckController
 * 아이디 중복체크용 서블릿
 */
@WebServlet("/idCheck.me")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxIdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    request.setCharacterEncoding("UTF-8");
	    
        // 요청시 전달값 뽑기
        // checkId : 검사하고자 하는 아이디
        String checkId = request.getParameter("checkId");
        
        // 전달값을 서비스로 넘겨서 요청 처리 후 결과 받기
        int count = new MemberService().idCheck(checkId); // 중복된 아이디가 있다면 1, 없다면 0
        
        // 조건에 따른 응답데이터 넘겨주기
        // => 어차피 응답데이터가 한개만 넘어갈거기 때문에 굳이 JSON 은 쓰지 않음
        response.setContentType("text/html; charset=UTF-8");
        
        if(count > 0) { // 이미 사용중인 아이디가 있을 경우 => 사용 불가능 ("NNNNN")
            response.getWriter().print("NNNNN");
        }
        else { // 존재하는 아이디가 없을 경우 => 사용 가능 ("NNNNY")
            response.getWriter().print("NNNNY");
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
