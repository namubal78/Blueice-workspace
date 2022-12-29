<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.donation.model.vo.Donation" %>
<% 
    // 서버로부터 받아온 도네이션 테이블 한 행의 정보(STATUS 제외)
	Donation d = (Donation)request.getAttribute("d");
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


	/* donationTemEnrollForm.jsp 영역 ------------------------------------------------*/
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
<%-- 뒤로가기 방지 --%>
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
                <img src="resources/images/logo3_2.png" id="logo2">
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
                        <p style="color:#163A66;">일시 후원 / <%= d.getDoBill() %> </p>
                        <hr>
                        <h4>2. 후원자 정보</h4>
                        <%-- session의 loginMember 객체로부터 받아오기 --%>
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

                        <%-- post 버전으로 넘기기 위한 form hidden --%>
                        <form action="temUpdate.do" method="post">
                            <input type="hidden" name="rId"> <%-- 결제번호(부트페이 제공) --%>
                            <input type="hidden" name="oId"> <%-- 주문번호(도네이션 테이블 PK) --%>
                            <input type="hidden" name="rUrl"> <%-- 결제 영수증 URL(부트페이 제공) --%>
                            <input type="hidden" name="payMethod"> <%-- 결제 방식(부트페이 제공) --%>
                            <button type="submit" style="display:none;"></button>
                        </form>

                        <script>
                        // 로고 눌렀을 시 질척모달창, 모달창에서 홈으로 갈 시 입력한 가정보 삭제하는 서블릿 호출 
                        function toHome() {
                            location.href="deleteError.do?oId=" + "<%= d.getDoId() %>";
                        }

                        // 결제하기 버튼 클릭 시 부트페이 결제 함수 실행
                        $(document).ready(function() {
                            $("#bootPayment").click(function() {
                                toDona();
                            });
                        });

                        // 부트페이 결제 함수
                        async function toDona() {
                            try {
                                const response = await Bootpay.requestPayment({
                                    "application_id": "63425e75cf9f6d001e9269f8", // 부트페이에서 발급해주는 ID
                                    "price": <%= d.getDoBill() %>,
                                    "order_name": "북극곰 일시 후원",
                                    "order_id": "<%= d.getDoId() %>", 
                                    "tax_free": <%= d.getDoBill() %>,
                                    "user": { // loginMember 객체로부터 추출
                                        "id": "<%= loginMember.getMemId() %>",
                                        "username": "<%= loginMember.getMemName() %>",
                                        "phone": "<%= loginMember.getPhone() %>",
                                        "email": "<%= loginMember.getEmail() %>"
                                    },
                                    "items": [
                                        {
                                        "id": "temporary",
                                        "name": "일시후원",
                                        "qty": 1,
                                        "price": <%= d.getDoBill() %>
                                        }
                                    ],
                                    "extra": {
                                        "open_type": "iframe", // 결제창 열리는 방식
                                        "card_quota": "0", // 할부 개월 수(무할부)
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

                                        // 결제 완료 시 hidden으로 숨긴 input들 안에 부트페이 데이터를 넣고 서블릿으로 요청
                                        console.log(response);
                                        $("form>input[name=rId]").val(response.data.receipt_id);
                                        $("form>input[name=oId]").val(response.data.order_id);
                                        $("form>input[name=rUrl]").val(response.data.receipt_url);
                                        $("form>input[name=payMethod]").val(response.data.method);
                                        $("form>button[type=submit]").click();

                                        break;
                                    case 'confirm': //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                                        console.log(response.receipt_id)
                                        /**
                                         * 1. 클라이언트 승인을 하고자 할때
                                         * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                                         */
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
                                         */
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
                                        // 결제 실패 alert창과 함께 홈으로 가기 + 가정보 delete 하는 서블릿 호출
                                        location.href="deleteError.do?oId=<%= d.getDoId() %>";
                                        console.log(e.message);
                                        break;
                                    case 'error':
                                        // 결제 승인 중 오류 발생시 호출
                                        // 결제 실패 alert창과 함께 홈으로 가기 + 가정보 delete 하는 서블릿 호출
                                        console.log(e.error_code);
                                        location.href="deleteError.do?oId=<%= d.getDoId() %>";
                                        break;
                                }
                            }
                    }
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