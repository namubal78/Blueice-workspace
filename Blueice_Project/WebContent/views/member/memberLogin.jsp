<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.member.model.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1530px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1000px; }
    #content_2>div { width: 100%;}
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
    #content>div { height : 100%; float : left; }
    #content_1 { width : 15%; }
    #content_2 { width : 70%; }
    #content_3 { width : 15%; }

    /* [영섭작업영역시작] */
    #contentForm { /* content_2_2 영역에 들어가는 전체폼 영역(contentForm) 스타일 */
        width: 45%;         
        height: 100%; 
        margin: auto;        
        border: solid skyblue;          
        border-radius: 45px;            
        padding: 20px 60px 20px 60px;
    }

    /* 전체폼 영역을 텍스트 영역(text), 입력폼 영역(form)으로 나눔 */
    #contentForm>div { width: 100%;} 
    #text { height: 30%; /* border: 1px solid green; */ }
    #form { height: 70%; /* border: 1px solid blue; */ }

    /* 텍스트 영역을 제목 영역(text_1), 설명 영역(text_2)으로 나눔 */
    #text>div { width: 100%;}
    #text_1 { height: 65%; margin-top: 20px; /* border: 1px solid red; */}
    #text_2 { height: 35%;  /* border: 1px solid red; */}
    
    /* 제목 영역과 설명 영역 p 태그 스타일 */
    #text_1>p {
        height: 100%;
        font-size: 40px; 
        font-weight: bold;
    }
    #text_2>p { height: 100%; font-size: 15px;}

    /* 입력폼 영역을 성명입력 영역(form_1), 이메일입력 영역(form_2), 버튼 영역(button), 아이디저장 영역(idSave), 링크 영역(form_3)으로 나눔 */
    #form>div { width: 100%; }
    #form_1 { height: 20%; /* border: 1px solid orange; */}
    #form_2 { height: 20%; /* border: 1px solid orange; */}
    #button { height: 20%;}
    #idSave { height: 5%;}
    #form_3 { height: 20%;}

    /* 입력폼 영역 라벨 스타일 */
    #form_1>label { font-size: 15px; font-weight: bold; }
    #form_2>label { font-size: 15px; font-weight: bold; }

    /* 버튼 스타일 */
    button { width: 100%; }
    button[class*=btn-primary] { background-color: #3970B3; }
    
    /* 링크 영역(form_3)을 아이디찾기링크 영역(link_1), 링크공백 영역1(link_left), 비밀번호찾기링크 영역(link_2), 링크공백 영역2(link_right), 회원가입링크 영역(link_3)으로 나눔 */
    #form_3>div { 
        height: 100%; 
        float: left;
        display: block;
        font-size: 14px;
        text-align: center;
        color: gray;
    }
    #link_1 { width: 30%; }
    #link_left { width: 5%; }
    #link_2 { width: 30%; }
    #link_right { width: 5%; }
    #link_3 { width: 30%; }

    /* 체크박스 스타일 */
    #saveId {
        margin-left: 5px;
    }

    /* [영섭작업영역끝] */
    
    

</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>로그인</p>
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

                    <!-- [영섭작업영역시작] -->
                    <div id="contentForm">
                        <div id="text" align="center">
                            <div id="text_1">
                                <p>
                                    로그인
                                </p>
                            </div>
                            <div id="text_2">
                                <p>
                                    북극곰이 발을 내딛을 수 있는 빙하를 선물해주세요.
                                </p>
                            </div>
                        </div>
                        <form id="login-form" action="/Blueice/login.me" method="post">
                        <div id="form">
                            <div class="form-group" id="form_1">
                                <p>&nbsp;아이디</p>
                                <input type="text" class="form-control" id="memId" name="memId" required placeholder="아이디를 입력해주세요.">
                            </div>
                            <div class="form-group" id="form_2">
                                <p>&nbsp;비밀번호</p>
                                <input type="password" class="form-control" id="memPwd" name="memPwd" required placeholder="비밀번호를 입력해주세요.">
                            </div>
                            <div class="form_group" id="idSave">
                                <input type="checkbox" id="saveId" name="saveId" value="y">
	                		    <label for="saveId">&nbsp;아이디 저장</label>
                            </div>
                            <div id="button" align="center">
                                <br>
                                <button type="submit" class="btn btn-primary">로그인</button>
                            </div>
                            <div class="form-group" id="form_3">
                                <hr>
                                <div class="link" id="link_1">
                                    <a href="<%= contextPath %>/findIdLink.me" style="text-decoration:none; color:black;">아이디 찾기</a>
                                </div>
                                <div id="link_left">
                                    <p>|</p>
                                </div>
                                <div class="link" id="link_2">
                                    <a href="<%= contextPath %>/findPasswordLink.me" style="text-decoration:none; color:black;">비밀번호 찾기</a>
                                </div>
                                <div id="link_right">
                                    <p>|</p>
                                </div>
                                <div class="link" id="link_3">
                                    <a href="<%= contextPath %>/joinForm.me" style="text-decoration:none; color:black;">회원가입</a>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                    
                <!-- 메뉴바 저장된 쿠키정보를 이용해 아이디저장 기능 이용 -->
			    <script>
			    	// 모든 요소들이 화면에 다 로딩 된 후 saveId 라는 자바변수에 저장된 값을 불러와서
			    	// 아이디 입력창에 value 속성으로 설정해둘것 & 아이디 저장하기 체크박스에 체크 수행
			    	$(function() {
			    		
			    		var saveId = "<%= saveId %>"; 
			    		
			    		if(saveId != "") { // 쿠키가 있는 경우
			    			
			    			$("#login-form input[name=memId]").val(saveId);
			    			$("#saveId").attr("checked", true);
			    		}
			    		
			    	});
			    
			    </script>
                    
                    

                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>