<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기</title>
<style>

    /* 전체를 감싸는 wrap -------------------------------------ㄴ- */
	.wrap {
	    width: 98%;
	    height: 1680px;
	    margin : auto; /* 가운데로 좌, 우 자동 정렬*/
	}

    /* header 영역 --------------------------------------------*/
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

    
	/* content 영역 -----------------------------------------------*/
	#content { height: 1150px;}
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

	/* donationFirstForm.jsp 영역 --------------------------- */

    /* 프로그레스 바(progress) CSS */
	#content_2_1>div {
		width: 50%;
		margin:0 auto;
		margin-top:50px;
		vertical-align: center;
	}
	
    /* 가로 크기 조절을 위해 추가로 컨텐트 영역을 감싸는 div .container의 CSS */
	#content_2_2>form>div {
		width:80%;
		margin:auto;
	}

    /* 후원방식 지정 영역의 css */
	.donation-method>div {
		width:100%;
		height: 50px;
		margin:auto;
		margin-top: 10px;
	}

    /* 후원 금액별 문구에 대한 css */
	#donation-prompt {
		padding:10px 20px;
		background-color: #c0cedf;
	}

    /* 부트스트랩4 primary 버튼에 대한 css */
	button[class*=btn-primary] { background-color: #3970B3; height:50px; }

    /* 총 후원금액 쪽 css 영역*/
	.totalBill>div {
		float:left;
		color: #3970B3;
		font-size: 30px;
		height:100%;
	}

	.totalBill_1 {
		width: 45%;
		padding-left: 30px;
	}

    .totalBill_2 { width:55%; }

	.totalBill_1+div>input {
		width:85%;
		border: none;
		color: #163A66;
		font-size: 30px;
		text-align: right;
	}

    /* '다음' 버튼에 대한 css */
    #next-btn, #submit-btn {
        background:#163A66;
        height: 60px;
        line-height: 45px;
    }

    /* 부트스트랩4 form 스타일에 대한 css 영역 */
    .form-control {
        display: inline-block !important;
        height: 50px !important;
    }

    /* hr 가로줄에 대한 css 영역*/
	hr {
		border-top: 2px solid rgb(194, 192, 192) !important; 
	}

    /* 후원자 정보입력 영역의 h4 요소에 대한 css 영역 */
    .donation-info h4 {
        color: rgb(194, 192, 192)
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
        
        <script>
	
			// 메인페이지 alertMsg 호출
			var msg = "<%= alertMsg %>"; 
			
			if(msg != "null") {
				alert(msg);
				<% session.removeAttribute("alertMsg"); %>
			}

		</script>

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
                        <div class="progress-bar" style="width:50%; background-color: #3970B3; text-align: right; padding-right:10px;">1</div>
                    </div>
                </div>
                <div id="content_2_2">
                    <%-- action은 나중에 백엔드에서 서블릿으로 연결 --%>
                    <form action="" method="post">
                        <div class="container">
                            <!-- 모달창 -->
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
                            <script>     
                                function toHome() {
                                    location.href="<%= contextPath %>";
                                }
                            </script>

                            <!-- 후원 방법 -->
                            <div class="donation-method">
                                <hr>
                                <h4 class="display-4">1. <small>후원방법</small></h4>
                                <hr>
                                <h4>후원방법</h4>
                                <div class="btn-group btn-group-lg" id="way-area">
                                    <button type="button" class="btn btn-primary" value="정기">정기후원</button>
                                    <button type="button" class="btn btn-light" value="일시">일시후원</button>
                                </div>
                                <hr>
                                <h4>후원금액(원)</h4>
                                <div class="btn-group" id="bill-area_1">
                                    <button type="button" class="btn btn-primary" value="3">30,000</button>
                                    <button type="button" class="btn btn-light" value="5">50,000</button>
                                    <button type="button" class="btn btn-light" value="10">100,000</button>
                                </div>
                                <div class="btn-group" id="bill-area_2">
                                    <button type="button" class="btn btn-light" value="20">200,000</button>
                                    <button type="button" class="btn btn-light" value="30">300,000</button>
                                    <div style="margin-left: 10px; width:33%;">
                                        <input type="text" style="display:inline-block; height:50px; width:80%;" id="bill-free" oninput='handleOnInput(this, 3)' placeholder="1000만원 이상 후원은 문의" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"> 
                                        만 원
                                    </div>
                                </div>
                                <div class="invalid-feedback billInvalid" style="text-align: right; height:5px;">
                                    후원금은 만 원 이상부터 가능합니다.</div>
                                
                                <br><br>
                                    
                                <p id="donation-prompt"> 
                                    <%-- 클릭 시 구문이 바뀔 수 있도록(.text("치환할구문")) 제이쿼리 추가 및 문구 생각 --%>
                                    환경문제는 단기간에 해결되기 어렵고, 오랜 기간 캠페인을 진행해야 하기 때문에 지속적인 후원이 무엇보다 중요합니다.<br>
                                    소중한 후원을 통해 장기 캠페인을 보다 효과적으로 펼칠 수 있습니다.<br><br>
                                </p>
                                <br>
    
                                <hr>
                                <div class="totalBill">
                                    <div class="totalBill_1">총 후원금액</div>
                                    <div class="totalBill_2"><input type="text" id="confirmBill" readonly value="0"> 원</div>
                                </div>
                                <hr>
                                <br clear="both">
                                <button type="button" id="next-btn" class="btn btn-primary btn-block">다음</button>    
                            </div>

                            <div class="donation-info" style="display:none;">
                                <hr>
                                <h4>1. 후원방법</h4>
                                <input type="hidden" name="doBill">
                                <p id="info-area1" style="color:#163A66; display:inline-block;">정기 후원</p> / 
                                <p id="info-area2" style="color:#163A66; display:inline-block;">30,000</p>
                                <%-- 제이쿼리로 윗단에서 받은 정보를 여기에 바로 뿌려주기 --%>
                                <hr>
                                <h4 class="display-4" style="color:#163A66;">2. <small>후원자 정보</small></h4>
                                <hr>
                                <br>
                                <div class="form-group">
                                    <label for="memName">성명 :</label>
                                    <input type="text" class="form-control" name="memName" readonly value="<%= loginMember.getMemName() %>">
                                    <%-- 얘 value 현재 loginMember의 값으로 val() 덮어씌우기 loginMember.getMemName() --%>
                                    <%-- <div class="valid-feedback nameValid">유효한 이름입니다.</div>
                                    <div class="invalid-feedback nameInvalid">한글로 된 2~6자의 이름을 입력해주세요.</div> --%>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="phone">휴대폰 번호 :</label>
                                    <input type="text" class="form-control" name="phone" readonly value="<%= loginMember.getPhone() %>">
                                    <%-- <div class="valid-feedback phoneValid">유효한 휴대폰 번호입니다.</div>
                                    <div class="invalid-feedback phoneInvalid">유효하지 않은 휴대폰 번호입니다. 010을 포함하여 '-'를 빼고 숫자만 입력하세요.</div> --%>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="text">이메일 :</label>
                                    <input type="text" class="form-control" name="email" readonly value="<%= loginMember.getEmail() %>">
                                    <%-- <div class="valid-feedback emailValid">유효한 이메일입니다.</div>
                                    <div class="invalid-feedback emailInvalid">유효하지 않은 이메일 주소입니다. 영문자와 '@'을 입력해서 이메일 형식으로 입력해주세요.</div> --%>
                                </div>
                                <br>
                                <button id="submit-btn" class="btn btn-primary btn-block">다음</button>
                                <%-- 이거 넘기는 순간 여태까지 받은 정보들 전부 서버로 넘기기 --%>
                                <br>
    
                                <hr>
                                <h4>3. 결제 정보</h4>
                                <hr>
                                


                                <script>

                                function handleOnInput(el, maxlength) {
                                    if(el.value.length > maxlength)  {
                                        el.value 
                                        = el.value.substr(0, maxlength);
                                    }
                                }

                                // 후원 방식 추출하는 함수
                                let way = "정기";
                                $(function() {
                                    console.log(way);
            
                                    $("#way-area>button").click(function() {
                                        $("#way-area>button").attr("class", "btn btn-light");
                                        $(this).attr("class", "btn btn-primary");
            
                                        way = $(this).val();
                                        console.log(way);

                                        $("#info-area1").text(way+"후원");
                                        
                                    });

                                });
            
                                // 후원 금액 추출하는 함수
                                $(function() {
                                    let bill = 3;
                                    $("input[name=doBill]").val(bill*10000);
                                    let newBill;
            
                                    $("#confirmBill").val(bill+"0000");
                                    console.log(bill);
            
                                    $("div[id*=bill-area]>button").click(function() {
                                        $(".billInvalid").css("display", "none");
                                        $("div[id*=bill-area]>button").attr("class", "btn btn-light");
                                        $(this).attr("class", "btn btn-primary");
            
                                        bill = $(this).val();
                                        $("#confirmBill").val(bill+"0000");
                                        $("#info-area2").text(bill*10000);
                                        $("input[name=doBill]").val(bill*10000);

                                        console.log(bill);
                                    });
            
                                    // click 이벤트가 발생 시 기존의 mouseenter, mouseout 이벤트 제거할 수도 있음 => off 메소드 사용
                                    // 이벤트 부여할 때 : on 메소드
                                    // 이벤트 제거할 때 : off 메소드
                                    // $(this).off("mouseenter").off("mouseout");
                                    // 메소드 체이닝으로 연달아서 이벤트 핸들러 제거 가능
            
                                    $("#bill-free").on({click:function() {
                                        $("div[id*=bill-area]>button").attr("class", "btn btn-light");

                                    }, blur:function() {
                                        // 금액 자유 입력 최종값을 저장하는 메소드
                                        bill = $(this).val();
                                        console.log(bill);
                                        $("#info-area2").text(bill*10000);
                                        $("input[name=doBill]").val(bill*10000);
                                       
                                    }, keyup:function() {
                                        
                                        newBill = $("#bill-free").val();
                                        // 금액 자유 입력 시 유효성 검사를 해주는 함수
                                        // 숫자만 + 1만원 이상
                                        if(!/^1|[1-9]+[0-9]*$/.test(newBill)) {  
                                            $(".billInvalid").css("display", "block");

                                            $(this).val("");
                                            $(this).focus();
                                        } else {$(".billInvalid").css("display", "none"); }
                                        // 금액 자유 입력 시 입력할 때마다 후원금액이 얼마인지 바꾸게 해주는 함수
                                        $("#confirmBill").val(newBill+"0000");
                                    }});
            
                                    $("#bill-free").keyup(function() {
            
                                        let content = $(this).val();
                                        if(content == "") {
                                            $("#confirmBill").val("0");
                                        }
            
                                    });
            
                                    $("#bill-free").click(function() {
                                        let content = $(this).val();
                                        if(content != "") {
                                            $("#confirmBill").val(content+"0000");
                                        }

                                    });
                                    
                                    
                                });
                                
                                // 후원 방식, 후원자 정보 div 교체
                                // 정기결제, 일시결제 페이지창으로 나눠서 연결
                                $("#next-btn").click(function() {
                                    window.scrollTo(0,0);
                                    $(".donation-method").css("display", "none");
                                    $(".donation-info").css("display", "block");

                                    if(way === "정기") {
                                        // 정기후원 페이지로 연결하는 서블릿 호출
                                        $("form").attr("action", "regEnroll.do");
                                    } else {
                                        // 일시후원 페이지로 연결하는 서블릿 호출
                                        $("form").attr("action", "temEnroll.do");
                                    }
                                });

                                </script>
                                
                            </div>
                        </div>
                    </form>   
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