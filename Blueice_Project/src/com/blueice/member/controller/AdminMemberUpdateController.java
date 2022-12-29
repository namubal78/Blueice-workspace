package com.blueice.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberUpdateController
 * 관리자 회원 업데이트 서블릿
 * 
 */
@WebServlet("/adminMemberUpdate.me")
public class AdminMemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		String memName = request.getParameter("memName");
		String memId = request.getParameter("memId");
		String phone = request.getParameter("phone");
		
		// 이메일 주소 앞 + 뒤 값 가져오기
		String emailBno = request.getParameter("emailBno");
		String emailAno = request.getParameter("emailAno");
		
		String zip = request.getParameter("zip");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		// 이메일 주소값 합치기
		String email = "";
		
		if((emailBno != null) && (emailAno != null)) {
			
			email = emailBno + "@" + emailAno;
		}
		
		// Member 타입의 객체에 값 담기
		Member m = new Member(memId, memName, address1, address2, zip, email, phone);
		System.out.println(m);
		
		// 서비스단으로 요청을 보내고 결과 받기
		Member updateMem = new MemberService().updateMember(m);
		
		
		// 돌려받은 처리 결과로 사용자가 보게 될 응답 페이지를 지정
		if(updateMem == null) { // 실패 => 에러문구를 담아서 에러페이지로 포워딩
			
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		else { // 성공

			request.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
			
			// admin_menubar.jsp 로 포워딩하는 controller 로 연결
			request.getRequestDispatcher("/adminMyPageLink.me").forward(request, response);
			
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
