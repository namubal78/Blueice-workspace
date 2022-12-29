<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.member.model.vo.*"%>

<%
	//session에 담긴 login사용자 정보
	Member loginMember = (Member)session.getAttribute("loginMember");
	
	//메인페이지 경로
	String contextPath = request.getContextPath();
	
	// alertMsg 정보 가져오기
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	// 메뉴바 쿠키 저장
	Cookie[] cookies = request.getCookies();
	
	String saveId = "";
	if(cookies != null) {
	
		for(int i = 0; i < cookies.length; i++) {
			
			if(cookies[i].getName().equals("saveId")) {
				saveId = cookies[i].getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>

    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    #navigator {
        position: fixed; /* 상단 메뉴바 스크롤 시 고정 */
        top: 0;
        width: 98%;
        height: 150px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
        background-color: white;
        z-index: 500;
    }

    #navigator_1 { 
        width: 20%;
        height: 100%; 
        float: left; 
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
        width: 50%;
        height: 30%; 
        float: left;
        margin-top: 50px; 
    }

    /* 메인 메뉴를 담당하는 navi 에 대한 스타일 */
    #navi {
        /* border: 1px solid blue; */
        list-style-type: none;
        margin: 0px;
        padding: 0px;
        height: 100%;
    }

    /* 메인 메뉴 항목을 담당하는 li 요소들에 대한 스타일 */
    #navi>li {
        /* border: 1px solid blue; */
        float: left;
        width: 20%;
        height: 100%;
        text-align: center;
        margin-left: 25px;
    }

    /* 메뉴바에 있는 모든 a 태그들을 일괄 선택하여 스타일 부여 */
    #navi a {
        /* border: 1px solid green; */
        text-decoration: none;
        color: #163A66;
        font-size: 20px;
        font-weight: 900;
        width: 100%;
        height: 100%;
        display: block;
        line-height: 50px; /* 장평조절 속성 */
    }

    /* 메뉴에 마우스가 올라갔을 때 추가적인 효과 */
    #navi a:hover { color: #bcbcbc; }

    /* 서브메뉴를 담당하는 ul 태그에 대한 스타일 */
    #navi>li>ul {
        list-style-type: none;
        padding: 0px;
        display: none; /* 안보였다가 마우스가 보이는 순간만 보여야 함 */
    }

    /* 메인메뉴에 마우스가 올라가는 순간 서브메뉴 펼쳐져서 보이게 */
    #navi>li>a:hover+ul { display: block; background-color: #d1d8e1;}

    /* 서브메뉴에도 마우스가 올라갈 때 목록이 펼쳐진 것이 유지 */
    #navi>li>ul:hover { display: block; background-color: #b7c2d0;}

    /* 서브메뉴 글씨 */
    #navi>li>ul a { font-size: 15px; cursor: pointer; }

    
    /* 로그인, 회원가입 버튼 */
    #navigator_3 {
        width: 15%;
        height: 100%; 
        float: left;
        position: relative; 
    }

    #navigator_3>p {
        width: 100%;
        height: 100%;
        text-align: center;
        color: #163A66;
        box-sizing: border-box;
        word-spacing: 5px
    }

    #navigator_3 a {
        text-decoration: none;
        font-size: 17px;
        color: #163A66;
        line-height: 150px;
    }

    #navigator_3 a:hover { color: #bcbcbc; }        


    /* 상단 후원하기 버튼 */
    #navigator_4 { 
        width: 15%;
        height: 100%; 
        float: left;
    }

    .donation {
        color: white;
        background-color: #163A66;
        border: none;
        border-radius: 15px;
        text-align: center;
        font-size: medium;
        cursor: pointer;
        width: 130px;
        height: 45px;
        float: left;
        margin-left: 50px;
        margin-top: 55px;
    }
    
    .donation:hover { background-color: #5174a0;}

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
	<script>
			
		//메인페이지 alertMsg 호출
		var msg = "<%= alertMsg %>"; 
		
		if(msg != "null") {
			alert(msg);
			<% session.removeAttribute("alertMsg"); %>
		}
		
	</script>
	
    <div id="navigator">
        <div id="navigator_1">
            <a href="<%= contextPath %>"><img src="<%= contextPath %>/resources/images/logo/logo3_1.png" id="logo" data-toggle="modal" data-target="#please"></a>
        </div>
        <div id="navigator_2">
            <ul id="navi">
                <li>
                    <a href="list.mi">소개</a>
                    <ul>
                        <li><a href="list.mi">이념과 미션</a></li>
                        <li><a href="list.bu">사업</a></li>
                        <li><a href="peopleLink.in">조직도</a></li>
                        <li><a href="locationLink.in">오시는 길</a></li>
                    </ul>
                </li>
                <li>
                    <a href="introDona.do">후원</a>
                </li>
                <li>
                    <a href="list.challenge">이벤트</a>
                    <ul>
                        <li><a href="list.news?currentPage=1">뉴스</a></li>
                        <li><a href="list.challenge">챌린지</a></li>
                        <li><a href="list.re?currentPage=1">리워드</a></li>
                    </ul>
                </li>
                <li>
                    <a href="faqSelect.in">고객센터</a>
                    <ul>
                        <li><a href="list.no?currentPage=1">공지사항</a></li>
                        <li><a href="faqSelect.in">FAQ</a></li>
                        <li><a href="inquiryInsertForm.in">1:1 문의하기</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div id="navigator_3">
           	<!-- 로그인 전 화면 -->
           	<% if(loginMember == null) { %>
            <p>
                <a href="<%= contextPath %>/LoginForm.me">로그인</a> |
                <a href="<%= contextPath %>/joinForm.me">회원가입</a>
            </p>
            <% } else { %>
            <!-- 로그인 후 화면 -->
            <p>
                <a href="<%= contextPath %>/logout.me">로그아웃</a> |
                <a href="myMenu.me">마이페이지</a>
            </p>
            <% } %>
        </div>
        <div id="navigator_4">
            <% if(loginMember != null) { %> 
                <button class="donation" onclick="toDona();">후원하기</button>
            <% } else { %> 
                <button class="donation" onclick="toLogin();">후원하기</button>
            <% } %>
            <script>
                function toDona() {
                    location.href = "enrollInfo.do";
                }
                function toLogin() {
                    location.href = "LoginForm.me";
                }
            </script>
        </div>
    </div>

</body>
</html>