package com.blueice.donation.controller;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.blueice.donation.model.service.DonationService;
import com.blueice.donation.model.vo.DonationRegular;
import com.blueice.member.model.vo.Member;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.Cancel;
import kr.co.bootpay.model.request.CashReceipt;
import kr.co.bootpay.model.request.Extra;
import kr.co.bootpay.model.request.Payload;
import kr.co.bootpay.model.request.Shipping;
import kr.co.bootpay.model.request.ShippingUser;
import kr.co.bootpay.model.request.Subscribe;
import kr.co.bootpay.model.request.SubscribeExtra;
import kr.co.bootpay.model.request.SubscribePayload;
import kr.co.bootpay.model.request.User;
import kr.co.bootpay.model.request.UserToken;
import kr.co.bootpay.model.response.ResDefault;


public class BootpayController {

    static Bootpay bootpay = new Bootpay("63425e75cf9f6d001e9269fb", "ELkWyZST5UKUb3BYEIS+cEBMP3nnM1MoAMcC7Lcte14=");

    /**
     * 토큰 발급받는 컨트롤러
     * @return token
     */
    public static String goGetToken() {
    	String token = null;
        try {
            HashMap<String, Object> res = bootpay.getAccessToken();
            if(res.get("error_code") == null) { //success
            	token = String.valueOf(res.get("access_token"));
                // System.out.println("토큰 발급 성공: " + token);
            } else {
                System.out.println("토큰 발급 실패: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return token;
    }
    
    /**
     * 빌링키 조회를 요청하는 컨트롤러
     * @param receiptId
     * @return billingkey
     */
    public static String lookupBillingKey(String receiptId) {
    	
    	String billingKey = null;
    	
        try {
            HashMap<String, Object> res = bootpay.lookupBillingKey(receiptId);
            billingKey = String.valueOf(res.get("billing_key"));
            if(res.get("error_code") == null) { //success
                // System.out.println("getReceipt success: " + res);
            } else {
                System.out.println("빌링키 발급 실패 : " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return billingKey;
    }
    
    
    /**
     * 정기결제 예약 컨트롤러
     * @param beforeDr
     * @param orderId
     * @return dr
     */
    /*
    public static DonationRegular reserveSubscribe(DonationRegular beforeDr, String orderId, Member m, HttpServletRequest request, HttpServletResponse response, String method) { // Member m도 같이 받기
        SubscribePayload payload = new SubscribePayload();
        
        // 정기결제 예약 id
    	String reserveId = null;
    	
        payload.billingKey = beforeDr.getBillingkey();
        payload.orderName = "북극곰 정기 후원";
        payload.price = 200; //beforeDr.getDoRegBill(); 
        payload.orderId = beforeDr.getDoRegId() + (System.currentTimeMillis() / 1000);
        payload.taxFree = 200; //beforeDr.getDoRegBill();
        payload.feedbackUrl = "https://934d-1-220-236-77.jp.ngrok.io/Blueice/requestNext.do";
        payload.contentType = "application/json";
        
        payload.user = new User();
        payload.user.phone = m.getPhone();
        payload.user.id = m.getMemId();
        payload.user.email = m.getEmail();
        payload.user.username = m.getMemName();
        
		Date now = new Date();
		if(orderId.equals(beforeDr.getDoRegId())) {
			now.setTime(now.getTime() + 1 * 1000); // 1초 뒤 결제
		}
		else {
			now.setTime(now.getTime() + 60 * 1000); // 180초 뒤 결제
			// 1000 == 1초
			// 1000 * 60 == 1분
			// 1000 * 60 * 60 == 1시간
			// 1000 * 60 * 60 * 24 == 1일
			// 1000 * 60 * 60 * 24 * 30 == 한 달
		}

		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		payload.reserveExecuteAt = sdf.format(now); // 결제 승인 시점
		payload.schedulerType = "oneshot";
		try {
			   HashMap res = bootpay.reserveSubscribe(payload);
			   JSONObject json =  new JSONObject(res);
			   // System.out.printf( "JSON: %s", json);
			   reserveId = String.valueOf(json.get("reserve_id"));
			   beforeDr.setReserveId(reserveId);
			   
			   if(res.get("error_code") == null) { //success

					int result = new DonationService().UpdateRegDonation(beforeDr);
					
					if(result > 0) {
						// System.out.println("정기결제 실결제 정보 등록 성공");
						
						DonationRegular dr2 = new DonationService().SelectRegDonation(beforeDr.getDoRegId());
						
						// System.out.println("실결제 등록 성공 후" + dr2);
						
						request.setAttribute("way", "정기");
						request.setAttribute("dr", dr2);
						request.setAttribute("method", method);
						request.getRequestDispatcher("views/donation/donationThanksForm.jsp").forward(request, response);
						
					}
					else {
						// 실패 시 에러페이지로 포워딩
						request.setAttribute("errorMsg", "결제에 실패하였습니다.");
						request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
					}
			   } else {
			       System.out.println("정기 결제 요청 실패 : " + res);
			       int result = new DonationService().DeleteErrorReg(beforeDr.getDoRegId());
			       int result2 = BootpayController.destroyBillingKey(beforeDr.getBillingkey());
			       
			       if(result * result2 > 0) {
			    	   request.getSession().setAttribute("alertMsg", "후원이 중단되었습니다. 후원을 원하시면 올바른 카드 정보로 다시 후원을 시도해주세요.");	        
			    	   response.sendRedirect(request.getContextPath()+"/enrollInfo.do");
				        
			       } else {
			    	   // 실패 시 에러페이지로 포워딩
			    	   request.setAttribute("errorMsg", "후원 시도 중 오류가 발생하였습니다. 관리자에게 문의해주세요.");
			    	   request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			       }
			   }
			} catch (Exception e) {
			   e.printStackTrace();
			}
		return beforeDr;
    }
    */
    
    public static DonationRegular reserveSubscribe(DonationRegular beforeDr, String orderId, Member m) { // Member m도 같이 받기
        SubscribePayload payload = new SubscribePayload();
        
        // 정기결제 예약 id
    	String reserveId = null;
    	
        payload.billingKey = beforeDr.getBillingkey();
        payload.orderName = "북극곰 정기 후원";
        payload.price = 200; //beforeDr.getDoRegBill(); 
        payload.orderId = beforeDr.getDoRegId() + (System.currentTimeMillis() / 1000);
        payload.taxFree = 200; //beforeDr.getDoRegBill();
        payload.feedbackUrl = "https://934d-1-220-236-77.jp.ngrok.io/Blueice/requestNext.do";
        payload.contentType = "application/json";
        
        payload.user = new User();
        payload.user.phone = m.getPhone();
        payload.user.id = m.getMemId();
        payload.user.email = m.getEmail();
        payload.user.username = m.getMemName();
        
		Date now = new Date();
		if(orderId.equals(beforeDr.getDoRegId())) {
			now.setTime(now.getTime() + 1 * 1000); // 1초 뒤 결제
		}
		else {
			now.setTime(now.getTime() + 60 * 1000); // 180초 뒤 결제
			// 1000 == 1초
			// 1000 * 60 == 1분
			// 1000 * 60 * 60 == 1시간
			// 1000 * 60 * 60 * 24 == 1일
			// 1000 * 60 * 60 * 24 * 30 == 한 달
		}

		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		payload.reserveExecuteAt = sdf.format(now); // 결제 승인 시점
		payload.schedulerType = "oneshot";
		try {
			   HashMap res = bootpay.reserveSubscribe(payload);
			   JSONObject json =  new JSONObject(res);
			   // System.out.printf( "JSON: %s", json);
			   reserveId = String.valueOf(json.get("reserve_id"));
			   beforeDr.setReserveId(reserveId);
			   
			   if(res.get("error_code") == null) { //success
			       // System.out.println("reserveSubscribe success: " + res);
			   } else {
			       System.out.println("정기 결제 요청 실패 : " + res);
			       new DonationService().DeleteErrorReg(beforeDr.getDoRegId());
			   }
			} catch (Exception e) {
			   e.printStackTrace();
			}
		return beforeDr;
    }
    
    /**
     * 정기결제 취소 컨트롤러
     * @param reserveId
     * @return
     */
    public static int reserveCancelSubscribe(String reserveId) {
    	
    	int result = 0;
    	
        try {
            HashMap<String, Object> res = bootpay.reserveCancelSubscribe(reserveId);
            if(res.get("error_code") == null) { //success
            	result = 1;
                // System.out.println("reserveCancelSubscribe success: " + res);
            } else {
                System.out.println("정기결제 예약 취소 실패 : " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    /**
     * 빌링키 삭제 컨트롤러
     * @param billingkey
     */
    public static int destroyBillingKey(String billingkey) {
    	int result = 0;
        try {
            HashMap<String, Object> res = bootpay.destroyBillingKey(billingkey);
            if(res.get("error_code") == null) { //success
            	result = 1;
                // System.out.println("destroyBillingKey success: 빌링키 제거 성공 " + res);
            } else {
                System.out.println("빌링키 삭제 실패 : " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    

    public static int receiptCancel(String receiptId) {
    	
    	int result = 0;
    	
        Cancel cancel = new Cancel();
        cancel.receiptId = receiptId;
        cancel.cancelUsername = "관리자";
        cancel.cancelMessage = "취소 요청";
//        cancel.price = 1000.0; //부분취소 요청시
//        cancel.cancelId = "12342134"; //부분취소 요청시, 중복 부분취소 요청하는 실수를 방지하고자 할때 지정
//        RefundData refund = new RefundData(); // 가상계좌 환불 요청시, 단 CMS 특약이 되어있어야만 환불요청이 가능하다.
//        refund.account = "675601012341234"; //환불계좌
//        refund.accountholder = "홍길동"; //환불계좌주
//        refund.bankcode = BankCode.getCode("국민은행");//은행코드
//        cancel.refund = refund;

        try {
            HashMap<String, Object> res = bootpay.receiptCancel(cancel);
            if(res.get("error_code") == null) { //success
            	result = 1;
                // System.out.println("receiptCancel success: " + res);
            } else {
                System.out.println("receiptCancel false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    
    /**
     * 정기결제 요청 컨트롤러
     * @param billingKey
     * @param doRegId
     * @param doRegBill
     * @param m
     * @return dr
     */
    public static DonationRegular requestSubscribe(DonationRegular dr, Member m) {
        
    	SubscribePayload payload = new SubscribePayload();
        payload.billingKey = dr.getBillingkey();
        payload.orderName = "북극곰 정기 후원";
        payload.orderId = dr.getDoRegId();
        payload.price = 200; // doRegBill;
        
        payload.user = new User();
        payload.user.phone = m.getPhone();
        payload.user.id = m.getMemId();
        payload.user.email = m.getEmail();
        payload.user.username = m.getMemName();
        payload.schedulerType = "oneshot";
        
        // DonationRegular dr = null;

        try {
            HashMap<String, Object> res = bootpay.requestSubscribe(payload);
            if(res.get("error_code") == null) { //success
            	
            	// 카드데이터를 String -> Map
            	Gson gson = new Gson();
            	String cardStr = gson.toJson(res.get("card_data"));
            	Type type = new TypeToken<Map<String,String>>(){}.getType();
            	Map<String,String> cardData = gson.fromJson(cardStr, type);
            	
            	// 정기결제 예약 id
            	String reserveId = null;
            	
        		Date now = new Date();
        		now.setTime(now.getTime() + 180 * 1000); // 180초 뒤 결제
        		//
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        		payload.reserveExecuteAt = sdf.format(now); // 결제 승인 시점
        		
        		try {
        			   HashMap res2 = bootpay.reserveSubscribe(payload);
        			   JSONObject json =  new JSONObject(res2);
        			   System.out.printf( "JSON: %s", json);
        			   reserveId = String.valueOf(json.get("reserve_id"));
        			   
        			   if(res.get("error_code") == null) { //success
        			       System.out.println("reserveSubscribe success: " + res);
        			   } else {
        			       System.out.println("reserveSubscribe false: " + res);
        			   }
        			} catch (Exception e) {
        			   e.printStackTrace();
        			}
        		
            	
            	dr = new DonationRegular();
        		//dr.setDoRegId(doRegId);
        		dr.setReceiptRegId(String.valueOf(res.get("receipt_id")));
        		dr.setReceiptRegURL(cardData.get("receipt_url"));
        		dr.setReserveId(reserveId);
        		//dr.setBillingkey(billingKey);
        		
                System.out.println("정기결제 결제 요청 성공: " + res);
            } else {
                System.out.println("정기결제 결제 요청 실패: " + res);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dr;
    }
    

    
    
    
    public static void getBillingKey() {
        Subscribe subscribe = new Subscribe();
        subscribe.orderName = "정기결제 테스트 아이템";
        subscribe.subscriptionId = "" + (System.currentTimeMillis() / 1000);
        subscribe.pg = "나이스페이";
        subscribe.cardNo = "5570**********1074"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribe.cardPw = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribe.cardExpireYear = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribe.cardExpireMonth = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribe.cardIdentityNo = ""; //생년월일 또는 사업자 등록번호 (- 없이 입력)


        subscribe.user = new User();
        subscribe.user.username = "홍길동";
        subscribe.user.phone = "01011112222";
        subscribe.extra = new SubscribeExtra();
        subscribe.extra.subscribeTestPayment = 1;

        try {
            HashMap<String, Object> res = bootpay.getBillingKey(subscribe);
            JSONObject json =  new JSONObject(res);
            System.out.printf( "JSON: %s", json);

            if(res.get("error_code") == null) { //success
                System.out.println("getBillingKey success: " + res);
            } else {
                System.out.println("getBillingKey false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    




    public static void getUserToken() {
        UserToken userToken = new UserToken();
        userToken.userId = "1234"; // 개발사에서 관리하는 회원 고유 번호
        try {
            HashMap<String, Object> res = bootpay.getUserToken(userToken);
            if(res.get("error_code") == null) { //success
                System.out.println("getUserToken success: " + res);
            } else {
                System.out.println("getUserToken false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void requestLink() {
        Payload payload = new Payload();
        payload.orderId = "1234";
        payload.price = 1000d;
        payload.orderName = "테스트 결제";
        payload.pg = "payapp";
//        payload.method = "vbank";

        User user = new User();
        user.username = "홍길동";
        user.phone = "01012341234";
        payload.user = user;

        Extra extra = new Extra();
        payload.extra = extra;


        try {
            ResDefault res = bootpay.requestLink(payload);
            System.out.println("requestLink:" + res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void confirm() {
        String receiptId = "6319773ad01c7e0032171270";
        try {
            HashMap<String, Object> res = bootpay.confirm(receiptId);
            JSONObject json =  new JSONObject(res);
            System.out.printf( "JSON: %s", json);
            if(res.get("error_code") == null) { //success
                System.out.println("confirm success: " + res);
            } else {
                System.out.println("confirm false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getReceipt() {
        String receiptId = "6323e0dcd01c7e001e91f151";
        try {
            HashMap<String, Object> res = bootpay.getReceipt(receiptId);
            JSONObject json =  new JSONObject(res);
            System.out.printf( "JSON: %s", json);
            if(res.get("error_code") == null) { //success
                System.out.println("getReceipt success: " + res);
            } else {
                System.out.println("getReceipt false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void lookupPaymentMethods() {
//        String receiptId = "62e1e2f2cf9f6d002705a7fa";
        try {
            HashMap<String, Object> res = bootpay.lookupPaymentMethods();
            JSONObject json =  new JSONObject(res);
            System.out.printf( "JSON: %s", json);
            if(res.get("error_code") == null) { //success
                System.out.println("getReceipt success: " + res);
            } else {
                System.out.println("getReceipt false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void certificate() {
        String receiptId = "630d5997d01c7e003f5aa109";
        try {
            HashMap<String, Object> res = bootpay.certificate(receiptId);
            if(res.get("error_code") == null) { //success
                System.out.println("certificate success: " + res);
            } else {
                System.out.println("certificate false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shippingStart() {
        Shipping shipping = new Shipping();
        shipping.receiptId = "628ae7ffd01c7e001e9b6066";
        shipping.trackingNumber = "123456";
        shipping.deliveryCorp = "CJ대한통운";
        ShippingUser user = new ShippingUser();
        user.username = "홍길동";
        user.phone = "01000000000";
        user.address = "서울특별시 종로구";
        user.zipcode = "08490";
        shipping.user = user;
        try {
            HashMap<String, Object> res = bootpay.shippingStart(shipping);
            if(res.get("error_code") == null) { //success
                System.out.println("certificate success: " + res);
            } else {
                System.out.println("certificate false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cashReceipt() {
        CashReceipt cashReceipt = new CashReceipt();
        cashReceipt.pg = "토스";
        cashReceipt.price = 1000;
        cashReceipt.orderName = "테스트";
        cashReceipt.cashReceiptType = "소득공제";
        cashReceipt.identityNo = "01000000000";

        Date now = new Date();
        now.setTime(now.getTime()); //10초 뒤 결제

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        cashReceipt.purchasedAt = sdf.format(now); // 결제 승인 시점
        cashReceipt.orderId = String.valueOf(now.getTime());


        try {
            HashMap<String, Object> res = bootpay.requestCashReceipt(cashReceipt);
            if(res.get("error_code") == null) { //success
                System.out.println("cashReceipt success: " + res);
            } else {
                System.out.println("cashReceipt false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cashReceiptCancel() {
        Cancel cancel = new Cancel();
        cancel.receiptId = "62f48ae41fc192036f9f4b54";
        cancel.cancelMessage = "테스트 결제";
        cancel.cancelUsername = "테스트 관리자";


        try {
            HashMap<String, Object> res = bootpay.requestCashReceiptCancel(cancel);
            if(res.get("error_code") == null) { //success
                System.out.println("cashReceiptCancel success: " + res);
            } else {
                System.out.println("cashReceiptCancel false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cashReceiptBootpay() {
        CashReceipt cashReceipt = new CashReceipt();
        cashReceipt.receiptId = "62e0f11f1fc192036b1b3c92";

        cashReceipt.username = "테스트";
        cashReceipt.email = "test@bootpay.co.kr";
        cashReceipt.phone = "01000000000";

        cashReceipt.identityNo = "01000000000";
        cashReceipt.cashReceiptType = "소득공제";


        try {
            HashMap<String, Object> res = bootpay.requestCashReceiptByBootpay(cashReceipt);
            if(res.get("error_code") == null) { //success
                System.out.println("cashReceiptBootpay success: " + res);
            } else {
                System.out.println("cashReceiptBootpay false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cashReceiptBootpayCancel() {
        Cancel cancel = new Cancel();

        cancel.receiptId = "62e0f11f1fc192036b1b3c92";
        cancel.cancelMessage = "테스트 결제";
        cancel.cancelUsername = "테스트 관리자";


        try {
            HashMap<String, Object> res = bootpay.requestCashReceiptCancelByBootpay(cancel);
            if(res.get("error_code") == null) { //success
                System.out.println("cashReceiptBootpayCancel success: " + res);
            } else {
                System.out.println("cashReceiptBootpayCancel false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}