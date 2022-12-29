package com.blueice.challenge.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueice.challenge.model.service.RewardService;

/**
 * Servlet implementation class RewardDeleteController
 */
@WebServlet("/delete.re")
public class RewardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RewardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	request.setCharacterEncoding("UTF-8");
		
		int chalRewardNo = Integer.parseInt(request.getParameter("rno"));
		
		int reFileNo = 0;
		
		String reFileNoStr = request.getParameter("rano");
		if(!reFileNoStr.equals("0")) { // !reFileNoStr.equals("0")
			reFileNo = Integer.parseInt(reFileNoStr);
		}
		
		
		int result = new RewardService().deleteReward(chalRewardNo, reFileNo);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/list.re?currentPage=1");
		} else {
			request.setAttribute("errorMsg", "게시글 삭제에 실패했습니다.");
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
