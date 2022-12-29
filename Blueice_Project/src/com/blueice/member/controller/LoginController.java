package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 * 로그인창 서블릿
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // POST방식이므로 인코딩 설정
	    request.setCharacterEncoding("UTF-8");
	    
	    String memId = request.getParameter("memId");
	    String memPwd = request.getParameter("memPwd");
	    String saveId = request.getParameter("saveId"); // "y"
	    
	       if(saveId != null && saveId.equals("y")) {
	            
	            Cookie cookie = new Cookie("saveId", memId); 
	            
	            cookie.setMaxAge(1 * 24 * 60 * 60); // 만료기간 1일 (초단위 작성)
	           
	            response.addCookie(cookie);
	            
	        }
	        else {
	            
	            Cookie cookie = new Cookie("saveId", memId);
	            cookie.setMaxAge(0); 
	            response.addCookie(cookie);
	          
	        }
	    
	    Member m = new Member();
	    m.setMemId(memId);
	    m.setMemPwd(memPwd);
	    
	    Member loginMember = new MemberService().loginMember(m);
	    
	    HttpSession session = request.getSession();
	    
	    if(loginMember == null) { // 로그인 실패 
	        
	        session.setAttribute("alertMsg", "로그인에 실패하였습니다. 다시 입력해주세요.");
	        response.sendRedirect(request.getContextPath() + "/LoginForm.me"); // 로그인 실패후 다시 로그인 창으로 돌아감
	        
	    } else { // 로그인 성공
	        
	        // 세션에 로그인 정보담기
	        session.setAttribute("loginMember", loginMember);
	                
	        if(loginMember.getMemId().equals("blueice123")) {  // 관리자 계정 로그인 성공
	            
	            
	            session.setAttribute("alertMsg", "관리자 계정으로 로그인 성공하였습니다.");
	            
	            response.sendRedirect(request.getContextPath() + "/adminMyPageLink.me"); // 관리자 로그인 후 관리자용 메인페이지로 가게
	            
	            
	        }
	        else {
	            
	            // 일반 회원 로그인 성공메세지
                session.setAttribute("alertMsg", "로그인에 성공하였습니다.");
	        
	            response.sendRedirect(request.getContextPath()); // 일반회원 로그인 후 메인페이지로 가게
	            
	        }
	        
	        
	    }
	    
	    // System.out.println(loginMember);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
