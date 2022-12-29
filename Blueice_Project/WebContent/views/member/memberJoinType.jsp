<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://kit.fontawesome.com/6ff74efdc8.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script> <!-- 온라인 방식 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border: 1px solid rgb(40, 78, 122); */
        box-sizing: border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1880px;
        margin: auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width: 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1350px; }
    #content_2>div { width: 100%; }
    #content_2_1 { height: 20%; float: left; }
    #content_2_2 { height: 60%; float: left; }
    #content_2_3 { height: 20%; float: left; }

    #header { height: 230px; background: #163A66; }

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

    /* content 영역 */
    #content>div { height: 100%; float: left; }
    #content_1 { width: 15%; }
    #content_2 { width: 70%; }
    #content_3 { width: 15%; }

    /* '북극곰이 ~ 선물해 주세요' 제목영역(form_1)과 나머지영역(form_2)과 버튼영역(form_3)를 나눔 */
    #content_2_2>div { width: 100% } 
    #form_1 { height: 10%; }
    #form_2 { height: 75%; }
    #form_3 { height: 15%; }

    /* 제목영역(form_1) 스타일 */
    #form_1>p { font-size: 27px; font-weight: bold; text-align: center; margin-top: 5px; }
    
    /* 나머지영역 중 가운데 width 80%(form_2_center)만 쓰기로 나눔 */
    #form_2>div { height: 100%; } 
    #form_2_left { width: 15%; float: left; }
    #form_2_center { width: 70%; float: left; }
    #form_2_right { width: 15%; float: left; }
    
    /* 가운데 영역 중 타입선택 영역(form_2_center_top)과 약관동의 영역(form_2_center_bottom)을 나눔 */
    #form_2_center>div { width: 100%; } 
    #form_2_center_top { height: 60%; padding-top: 25px; }
    #form_2_center_bottom { height: 40%; padding-top: 25px; }

    /*
        타입선택 영역 중 가운데 공간을 비우고자, 
        다섯개 영역들(grayBox_left,
                    grayBox_1,
                    grayBox_center,
                    grayBox_2,
                    grayBox_right) 로 나눔
    */
    #form_2_center_top>div { height: 100%; /* border: 1px solid orange; */ } 
    #grayBox_left { width: 15%; float: left; }
    #grayBox_1 { width: 30%; float: left; }
    #grayBox_center { width: 10%;  float: left; }
    #grayBox_2 { width: 30%; float: left; }
    #grayBox_right { width: 15%; float: left; }

    /* 두 그레이박스 영역 배경 스타일 */
    #grayBox_1, #grayBox_2 {
        background-color: #f0f0f0;
        border-radius: 80px;
        margin: auto;
        padding: 10px;
    }

    /* 두 그레이박스 아이콘 영역 */
    #icon_1, #icon_2 { width: 100%; height: 55%; }
    
    /* 두 그레이박스 아이콘 스타일 */
    #icon_1>#a1 {  
        padding-left: 90px; 
        padding-top: 30px;
        display: inline-block;
        color: rgb(114, 175, 199);
        text-align: center;
    }
    #icon_2>#a2 { 
        padding-left: 90px; 
        padding-top: 30px;
        display: inline-block;
        color: rgb(114, 175, 199); 
    }
    
    /* 두 그레이박스 텍스트 영역 나누고 스타일 */
    #text_1, #text_2 { width: 100%; height: 20%; text-align: center; /* border: 1px solid orange; */ }
    #text_1>div, #text_2>div { height: 50%; padding-top: 10px; /* border: 1px solid orange; */ }
    #text_1>div>p, #text_2>div>p { height: 100%; width: 100%; }
    
    #text_1_1, #text_2_1 { font-size: 18px; }
    #text_1_2, #text_2_2 { font-size: 24px; font-weight: bold; }

    /* 두 그레이박스 라디오버튼 스타일 */
    #radio_1, #radio_2 { width:100%; height: 25%; /* border: 1px solid red; */}
    #radio_1>p>input, #radio_2>p>input { 
        width: 100%;
        height: 100%;
        margin: auto;
        margin-top: 25px;
        zoom: 1.3; 
    }
    
    /* 약관동의 영역(form_2_center_bottom)을 
        가로줄1 영역(hr_1), 
        전체약관동의 영역(agreeTotal), 
        가로줄2 영역(hr_2), 
        세부약관동의 영역1(agreeDetail_1), 
        세부약관동의 영역2(agreeDetail_2), 
        세부약관동의 영역3(agreeDetail_3)
        다섯 영역으로 나눔
        */
    #form_2_center_bottom>div { width: 100%;}
    #hr_1 { height: 5%; }
    #agreeTotal { height: 30%; padding-top: 25px; font-size: 20px;} /* 전체약관동의 영역 스타일 */
    #hr_2 { height: 5%; }
    #agreeDetail_1, #agreeDetail_2, #agreeDetail_3 { height: 20%; }

    /* 세부약관동의 영역 스타일 */
    #agreeDetail_1>div, #agreeDetail_2>div, #agreeDetail_3>div { height: 100%; }
    .left { 
        width: 90%;
        float: left; 
        padding-left: 2px;
        font-size: 15px;
    }
    .right { 
        width: 10%; 
        float: left; 
        text-decoration: underline; 
        color: rgb(165, 165, 165); 
        font-size: 13px; 
        text-align: right;
        padding-right: 10px;
        cursor : pointer;
    }

    /* 전체약관동의 체크박스 스타일 */
    #ag { zoom: 1.5; } 

    /* 세부약관동의 영역 스타일 */
    #agreeTotal, #agreeDetail_1>div, #agreeDetail_2>div, #agreeDetail_3>div { padding-left: 10px; }

    /* 버튼 스타일 */
    button { 
        width: 200px;
    }
    button[class*=btn-primary] { background-color: #3970B3; }

    /* 약관 전문 스타일 */
    #form_2_center_bottom>div>div>p {
        border : 1px solid gray;
        width : 100%px;
        height : 100px;
        margin-top : 5px;
        border-radius : 10px;
        padding : 10px;
        box-sizing : border-box;
        display : none;
    }
    
    /*  조건 미충족 모달 스타일 */
    #modal.modal-overlay {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
        display: none;
        flex-direction: column;
        align-items: center;
        justify-content: center;

	}
	#modal .modal-window {
	    background: rgba(185, 212, 221, 0.8);
	    border-radius: 10px;
	    width: 350px;
	    height: 110px;
	    position: relative;
	    top: 78%;
	    padding: 20px;
	}
	#modal .title {
	    padding-left: 10px;
	    display: inline;
	    color: black;
	    
	}
	#modal .title h2 {
	    display: inline;
	    font-size: 20px;
	   
	}
	#modal .close-area {
	    display: inline;
	    float: right;
	    padding-right: 10px;
	    cursor: pointer;
	    color: black;
	}
	
	#modal .content {
	    margin-top: 20px;
	    padding: 0px 10px;
	    color: black;
	    font-size: 15px;
	}

	/* 세부약관 모달 스타일 */
	#modal1 .title, #modal2 .title, #modal3 .title {
	    padding-left: 5px;
	    display: inline;
	    color: black;
	    
	}
	#modal1 .title h2, #modal2 .title h2, #modal3 .title h2 {
	    display: inline;
	    font-size: 30px;
	   
	}
	#modal1 .close-area, #modal2 .close-area, #modal3 .close-area {
	    display: inline;
	    float: right;
	    padding-right: 10px;
	    cursor: pointer;
	    color: black;
	}
	
	#modal1 .content, #modal2 .content, #modal3 .content {
	    padding-left: 30px;
        margin-top: 20px;
	    padding: 0px 10px;
	    color: black;
	    font-size: 16px;
	}

    /* 세부약관1 창 크기 조절 스타일 */
    #modal1 .modal-window {
	    background: rgba(185, 212, 221, 0.8);
	    border-radius: 10px;
	    width: 1000px;
	    height: 700px;
	    position: relative;
	    top: 64%;
	    padding: 20px;
	}

    /* 세부약관2 창 크기 조절 스타일 */
    #modal2 .modal-window {
	    background: rgba(185, 212, 221, 0.8);
	    border-radius: 10px;
	    width: 700px;
	    height: 270px;
	    position: relative;
	    top: 64%;
	    padding: 20px;
	}

    /* 세부약관3 창 크기 조절 스타일 */
    #modal3 .modal-window {
	    background: rgba(185, 212, 221, 0.8);
	    border-radius: 10px;
	    width: 700px;
	    height: 300px;
	    position: relative;
	    top: 64%;
	    padding: 20px;
	}

    /* 세부약관1 창 노출 조절 스타일 */
    #modal1.modal-overlay {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
        display: none;
        flex-direction: column;
        align-items: center;
        justify-content: center;

	}

    /* 세부약관2 창 노출 조절 스타일 */
    #modal2.modal-overlay {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
        display: none;
        flex-direction: column;
        align-items: center;
        justify-content: center;

	}

    /* 세부약관3 창 노출 조절 스타일 */
    #modal3.modal-overlay {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
        display: none;
        flex-direction: column;
        align-items: center;
        justify-content: center;

	}

</style>
</head>
<body>
    <div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>회원가입</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1"></div>
                <div id="content_2_2">
                    <div id="modal" class="modal-overlay">
                        <div class="modal-window">
                            <div class="title">
                                <h2>회원가입 실패!</h2>
                            </div>
                            <div class="close-area">X</div>
                            <div class="content">
                                <p>회원타입 선택과 필수약관 동의를 해주세요!</p>                               
                            </div>
                        </div>
                    </div>
                    <div id="form_1"><p>북극곰이 발 디딜 수 있는 빙하를 선물해 주세요.</p></div>
                    <div id="form_2">
                        <div id="form_2_left"></div>
                        <div id="form_2_center">
                            <div id="form_2_center_top">
                                <div id="grayBox_left"></div>
                                <div id="grayBox_1">
                                    <div id="icon_1"><i class="fa-solid fa-user fa-5x" id="a1"></i></div>
                                    <div id="text_1">
                                        <div id="text_1_1"><p>14세 이상</p></div>
                                        <div id="text_1_2"><p><span style="color: rgb(114, 175, 199)">개인</span> 회원</p></div>
                                    </div>
                                    <div id="radio_1"><p><input type="radio" id="radioInput_1" name="radio" value="1"></p></div>
                                </div>
                                <div id="grayBox_center"></div>
                                <div id="grayBox_2">
                                    <div id="icon_2"><i class="fa-solid fa-building-user fa-5x" id="a2"></i></div>
                                    <div id="text_2">
                                        <div id="text_2_1"><p>일반 기업 및 2인 이상 단체</p></div>
                                        <div id="text_2_2"><p><span style="color: rgb(114, 175, 199)">기업 / 단체</span> 회원</p></div>
                                    </div>
                                    <div id="radio_2"><p><input type="radio" id="radioInput_2" name="radio"></p></div>
                                </div>
                                <div id="grayBox_right"></div>
                            </div>
                            <div id="form_2_center_bottom">
                                <div id="hr_1"><hr></div>
                                <div id="agreeTotal">
                                    <input type="checkbox" id="ag" name="agree_status" value="agree" >
                                    <label for="agree" id="agreeTotalText" style="font-weight: bold;">전체 약관에 동의합니다.</label>
                                </div>
                                <div id="hr_2"><hr></div>
                                <div id="agreeDetail_1">
                                    <div class="left">
                                        <input type="checkbox" id="ag_1" name="agree_status" value="agree">
                                        <label for="agree1">블루아이스 이용약관에 동의합니다. <span style="color:rgb(221, 1, 1);">(필수)</span></label>
                                    </div>
                                    <div class="right" id="right_1" onclick="detail_1();">자세히</div>
                                    <div id="modal1" class="modal-overlay">
                                        <div class="modal-window">
                                            <div class="title">
                                                <h2>이용약관</h2>
                                            </div>
                                            <div class="close-area">X</div>
                                            <div class="content">
                                                <p>
                                                    이용약관<br>
                                                    <br>
                                                    제1장 총칙<br>
                                                    <br>
                                                    제 1조 (목적)<br>
                                                    <br>
                                                    본 약관은 블루아이스가 관리 및 운영하는 웹사이트(“웹사이트”)에서 이용자에게 제공하는 제반 서비스와 관련하여 위원회와 이용자 간의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.<br>
                                                    <br>
                                                    제2조 (정의)<br>
                                                    <br>
                                                    본 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
                                                    “서비스”라 함은 구현되는 단말기와 관계없이 “웹사이트”에서 이용할 수 있는 일체 서비스를 의미합니다.<br>
                                                    “이용자”라 함은 “웹사이트”에 접속하여 제공되는 “서비스”를 이용하는 개인, 단체 또는 법인을 의미합니다.<br>
                                                    “회원”이라 함은 “웹사이트”에서 본 약관에 따라 “계정”과 “비밀번호”를 생성하여 등록하거나 자신의 SNS 계정으로 로그인하여 “서비스”를 이용할 수 있는 개인, 단체 또는 법인을 의미합니다.<br>
                                                    “계정”, “ID” 또는” 아이디”라 함은 회원의 식별 등을 위하여 “회원”이 선정하고 “웹사이트”에서 부여하는 문자와 숫자 등의 조합을 의미합니다.<br>
                                                    “비밀번호”라 함은 회원과 “계정”이 일치하는지 여부를 확인하고 통신상 비밀보호를 위하여 “회원”이 선정한 문자와 숫자 등의 조합을 의미합니다.<br>
                                                    “후원회원”이라 함은 “위원회” 활동을 지원하기 위하여 반대급부 없이 정기적 또는 비정기적으로 동산(금전, 유가증권, 기타 물건 포함) 및 부동산 등 재산을 기부하는 개인, 단체 또는 법인을 의미합니다.<br>
                                                    “콘텐츠”라 함은 “웹사이트”에 게시된 모든 이미지, 삽화, 영상, 텍스트, 오디오 등의 정보 형태의 글, 사진, 동영상 및 각종 파일과 링크를 의미합니다.<br>
                                                </p>                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="agreeDetail_2">
                                    <div class="left">
                                        <input type="checkbox" id="ag_2" name="agree_status" value="agree">
                                        <label for="agree2">개인정보 수집 및 이용에 동의합니다. <span style="color:rgb(221, 1, 1);">(필수)</span></label>
                                    </div>
                                    <div class="right" id="right_2" onclick="detail_2();">자세히</div>
                                    <div id="modal2" class="modal-overlay">
                                        <div class="modal-window">
                                            <div class="title">
                                                <h2>개인정보 수집 및 이용</h2>
                                            </div>
                                            <div class="close-area">X</div>
                                            <div class="content">
                                                <p>
                                                    목적 : 회원∙후원 상담, 관리, 긴급구호, 후원장려, 소식지 및 서비스 제공<br>
                                                    항목 : 성명, 아이디, 비밀번호, 전화번호, 이메일, 생년월일<br>
                                                    보유 기간 :	웹회원 탈퇴시까지<br>
                                                    <br>
                                                    * 위의 개인정보 수집∙이용에 대한 동의를 거부할 권리가 있으나, 동의를 거부할 경우 회원가입이 불가합니다.<br>
                                                </p>                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="agreeDetail_3">
                                    <div class="left">
                                        <input type="checkbox" id="ag_3" name="agree_status" value="agree">
                                        <label for="agree3">블루아이스 소식 및 후원 안내를 위한 개인정보 수집 및 이용 동의 <span style="color:rgb(221, 1, 1);">(선택)</span></label>
                                    </div>
                                    <div class="right" id="right_3" onclick="detail_3();">자세히</div>
                                    <div id="modal3" class="modal-overlay">
                                        <div class="modal-window">
                                            <div class="title">
                                                <h2>안내를 위한 개인정보 수집 및 이용 동의</h2>
                                            </div>
                                            <div class="close-area">X</div>
                                            <div class="content">
                                                <p>
                                                    블루아이스는 회원가입 시 수집된 개인정보를 이용하여 탈퇴 시까지 각종 및 후원 안내 등의 목적으로 본인에게 정보, 광고를 전달합니다.<br>
                                                    <br>
                                                    의무적으로 안내되어야 하는 정보성 내용은 수신동의 여부와 무관하게 제공됩니다.<br>
                                                    <br>
                                                    개인정보보호법 제22조 제4항에 의해 동의하지 않을 권리가 있으며, 동의하지 않는 경우에도 회원 가입이 가능합니다.<br>
                                                </p>                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="form_2_right"></div>
                    </div>
                    <div id="form_3" align="center">
                        <br><br><br>
                        <button type="submit" class="btn btn-primary" onclick="join();">회원가입하기</button>
                    </div>
                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

    <script>
        // 전체체크박스 함수
        $(document).ready(function() {
			
        	$("#ag").click(function() {
				if($("#ag").is(":checked")) $("input[name=agree_status]").prop("checked", true);
				else $("input[name=agree_status]").prop("checked", false);
			});
			
			$("input[name=agree_status]").click(function() {
				var total = $("input[name=agree_status]").length;
				var checked = $("input[name=agree_status]:checked").length;
				
				if(total != checked) $("#ag").prop("checked", false);
				else $("#ag").prop("checked", true); 
			});
		});
        
    	// 회원가입버튼 함수
    	function join() {
    		 
   			var radio1 = document.getElementById("radioInput_1");
   			var radio2 = document.getElementById("radioInput_2");
   			var agree1 = document.getElementById("ag_1");
   			var agree2 = document.getElementById("ag_2");
   			
   			var radioChecked1 = radio1.checked;
   			var radioChecked2 = radio2.checked;
   			var agreeChecked1 = agree1.checked;
   			var agreeChecked2 = agree2.checked;

   			const modal = document.getElementById("modal");
   			const closeBtn = modal.querySelector(".close-area")
			
   			if( 
   					radioChecked1 == true 
   				 && agreeChecked1 == true 
   				 && agreeChecked2 == true)
   			{ // 개인회원가입form으로 포워딩
   				
   				// location.href="<%= contextPath %>/ ";
                location.href="<%= contextPath %>/enrollForm.me";
   				
   			}
   			
   			else if ( 
   					radioChecked2 == true 
   				 && agreeChecked1 == true 
   				 && agreeChecked2 == true)
   			{ // 개인회원가입form으로 포워딩
   				
   				// location.href="<%= contextPath %>/ ";
                location.href="<%= contextPath %>/memberGroupEnrollForm.me";
   				
   			}
   			else { // 조건 미충족 시 모달
   			
   				modal.style.display = "flex"
				closeBtn.addEventListener("click", e => {
				    modal.style.display = "none"
				});
				window.addEventListener("keyup", e => {
				    if(modal.style.display === "flex" && e.key === "Escape") {
				        modal.style.display = "none"
				    }
				});
   			}
		}
        
        // 세부 약관 모달 함수
        function detail_1() {

            const modal1 = document.getElementById("modal1");
            const closeBtn1 = modal1.querySelector(".close-area");

            modal1.style.display = "flex"
            closeBtn1.addEventListener("click", e => {
                modal1.style.display = "none"
            });
            window.addEventListener("keyup", e => {
                if(modal1.style.display === "flex" && e.key === "Escape") {
                    modal1.style.display = "none"
                }
            });

        }

        function detail_2() {

        const modal2 = document.getElementById("modal2");
        const closeBtn2 = modal2.querySelector(".close-area");

        modal2.style.display = "flex"
        closeBtn2.addEventListener("click", e => {
            modal2.style.display = "none"
        });
        window.addEventListener("keyup", e => {
            if(modal2.style.display === "flex" && e.key === "Escape") {
                modal2.style.display = "none"
            }
        });
        }

        function detail_3() {

        const modal3 = document.getElementById("modal3");
        const closeBtn3 = modal3.querySelector(".close-area");

        modal3.style.display = "flex"
        closeBtn3.addEventListener("click", e => {
            modal3.style.display = "none"
        });
        window.addEventListener("keyup", e => {
            if(modal3.style.display === "flex" && e.key === "Escape") {
                modal3.style.display = "none"
            }
        });
        }
        
    </script>
</body>
</html>