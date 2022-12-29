package com.blueice.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 * 개인회원 가입용 서블릿
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // POST방식이므로 인코딩 설정
	    request.setCharacterEncoding("UTF-8");
	    
	    // 전달값 뽑기
	    String memId = request.getParameter("memId");
	    String memPwd = request.getParameter("memPwd");
	    String memName = request.getParameter("memName");
	    
	    // 주민등록번호 앞 + 뒷자리 추출
	    String personalBno = request.getParameter("personalBno");
	    String personalAno = request.getParameter("personalAno");
	    
	    // 이메일주소 앞 + 뒷자리 추출
	    String emailBno = request.getParameter("emailBno");
	    String emailAno = request.getParameter("emailAno");
	    
	    String phone = request.getParameter("phone");
	    String zip = request.getParameter("zip");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    
	    // 주민등록번호 합치기
        String personalNo = "";
        if((personalBno != null) && (personalAno != null)) {
            personalNo = personalBno + personalAno;
        } 
        
        // 이메일 주소 합치기
        String email = "";
        if((emailBno != null) && (emailAno != null)) {
            email = emailBno + "@" + emailAno;
        } 
        
	    Member m = new Member(memId, memPwd, memName, address1, address2, zip, email, phone, personalNo);
	    // System.out.println(m);
	    
	    int result = new MemberService().insertMember(m);
	    
        if(result > 0) { // 회원가입 성공
            
            HttpSession session = request.getSession();
            session.setAttribute("alertMsg", "회원가입에 성공했습니다.");
            
            response.sendRedirect(request.getContextPath() + "/joinwelcome.me");
            
        } else { // 회원가입 실패
            
            request.setAttribute("errorMsg", "회원가입에 실패하였습니다.");
        
            RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
            view.forward(request, response);
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
