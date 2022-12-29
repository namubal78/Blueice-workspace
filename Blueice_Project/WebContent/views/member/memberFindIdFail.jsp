<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1130px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 600px; }
    #content_2>div { width: 100%;}
    #content_2_1 { height: 30%; float: left; }
    #content_2_2 { height: 50%; float: left; }
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
    #content>div { height: 100%; float : left; }
    #content_1 { width: 15%; }
    #content_2 { width: 70%; }
    #content_3 { width: 15%; }

    /* [영섭작업영역시작] */
    #contentForm { /* content_2_2 영역에 들어가는 전체폼 영역(contentForm) 스타일 */
        width: 45%;         
        height: 100%; 
        margin: auto;        
        border: 1px solid skyblue;          
        border-radius: 45px;            
        padding: 20px 40px 20px 40px;
    }

    #contentForm>div { width: 100%; } /* 전체폼 영역을 텍스트 영역(text), 결과 영역(form)으로 나눔 */
    #text { height: 35%; /* border: 1px solid green; */ }
    #form {
        height: 65%;
        float: left;
        line-height: 30px;
        padding-top: 15px; /* border: 1px solid blue; */ 
    }
    
    /* 텍스트 영역을 제목 영역(text_1), 설명 영역(text_2)으로 나눔 */
    #text>div { width: 100%; }
    #text_1 { height: 100%; }

    /* 제목 영역과 설명 영역 p 태그 스타일 */
    #text_1>p { height: 100%; font-size: 30px; font-weight: bold; }
    
    /* 결과 영역을 아이디찾기결과 영역(text_3), 버튼 영역(button)으로 나눔 */
    #form>div { width: 100%; }
    #text_2 { height: 50%; /* border: 1px solid orange; */}
    #button { height: 50%; }

    /* 아이디찾기결과 영역 p 태그 스타일 */
    #text_2>p { font-weight: bold; text-align: center; font-size: 18px; }

    /* 버튼 스타일 */
    button { width: 125px; display: block; }
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
                                <p>아이디 찾기</p>
                            </div>
                        </div>
                        <div id="form" align="center">
                            <div id="text_2">
                                <p>가입하지 않은 사용자입니다.</p>
                            </div>
                            <div>
                            	<!-- 회원가입 연결 서블릿은 동민이 만든 것 활용 -->
                                <button type="button" class="btn btn-primary" style="font-size: 15px;" onclick="location.href='<%= contextPath %>/joinForm.me'">회원가입하기</button>
                                <button type="button" class="btn btn-primary" style="font-size: 15px;" onclick="location.href='<%= contextPath %>'">홈으로</button>
                            </div>
                        </div>
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