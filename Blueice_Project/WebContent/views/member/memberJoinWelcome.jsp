<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://kit.fontawesome.com/6ff74efdc8.js" crossorigin="anonymous"></script>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border: 1px solid rgb(40, 78, 122); */
        box-sizing: border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1780px;
        margin: auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width: 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1250px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 15%; float: left; }
    #content_2_2 { height: 70%; float: left; }
    #content_2_3 { height: 15%; float: left; }

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


    /* [영섭작업영역시작] */
    #form { /* content_2_2 영역에 들어가는 전체폼 영역(form) 스타일 */
        width: 60%;
        height: 100%; 
        margin: auto;
        border: 2px solid skyblue;
        border-radius: 45px;
    }
    
    /* 전체폼 영역을
        아이콘(form_1),
        텍스트1(form_2),
        텍스트2(form_3),
        후원(form_4),
        챌린지(form_5)
        영역으로 나누고 스타일
    */
    #form_1 { height: 45%; margin: auto; padding: 10px 10px ;}
    #form_2, #form_3 { text-align: center; }
    
    /* 아이콘 영역(form_1) 로고 스타일 */
    #logo_1 {
        height: 100%;
        margin: auto;
        display: block;
    }

    /* 텍스트1 영역(form_2) 스타일 */
    #form_2 {
        height: 10%;
        /* border: 1px solid red; */
        font-size: 50px;
        font-weight: bold;
        color: #296aba;
        background: linear-gradient(to right top, #1f5a9e, #b2ddee);
        color: transparent;
        -webkit-background-clip: text;
    }
    
    /* 텍스트2 영역(form_3) 스타일 */
    #form_3 {
        height: 5%;
        /* order: 1px solid red; */
        font-size: 20px;
    }
    
    /* 후원 영역(form_4)를 후원아이콘 영역(form_4_1), 후원문구 영역(form_4_2)으로 나눔 */
    #form_4 { width: 100%; height: 15%; /* border: 1px solid red; */ }

    #form_4_1 {
        width: 20%;
        height: 100%;
        /* border: 1px solid green; */
        float: left;
        text-align: center;
    }

    #form_4_2 {
        width: 80%;
        height: 100%;
        /* border: 1px solid green; */
        display: block;
        float: left;
        align-items: center;
    }

    /* 챌린지 영역(form_5)를 챌린지문구 영역(form_5_1), 챌린지아이콘 영역(form_5_2)으로 나눔 */
    #form_5 { width: 100%; height: 15%; /* border: 1px solid red; */ }

    #form_4, #form_5 { padding: 5px 20px; }

    #form_5_1 {
        width: 80%;
        height: 100%;
        /* border: 1px solid green; */
        float: left;
        align-items: center;
    }

    #form_5_2 {
        width: 20%;
        height: 100%;
        /* border: 1px solid green; */
        display: block;
        float: left;
        text-align: center;
    }

    /* 후원, 챌린지 영역 아이콘 스타일 */
    #logo_2, #logo_3 {  max-width: 100%; max-height: 100%; }
    
    /* 후원과 챌린지 영역 문구 스타일 */
    #form_4_2>p, #form_5_1>p {
        width: 100%;
        height: 100%;
        font-size: 18px;
        margin-top: 50px;
    }

    #form_5_1 { text-align: right; }
    /* [영섭작업영역끝] */
    

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
                    
                    <!-- [영섭작업영역시작] -->
                    <div id="form">
                        <div id="form_1">
                            <img src="<%= contextPath %>/resources/images/logo/logo5_1.png" id="logo_1"> <!-- form 영역 최상단 로고 -->
                        </div>
                        <div id="form_2">
                            <p>Welcome to Blue Ice!</p>
                        </div>
                        <div id="form_3">
                            <p>환경을 위한 발걸음에 함께 해주셔서 감사합니다!</p>
                        </div>
                        <div id="form_4">
                            <hr>
                            <div id="form_4_1">
                                <a href="introDona.do"><img src="<%= contextPath %>/resources/images/logo_don2.png" id="logo_2"></a><!-- 후원 영역 아이콘 로고 -->
                            </div>
                            <div id="form_4_2">
                                <p>&nbsp;&nbsp;후원으로 북극곰에게 빙하 다리 놓아주기</p>
                            </div>
                        </div>
                        <div id="form_5">
                            <div id="form_5_1">
                                <p>북극곰을 위한 일상에서의 작은 챌린지!&nbsp;&nbsp;</p>
                            </div>
                            <div id="form_5_2">
                                <a href="list.challenge"><img src="<%= contextPath %>/resources/images/logo_chal.png" id="logo_3"></a><!-- 챌린지 영역 아이콘 로고 -->
                            </div>
                        </div>
                        <!-- [영섭작업영역끝] -->

                    </div>
                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>