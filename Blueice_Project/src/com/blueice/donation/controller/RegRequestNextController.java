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
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.service.MemberService;
import com.blueice.member.model.vo.Member;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class RegRequestNextSubscribe
 */
@WebServlet("/requestNext.do")
public class RegRequestNextController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegRequestNextController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 정기결제 예약 서블릿
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
       // System.out.println(strBuffer.toString());
        
        // Request Body JSON 처리.
        // 자세한 Request Body 항목은 하단의 [Webhook 메시지 구성] 참조
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(strBuffer.toString());
        Type type = new TypeToken<Map<String,Object>>(){}.getType();
    	Map<String,Object> data = new Gson().fromJson(jsonObject, type);
    	
    	String orderId = String.valueOf(data.get("order_id"));
    	// System.out.println("웹훅 오더아이디 : " + orderId);
    	String billingkey = String.valueOf(data.get("billing_key"));
    	// System.out.println("빌링키" + billingkey);
    	String receiptRegURL = String.valueOf(data.get("receipt_url"));
        
    	DonationRegular beforeDr = new DonationService().SelectRegDonaWithBilling(billingkey);
    	String doRegId = beforeDr.getDoRegId();
    	
    	// 해당 빌링키를 가진 회원의 회원번호(PK)를 이용한 회원정보 조회
    	int memNo = Integer.parseInt(beforeDr.getRegDonator());
    	Member m = new MemberService().selectMemberNo(memNo);
    	
    	// System.out.println("도네이션레귤러 vo : " + beforeDr);
    	
    	DonationRegular dr = BootpayController.reserveSubscribe(beforeDr, orderId, m);
    	String reserveId = dr.getReserveId();

    	DonationRegular dr2 = new DonationRegular();
    	dr2.setDoRegId(doRegId);
    	dr2.setReserveId(reserveId);
    	dr2.setReceiptRegURL(receiptRegURL); // url 세팅

    	if (reserveId != null && !reserveId.isEmpty()) {
    		
    		// 카운트랑 reserveId, url 업데이트 updateCount
    		int result = new DonationService().UpdateSubscribe(dr2);
    		
    		if(result < 1) {
    			System.out.println("정기결제 예약 실패! : " + dr.getDoRegId());
        		int cancel = BootpayController.reserveCancelSubscribe(reserveId);
        		cancel++; // cancel이 2라면 error로 취소된 정기결제
        		// 오류로 예약 취소되었다는 걸 알리는 메소드를 만들어야 할듯? 상태값을 E로 바꾼다던가.. 이럼 또 컬럼 바꿔야겠네ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
        		
        		new DonationService().CancelRegDonation(cancel, reserveId);
    		}
    	}
    	else {
    		System.out.println("정기결제 예약 실패! : " + dr.getDoRegId());
    		int cancel = BootpayController.reserveCancelSubscribe(reserveId);
    		cancel++; // cancel이 2라면 error로 취소된 정기결제
    		// 오류로 예약 취소되었다는 걸 알리는 메소드를 만들어야 할듯? 상태값을 E로 바꾼다던가.. 이럼 또 컬럼 바꿔야겠네ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
    		
    		new DonationService().CancelRegDonation(cancel, reserveId);
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
