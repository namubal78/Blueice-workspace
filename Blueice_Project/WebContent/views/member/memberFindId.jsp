<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /*border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1230px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 700px; }
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
        border: 1px solid skyblue;          
        border-radius: 45px;            
        padding: 20px 40px 20px 40px;
    }

    /* 전체폼 영역을 텍스트 영역(text), 입력폼 영역(form)으로 나눔 */
    
    #text { width: 100%; height: 35%; /* border: 1px solid green; */ }
    #form { width: 100%; height: 65%; /* border: 1px solid blue; */ }

    /* 텍스트 영역을 제목 영역(text_1), 설명 영역(text_2)으로 나눔 */
    #text>div { width: 100%;}
    #text_1 { height: 60%; margin-top: 20px; /* border: 1px solid red; */}
    #text_2 { height: 40%; margin-top: 10px; /* border: 1px solid red; */}
    
    /* 제목 영역과 설명 영역 p 태그 스타일 */
    #text_1>p { height: 100%; font-size: 30px; font-weight: bold;}
    #text_2>p { height: 100%; font-size: 15px;}

    /* 입력폼 영역을 성명입력 영역(form_1), 이메일입력 영역(form_2), 버튼 영역(button)으로 나눔 */
    #form>div { width: 100%;}
    #form_1 { height: 30%; /* border: 1px solid orange; */}
    #form_2 { height: 30%; /* border: 1px solid orange; */}
    #button { height: 40%;}

    /* 입력폼 영역 라벨 스타일 */
    #form_1>label { font-size: 13px; font-weight: bold; }
    #form_2>label { font-size: 13px; font-weight: bold; }

    /* 버튼 스타일 */
    button { width: 250px; }
    button[class*=btn-primary] { background-color: #3970B3; }
    /* [영섭작업영역끝] */

</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>아이디 찾기</p>
            </div>
            <div id="header_2">
                <img src="resources/images/logo/logo3_2.png" id="logo2">
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
                                    아이디 찾기
                                </p>
                            </div>
                            <div id="text_2">
                                <p>
                                    회원 가입 시 입력한 이메일로 아이디를 찾을 수 있습니다.
                                </p>
                            </div>
                        </div>
                        <form id="form" action="<%= contextPath %>/findId.me" method="post">
                            <div class="form-group" id="form_1">
                                <label for="usr">&nbsp;&nbsp;성명 <span style="color:rgb(221, 1, 1);">*</span></label>
                                <input type="text" class="form-control" name="memName" id="usr" required placeholder="회원가입 시 등록하신 이름을 입력해주세요.">
                            </div>
                            <div class="form-group" id="form_2">
                                <label for="email">&nbsp;&nbsp;이메일 <span style="color:rgb(221, 1, 1);">*</span></label>
                                <input type="email" class="form-control" id="email" name="email" required placeholder="@를 포함한 이메일 주소를 입력해주세요.">
                            </div>
                            <div id="button" align="center">
                                <button type="submit" class="btn btn-primary">확인</button>
                            </div>
                        </form>
                    </div>
                    <!-- [영섭작업영역끝] -->
            
                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>