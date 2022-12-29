<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.donation.model.vo.DonationRegular" %>
<% 
	DonationRegular dr = (DonationRegular)request.getAttribute("dr");
    String token = String.valueOf(request.getAttribute("token"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기</title>
<style>

    /* 전체를 감싸는 wrap */
	.wrap {
	    width: 98%;
	    height: 1680px;
	    margin : auto; /* 가운데로 좌, 우 자동 정렬*/
	}

    /* header 영역 */
	#header { 
		height: 230px;
		background: #163A66;
	}

	#header>div { height: 100%; float: left; }

	#header_1 { width: 70%; }

	#header_1>p {
		box-sizing: border-box;
		font-size: 45px;
		font-weight: bold;
		margin-top: 130px;
		margin-left: 150px;
		color: white;
	}

	#header_2 { width: 30%; }

	#header_2>img {
		height: 60%;
		margin-left: 150px;
		margin-top: 70px;
	}

    
	/* content 영역 -------------------------------------------------------*/
	#content { height: 1050px;} /* 세로를 100px 정도 줄였습니다 */
	#content>div {
		height : 100%; 
		float: left;
	}
	#content_1 { width : 15%; }
	#content_2 { width : 70%; }
	#content_2>div { width: 100%; }
	#content_2_1 { height: 10%; float: left; }
	#content_2_2 { height: 80%; float: left; }
	#content_2_3 { height: 10%; float: left; }
	#content_3 { width : 15%; }


	/* donationRegEnrollForm.jsp 영역 ------------------------------------------------*/
    /* 프로그레스 바(progress) CSS */
	#content_2_1>div {
		width: 50%;
		margin:0 auto;
		margin-top:50px;
		vertical-align: center;
	}
	
    /* 가로 크기 조절을 위해 추가로 컨텐트 영역을 감싸는 div .container의 CSS */
	#content_2_2>div {
		width:80%;
		margin:auto;
	}

    /* 결제하기 버튼 css */
    #bootPayment {
        background:#163A66;
        height: 50px;
        line-height: 35px;
    }

    /* h4에 대한 css 영역 */
    h4 {
        color: rgb(194, 192, 192)
    }
    
    /* hr 가로줄에 대한 css 영역*/
	hr {
		border-top: 2px solid rgb(194, 192, 192) !important; 
	}

</style>

</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
    <script type="text/javascript">
        window.history.forward();
        function noBack(){window.history.forward();}
    </script>

    <div class="wrap">

        <!-- menubar -->
        <%@ include file="../common/donation_menubar.jsp" %>

        <!-- header -->
        <div id="header">
            <div id="header_1">
                <p>후원하기</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo3_2.png" id="logo2">
            </div>
        </div>

        <!-- content -->
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1">
                    <div class="progress">
                        <div class="progress-bar" style="width:100%; background-color: #3970B3; text-align: right; padding-right:10px;">2</div>
                    </div>
                </div>
                <div id="content_2_2" style="margin-top:5px;">
                    <div class="container">
                        <div id="please" class="modal modalContainer">
                            <div class="modalDialog">
                                <div class="popupBody">
                                    조금만 더 입력하시면 후원자님의<br> 소중한 나눔이 기후 위기를 막을 수 있습니다.
                                </div>
                                <div class="popupFooter">
                                    <button type="button" class="cancelDona" onclick="toHome();">취소</button>
                                    <button type="button" class="keepGoing" data-dismiss="modal">계속</button>
                                </div>
                            </div>
                        </div>

                        <hr>
                        <h4>1. 후원방법</h4>
                        <p style="color:#163A66;">정기 후원 / <%= dr.getDoRegBill() %> </p>
                        <hr>
                        <h4>2. 후원자 정보</h4>
                        <p style="color:#163A66;"><%= loginMember.getMemName() %> / <%= loginMember.getPhone() %> / <%= loginMember.getEmail() %> </p>
                        <hr>
                        <h4 class="display-4" style="color:#163A66;">3. <small>결제 정보</small></h4>
                        <hr>
                        <br>
                        <div class="card bg-info text-white">
                            <div class="card-body" style="background-color: #3970b392;">
                                <b>신용카드 후원 시 유의사항</b><br><br>   

                                &nbsp;신용카드의 경우, 카드사에 전달하는 과정을 거쳐야 합니다. <br>
                                신청하신 약정일로부터 영업일(평일) 기준 2일 전에 신청하신 경우에만 해당 월에 후원이 가능합니다. <br>
                                &nbsp;또한,  결제일에 정기후원이 시작되어 매 결제일로부터 30일 뒤 요청하신 금액이 자동 승인됩니다. <br>
                                정기 결제 취소 문의는 관리자에게 상담하세요.<br><br><br>

                                <b>정기 후원 시 유의사항</b><br><br>
                                정기 결제 시 결제일 당일에 정기후원이 시작되어 매달 약정하신 금액이 자동 승인됩니다. <br>
                                기타 관련 문의는 고객센터 > 1:1 문의로 관리자에게 문의하세요. 
                            </div>
                        </div>

                        <br>
                        <button type="button" id="bootPayment" class="btn btn-primary btn-block">후원하기</button>
                        <br>

                        <form action="regUpdate.do" method="post">
                            <input type="hidden" name="bill" value="<%= dr.getDoRegBill() %>">
                            <input type="hidden" name="phone" value="<%= loginMember.getPhone() %>">
                            <input type="hidden" name="rId">
                            <input type="hidden" name="oId" value="<%= dr.getDoRegId() %>">
                            <input type="hidden" name="payMethod">
                            <button type="submit" style="display:none;"></button>
                        </form>

                        <script>
                        
                        function toHome() {
                            location.href="deleteErrorReg.do?oId=" + "<%= dr.getDoRegId() %>";
                        }

                        $(document).ready(function() {
                            $("#bootPayment").click(function() {
                                toSubscribe();
                            });
                        });

                        function toSubscribe() {

                            Bootpay.requestSubscription({
                            application_id: '63425e75cf9f6d001e9269f8',
                            pg: '케이씨피',
                            //price: 100, // <%= dr.getDoRegBill() %>,
                            //tax_free: 100, // <%= dr.getDoRegBill() %>,
                            order_name: '북극곰 정기 후원',
                            subscription_id: '<%= dr.getDoRegId() %>', // 우리가 보내는 고유 PK
                            user: {
                                username: '<%= loginMember.getMemName() %>',
                                phone: '<%= loginMember.getPhone() %>',
                                email : '<%= loginMember.getEmail() %>'
                            },
                            extra: {
                                subscription_comment: '매월' + <%= dr.getDoRegBill() %> + '원이 결제됩니다',
                                subscribe_test_payment: false
                            }
                        }).then(
                            function (response) {
                                if (response.event === 'done') {
                                    console.log('빌링키 발급이 완료되었습니다.');
                                    console.log(response);
                                    $("form>input[name=rId]").val(response.data.receipt_id);
                                    $("form>input[name=payMethod]").val(response.data.method);
                                    $("form>button[type=submit]").click();  
                                }
                            },
                            function (error) {
                                console.log(error.message);
                                location.href="deleteErrorReg.do?oId=" + "<%= dr.getDoRegId() %>";
                            }
                        )
                        }
                        

                        
                        /*
                        async function toDona() {

                            try {
                                const response = await Bootpay.requestPayment({
                                    "application_id": "63425e75cf9f6d001e9269f8",
                                    "price": 100, // <%= dr.getDoRegBill() %>,
                                    "order_name": "펭귄후원",
                                    "order_id": "<%= dr.getDoRegId() %>",
                                    "tax_free": 100, // <%= dr.getDoRegBill() %>,
                                    "user": {
                                        "id": "<%= loginMember.getMemId() %>",
                                        "username": "<%= loginMember.getMemName() %>",
                                        "phone": "<%= loginMember.getPhone() %>",
                                        "email": "<%= loginMember.getEmail() %>"
                                    },
                                    "items": [
                                        {
                                        "id": "regular",
                                        "name": "정기후원",
                                        "qty": 1,
                                        "price": 100 // <%= dr.getDoRegBill() %>
                                        }
                                    ],
                                    "extra": {
                                        "open_type": "Popup",
                                        "card_quota": "0",
                                        "escrow": false,
                                        "display_success_result" : true,
                                        "display_error_result" : true
                                        // "separately_confirmed" : true
                                    }
                                })

                                switch (response.event) {
                                    case 'issued':
                                        // 가상계좌 입금 완료 처리
                                        break;
                                    case 'done':
                                        console.log(response);
                                        $("form>input[name=rId]").val(response.data.receipt_id);
                                        $("form>input[name=oId]").val(response.data.order_id);
                                        $("form>input[name=rUrl]").val(response.data.receipt_url);
                                        $("form>input[name=payMethod]").val(response.data.method);
                                        $("form>button[type=submit]").click();
                                        /*
                                        $.ajax({
                                            url : "regUpdate.do",
                                            data : {
                                                rId : response.data.receipt_id,
                                                oId : response.data.order_id,
                                                rUrl : response.data.receipt_url
                                            },
                                            type : "post"
                                        })
                                        
                                        console.log(response.data.receipt_id);
                                        // 결제 완료 처리
                                        break;
                                    case 'confirm': //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                                        console.log(response.receipt_id)
                                        /**
                                         * 1. 클라이언트 승인을 하고자 할때
                                         * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                                         
                                        const confirmedData = await Bootpay.confirm() //결제를 승인한다
                                        if(confirmedData.event === 'done') {
                                            //결제 성공
                                        } else if(confirmedData.event === 'error') {
                                            //결제 승인 실패
                                        }

                                        /**
                                         * 2. 서버 승인을 하고자 할때
                                         * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                                         * Bootpay.destroy(); //결제창을 닫는다.
                                         
                                        break;
                                }
                            } catch (e) {
                                // 결제 진행중 오류 발생
                                // e.error_code - 부트페이 오류 코드
                                // e.pg_error_code - PG 오류 코드
                                // e.message - 오류 내용
                                console.log(e.message)
                                switch (e.event) {
                                    case 'cancel':
                                        // 사용자가 결제창을 닫을때 호출
                                        location.href="deleteErrorReg.do?oId=" + "<%= dr.getDoRegId() %>";
                                        console.log(e.message);
                                        break;
                                    case 'error':
                                        // 결제 승인 중 오류 발생시 호출
                                        console.log(e.error_code);
                                        location.href="deleteErrorReg.do?oId=" + "<%= dr.getDoRegId() %>";
                                        break;
                                }
                            }
                            
                        }*/


                        </script>
                    </div> 
                </div>

            </div>
            <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>

        <!-- footer-->
        <%@ include file="../common/footerbar.jsp"%>
    </div>

</body>
</html>