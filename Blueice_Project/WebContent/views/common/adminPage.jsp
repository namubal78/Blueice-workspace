<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<style>
	
	div {
		border : 1px solid rgb(40, 78, 122);
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 80%;
        height: 900px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { height : 100%; }
    
    /* 컨테이너 영역 */
    #container {
        width: 85%;
        float: left;
        background-color: #f3f5f9;
     }

    #container>div { width: 100%; float:left;}
    #header { height: 15%; }
    #content { height: 85%; }

    /* 헤더 영역 */
    #header>div { 
        height: 100%;
        float: left;
    }

    #header_1 { width: 30%; }
    #header_2 { width: 55%; }
    #header_3 { width: 15%;}

    #header_1>p {
        width: 100%;
        height: 100%; 
        box-sizing: border-box;
        color: #163A66;
        font-weight: bolder;
        font-size: 23px;
        margin-top: 45px;
        margin-left: 40px; 
    }

    #header_3>p {
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        margin-top: 50px;
        text-align: center;
    }

    #header_3 a {
        text-decoration: none;
        font-size: 17px;
        color: #163A66;
    }
    

    /* 폰트 */
    body { font-family: 'Noto Sans KR', sans-serif !important; }

</style>

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

</head>
<body>
	<div class="wrap">
        <%@ include file="admin_menubar.jsp" %>
        <div id="container">
            <div id="header">
                <div id="header_1">
                    <p>관리자 페이지</p>
                </div>
                <div id="header_2"></div>
                <div id="header_3">
                    <p>
                        <a href="#">로그아웃</a>
                    </p>
                </div>
            </div>
            <div id="content">
                
            </div>
        </div>

    </div>

</body>
</html>