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
 * Servlet implementation class GroupMemberInsertController
 * 단체회원 가입 서블릿
 */
@WebServlet("/groupInsert.me")
public class GroupMemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
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
        String groupName = request.getParameter("groupName");
        String memName = request.getParameter("memName");
        String phone = request.getParameter("phone");
        
        // 이메일 전 + 후
        String emailBno = request.getParameter("emailBno");
        String emailAno = request.getParameter("emailAno");
        
        String zip = request.getParameter("zip");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        
        // 이메일 주소 합치기
        String email = "";
        if((emailBno != null) && (emailAno != null)) {
            email = emailBno + "@" + emailAno;
        } 
        
        // 생성자 오버로딩때문에 groupName만 setter로 넣었음
        Member m = new Member(memId, memPwd, memName, address1, address2, zip, email, phone);
        m.setGroupName(groupName);
        
        int result = new MemberService().groupInsertMember(m);
        
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
