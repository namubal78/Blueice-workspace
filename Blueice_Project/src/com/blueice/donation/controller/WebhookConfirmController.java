package com.blueice.donation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.blueice.donation.model.service.DonationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class WebhookConfirm
 */
@WebServlet("/WebhookConfirm")
public class WebhookConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebhookConfirmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BootpayController.goGetToken();
		
        StringBuffer strBuffer = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
               strBuffer.append(line);
            }
        } catch (Exception e) {
            System.out.println("웹훅 통지 JSON 읽어오기 실패 : " + e.toString());
            
        }
        
		// 웹훅 통지
		response.setContentType("application/json; charset=UTF-8");
		JSONObject jObj = new JSONObject();
		jObj.put("success", true);
		response.getWriter().print(jObj);

        // Request Body 출력
        System.out.println(strBuffer.toString());
        
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(strBuffer.toString());
        Type type = new TypeToken<Map<String,Object>>(){}.getType();
    	Map<String,Object> confirmData = new Gson().fromJson(jsonObject, type);
    	

    	
    	// Map<String,Object> payloadData = (Map<String, Object>)confirmData.get("payload"));
    	//System.out.println(String.valueOf(payloadData.get("order_id")));
    	
    	if(confirmData.get("error_code") != null) {
    		
        	String payloadData = String.valueOf(confirmData.get("payload"));
        	String[] dataArr = payloadData.split(", ");
        	String originId = dataArr[21];
        	System.out.println(originId);
    		
        	// String orderId = String.valueOf(confirmData.get("order_id"));
        	// blueice_reg_donation_0001261667843930
        	
    		String doRegId = originId.substring(8, 35);
    		System.out.println(doRegId);
    		
    		int result = new DonationService().DeleteErrorReg(doRegId);
    		
    		if(result > 0) {
    			request.getSession().setAttribute("alertMsg", "후원이 중단되었습니다. 다시 시도해주세요.");	        
    	        response.sendRedirect(request.getContextPath()+"/enrollInfo.do");
    	        
    		} else {
    			// 실패 시 에러페이지로 포워딩
    			request.setAttribute("errorMsg", "후원 시도 중 오류가 발생하였습니다. 관리자에게 문의해주세요.");
    			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
    		}

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
