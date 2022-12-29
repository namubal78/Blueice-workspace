<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.member.model.vo.*"%> <!-- member 테이블 import -->

<%
    // session에 담긴 login사용자 정보
    Member loginMember = (Member)session.getAttribute("loginMember");

    // 메인페이지 경로
    String contextPath = request.getContextPath();

    // alertMsg 정보 가져오기
    String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원하기</title>
<style>
	
    
    div {
        /* border : 1px solid rgb(40, 78, 122);*/
        box-sizing : border-box;
    }


    /* donation_menubar 영역*/
    #navigator {
        height: 150px;
        position: sticky; /* 상단 메뉴바 스크롤 시 고정 */
        top: 0;
        width: 100%;
		background-color: white;
		z-index: 1;
    }

    #navigator>div {
        height : 100%;
        float : left;
    }

    #navigator_1 { 
        width : 20%;
		float : left;
    }

    /* 로고 */
    #logo {
        width: 125px;
        height: 125px;
        text-align: center;
        object-fit: contain;
        margin-left: 150px;
        margin-top: 15px;
    }
    
    /* 메뉴바 */
    #navigator_2 { 
        width: 80%;
    }

    /* 폰트 */
    body { font-family: 'Noto Sans KR', sans-serif !important; }

    /* 모달창 */
    .modalContainer {
        display: block;
        position: fixed;
        top: 0px;
        left: 0px;
        width: 100%;
        height: 100%;
        z-index: 100;
        overflow-y: auto;
    }

    .modalDialog {
        position: relative;
        max-width: 400px;
        margin: 1.75rem auto;
        background-color: rgb(255, 255, 255);
        z-index: 1;
    }

    .popupBody {
        text-align: center;
        padding: 32px;
        font-size: 16px;
        font-weight: normal;
        font-stretch: normal;
        font-style: normal;
        line-height: 2;
        letter-spacing: -0.8px;
    }

    .popupFooter {
        display: flex;
        -webkit-box-pack: center;
        justify-content: center;
        padding-bottom: 32px;
    }

    .cancelDona {
        box-sizing: border-box;
        font-weight: bold;
        border-width: 1px;
        border-style: solid;
        position: relative;
        overflow: hidden;
        padding: 10px 20px;
        font-size: 16px;
        color: #3970B3;
        border-color: #3970B3;
    }

    .keepGoing {
        box-sizing: border-box;
        font-weight: bold;
        border-width: 1px;
        border-style: solid;
        position: relative;
        overflow: hidden;
        padding: 10px 20px;
        font-size: 16px;
        color: rgb(255, 255, 255);
        background-color: #3970B3;
        border-color: #3970B3;
    }



</style>
<!-- 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- 부트페이 CDN -->
<script src="https://js.bootpay.co.kr/bootpay-4.2.5.min.js" type="application/javascript"></script>
</head>

<body>
    
	<div id="navigator">
		<div id="navigator_1">
			<a href="<%= contextPath %>" data-toggle="modal" data-target="#please"><img src="<%= contextPath %>/resources/images/logo3_1.png" id="logo"></a>
		</div>

        <!-- 
            뒤로가기 누르면 모달창 떠서 질척거리며 잡도록 동적 구현!
            유니세프 질척: 조금만 더 입력하시면 후원자님의 소중한 나눔이 북극곰과 우리 모두에게 전달될 수 있습니다.
         -->
	</div>
</body>
</html>