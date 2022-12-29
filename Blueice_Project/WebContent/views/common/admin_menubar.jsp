<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
    
    /* 네비게이터 메뉴바 영역 */
    #navigator { 
        width : 15%; 
        float:left; 
        background-color: #081d42;
    }

    #navigator>div { width: 100%; }

    #navigator_1 { height: 15%; }
    #navigator_2 { height: 85%; }

    /* 로고 */
    #logo {
        width: 100%;
        height: 100%;
        text-align: center;
        object-fit: contain;
    }

    #navi {
        /* border: 1px solid blue; */
        list-style-type: none;
        height: 90%;
        margin: 0px;
        padding: 0px;
        margin-top: 50px;
        overflow: hidden;
    }

    .navi_1 .submenu { display: none; }

    #navi>li {
        border-bottom: 1px solid rgb(195, 198, 204);
        border-top: 1px solid rgb(195, 198, 204);
        width: 100%;
        padding: 3px;
        text-align: center;
        font-size: 20px;
        line-height: 50px;
    }

    #navi>li:hover { background: #1a6292; }

    #navi a {
        text-decoration: none;
        width: 100%;
        height: 100%;
        color: #fff;
        display: block;
    }

    #navi a .fa-solid { width: 25px; }

    #navi>li:hover a { color: #fff; }

    #navi>li>ul {
        list-style-type: none;
        padding: 0px;
        display: none;
        overflow: auto;
    }

    #navi>li>a:hover+ul { display: block; }
    #navi>li>ul:hover { display: block; }

    #navi>li a:hover { background-color: #7eacca; }

    /* 폰트 */
    body { font-family: 'Noto Sans KR', sans-serif !important; }

</style>

    <!-- 
        * 부트스트랩
        웹 사이트를 쉽게 꾸밀 수 있게 도와주는 HTML, CSS, JS 프레임워크 (규칙성이 강한 라이브러리)
    -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- bootstrap-select 라이브러리 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/i18n/defaults-*.min.js"></script>

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    
</head>
<body>
        <div id="navigator">
            <div id="navigator_1">
                <a href=""><img src="../../resources/images/logo/logo6_1.png" id="logo"></a>
            </div>
            <div id="navigator_2">
                <ul id="navi">
                    <li class="navi_1">
                        <a href="#"><i class="fa-solid fa-hand-holding-dollar"></i>&nbsp;후원관리</a>
                        <ul class="submenu">
                            <li><a href="#">정기후원</a></li>
                            <li><a href="#">일시후원</a></li>
                            <li><a href="#">단체후원</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa-solid fa-table"></i>&nbsp;게시판관리</a>
                        <ul>
                            <li><a href="#">뉴스</a></li>
                            <li><a href="#">챌린지</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa-solid fa-user"></i>&nbsp;회원관리</a>
                        <ul>
                            <li><a href="<%= contextPath %>/memberListAll.me?currentPage=1">개인회원</a></li>
                            <li><a href="<%= contextPath %>/groupMemberListAll.me?currentPage=1">단체회원</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa-solid fa-circle-exclamation"></i>&nbsp;고객센터</a>
                        <ul>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">1:1 문의</a></li>
                        </ul>
                    </li>    
                </ul> 
            </div>
        </div>
</body>
</html>