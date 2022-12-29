<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.member.model.vo.*"%> <!-- member 테이블 import -->
<%
	// session에 담긴 login사용자 정보
	Member loginMember = (Member)session.getAttribute("loginMember");

	// 메인페이지 경로
	String contextPath = request.getContextPath();

	// alertMsg 정보 가져오기
	String alertMsg = (String)session.getAttribute("alertMsg");

	// 쿠키 정보 불러오기(미완성)
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blue Ice</title>
<style>
	
    div {
        /* border : 1px solid rgb(165, 208, 228); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 3400px;
        margin : auto;
    }

    /* 전체 3가지 영역의 세로길이 */
    .wrap>div { width : 100%; }

    #hena { /* 배경이미지 삽입 */
        height : 840px;
        background-image: url("resources/images/polarbear7_1.png");
        background-size: cover;
    }

    #content { height : 2410px; }
    #footer { height: 150px; }

    /* hena 영역 */
    #navigator { height: 15%; }

    #navigator>div { height: 100%; float: left;}
    #navigator_1 { width: 20%; }

    /* 로고 */
    #logo {
        width: 125px;
        height: 125px;
        text-align: center;
        object-fit: contain;
        margin-left: 110px;
        margin-top: 15px;
    }

    /* 상단 메뉴바 */
    #navigator_2 {
        width: 60%;
        height: 30%; 
        float: left;
    }

    /* 메인 메뉴를 담당하는 navi 에 대한 스타일 */
    #navi {
        /* border: 1px solid blue; */
        list-style-type: none;
        margin: 0px; /* ul 요소도 기본적으로 위, 아래에 magin 이 존재 */
        padding: 0px; /* ul 요소는 기본적으로 padding 도 갖고 있음 */
        height: 100%;
    }

    /* 메인 메뉴 항목을 담당하는 li 요소들에 대한 스타일 */
    #navi>li {
        /* border: 1px solid blue; */
        float: left;
        width: 20%;
        height: 100%;
        text-align: center;
        margin-left: 35px;
        line-height: 100px;
    }

    #navi>li>a {
        text-decoration: none;
        color: white;
        font-size: 20px;
        font-weight: 900;
        width: 100%;
        height: 100%;
        display: block; 
        line-height: 160px;
    }

    #navi>li>ul>li>a {
        display: block;
        color: #163A66;
        height: 70px;
        width: 100%;
        text-decoration: none;
        font-size: 15px;
        font-weight: 900;
        line-height: 70px;
    }

    #navi>li>ul>li:hover { background-color: #9db1ca; }

    /* 메뉴에 마우스가 올라갔을 때 추가적인 효과 */
    #navi a:hover { color: #163A66; }

    /* 서브메뉴를 담당하는 ul 태그에 대한 스타일 */
    #navi>li>ul { list-style-type: none; padding: 0px; display: none; /* 안보였다가 마우스가 보이는 순간만 보여야 함 */ }

    /* 메인메뉴에 마우스가 올라가는 순간 서브메뉴 펼쳐져서 보이게 */
    #navi>li>a:hover+ul { display: block; background-color: rgba(237, 235, 235, 0.3); }

    /* 서브메뉴에도 마우스가 올라갈 때 목록이 펼쳐진 것이 유지 */
    #navi>li>ul:hover { display: block; background-color: rgba(237, 235, 235, 0.3); }


    /* 로그인 */
    #navigator_3 { width: 20%; position: relative; }

    #navigator_3>div { 
        width: 100%; 
        height: 100%;
        vertical-align: middle; 
        margin: 0px;
        padding: 0px;
    }

    #login>ul { list-style-type: none; height: 100%; }

    #login>ul>li { float: left; text-align: center; height: 100%; width: 30%;  }

    #login>ul>li>a {
        text-decoration: none;
        color: white;
        font-size: 17px;
        width: 100%;
        height: 100%;
        display: block; 
        line-height: 160px;
    }

    #login>ul>li>a:hover { color: #163A66; }

    #login>ul>li>ul { list-style-type: none; padding: 0px; display: none; }

    #login>ul>li>ul a { 
        color: #163A66;
        height: 40px;
        width: 100%;
        display: block; 
        text-decoration: none; 
        font-size: 15px; 
        line-height: 40px;
    }

    #login>ul>li>ul>li:hover { background-color: #9db1ca; }

    #login>ul>li>a:hover+ul { display: block; background-color: rgba(237, 235, 235, 0.3); }

    #login>ul>li>ul:hover { display: block; background-color: rgba(237, 235, 235, 0.3); }


	#top { width: 150px; height: 150px; }
    #top img { height: 100%; width: 100%; margin: auto; display: block; object-fit: contain; }
    #topp { width: 100%; height: 85%; float: left; }
    #topt { width: 100%; height: 15%; float: left; }
    #top>div p {
        height: 100%;
        width: 100%;
        font-size: 20px;
        font-weight: 500;
        color: #163A66;
        padding: 0px;
        box-sizing: border-box;
        text-align: center;
        margin-top: -23px;
    }



    /* 배너 문구, 후원버튼 */
    #header { height: 85%; }

	#header>p {
        width: 30%;
        height: 20%;
        text-align: center;
        box-sizing: border-box;
        font-size: 50px;
        font-weight: 500;
        font-style: italic;
        color: rgb(196, 196, 196);
        margin-top: 190px;
        margin-left: 1100px;
        font-family: 'Ubuntu', sans-serif;
    }

    #dona {
        width: 100%;
        height: 50%;
        float: right;
        position: relative;
    }

	.donation {
        color: white;
        background-color: #629AD6;
        border: none;
        border-radius: 15px;
        text-align: center;
        font-size: medium;
        cursor: pointer;
        width: 130px;
        height: 45px;
        float: right;
        margin-right: 240px;
        margin-top: -76px;
    }

    /* content 영역 */
    #content>div { height: 100%; float: left; }
    #content_1 { width: 10%; }
    #content_2 { width: 80%; }

    #content_2_1 { width: 100%; height: 36%; float: left; }

    #blank1, #blank2, #blank3 { width: 100%; height: 8%; float: left; }

    #blank1>p {
        width: 100%;
        height: 100%;
        margin: 0px;   
        text-align: center;
        box-sizing: border-box;
        font-size: 30px;
        font-weight: 900;
        margin-top: 125px;
    }


    #blank { width: 100%; height: 30%;}


    #calendar { width: 60%; height: 70%; float: left; padding: 40px 30px; }

    #demo { width: 100%; height: 100%; }
    .carousel-inner { width: 100%; height: 100%; }


    .carousel-inner>div { width: 100%; height: 100%; overflow: hidden; }

    .carousel-inner a>img { width: 100%; height: 100%; object-fit: cover; }

    .carousel-inner p {
        font-size: 30px;
        font-weight: 600;
        background-color: rgba(237, 235, 235, 0.7);
        margin-top: -75px;
        position: relative;
    }

    

    #dona_graph {
        width: 40%;
        height: 70%;
        float: left;
        padding: 40px 30px;
    }

    #dona_graph>img { 
        height: 100%; 
        width: 100%; 
        margin: auto; 
        display: block; 
        object-fit: contain;
    }

    #dona_graph>p {
        width: 400px;
        height: 110px;
        position: absolute;
        margin-left: 70px;
        margin-top: -140px;
        font-size: 40px;
        font-weight: 700;
        font-family: 'Rubik', sans-serif;
        background: linear-gradient(to right, #f4dd84, rgba(255, 255, 255, 0.8));
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    #content_2_2 { 
        width: 100%; 
        height: 25%; 
        float: left;
        border: solid skyblue;
        border-radius: 45px; 
        padding: 30px 30px;
    }

    #content_2_2 a>img { height: 100%; width: 100%; margin: auto; display: block; object-fit: contain; }

    #content_2_2 a>img:hover { opacity: 0.7;}

    #content_2_2>div { width: 33.3%; height: 100%; float: left; }
    
    #imggg_1, #imggg_2, #imggg_3 { width: 100%; height: 80%; float: left; }
    #texttt_1, #texttt_2, #texttt_3 {width: 100%; height: 20%; float: left; }

    #texttt_1 a, #texttt_2 a, #texttt_3 a {
        text-decoration: none;
        color: black;
        font-size: 25px;
        font-weight: 400;
        line-height: 80px;
    }



    #content_2_3 { width: 100%; height: 15%; float: left; }

    #content_2_3_1 { width: 100%; height: 20%; float: left; }
    #content_2_3_2 { width: 100%; height: 80%; float: left; }

    #content_2_3_1>p {
        width: 100%;
        height: 100%;
        margin: 0px;   
        text-align: center;
        box-sizing: border-box;
        font-size: 30px;
        font-weight: 900;
    }

    #imgg {
        width: 100%;
        height: 70%;
        float: left;
    }

    #imgg>div {
        width: 20%;
        height: 100%;
        float: left;
        padding: 20px;
    }

    #imgg img {
        height: 90%;
        margin: auto;
        margin-top: 10px;
        display: block;
    }

    #textt {
        width: 100%;
        height: 30%;
        float: left;
    }

    #textt>div {
        width: 20%;
        height: 100%;
        float: left;
    }

    #textt p {
        width: 100%;
        height: 100%;
        margin: 0px;
        margin-top: 15px;
        text-align: center;
        box-sizing: border-box;
        font-size: 20px;
        font-family: 'Noto Sans KR', sans-serif;
    }


    #content_3 { width : 10%; }

    /* footer 영역 */
    #footer { width : 100%; background-color: #edebeb; }

    /* footer 자식의 개별적인 속성 : 세로길이 */
    #footer_1 { height: 20%; }
    #footer_2 { height: 80%; }

    /* footer_2 자식의 공통적인 속성 : p 태그들 */
    #footer_2>p {
        width: 100%;
        /* border: 1px solid red; */
        margin: 0px; /* p 태그에는 기본적으로 위 아래 마진이 있음 */
        box-sizing: border-box; /* p 태그도 마찬가지로 테두리를 포함한 width, height 속성을 적용시킬것 */
        font-size: 13px; /* 글자크기 줄이기 */
    }

    /* footer_2 자식의 개별적인 속성 : 세로길이 */
    #p1 { 
        height: 80%;
        padding: 5px 15px; /* padding 으로 상하, 좌우의 여백 넣어주기 */
    }
    #p2 { 
        height: 20%;
        text-align: center;
    }

    #footer_1>a {
        text-decoration: none;
        color: black;
        font-weight: 600;
        margin: 15px;
        vertical-align: middle; /* 수직 구조에서 가운데로 맞추는 속성 */
    }

    /* body 안 모든 곳에 폰트 적용 */
    body { font-family: 'Noto Sans KR', sans-serif; }

</style>
    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik:ital@0;1&display=swap" rel="stylesheet">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">

</head>

<div id="top" style="position: fixed; bottom: 5px; right: 5px;">
    <div id="topp"><a href="#"><img src="<%= contextPath %>/resources/images/logo/logo2.png"></a></div>
    <div id="topt"><p>TOP</p></div>
</div>
<body>

<script>
	
	// 메인페이지 alertMsg 호출
	var msg = "<%= alertMsg %>"; 
	
	if(msg != "null") {
		alert(msg);
		<% session.removeAttribute("alertMsg"); %>
	}

</script>
	
	<div class="wrap">
        <div id="hena">
            <div id="navigator">
                <div id="navigator_1">
                    <a href=""><img src="resources/images/logo/logo8_1.png" id=logo></a>
                </div>
                <div id="navigator_2">
                    <ul id="navi">
                        <li>
                            <a>소개</a>
                            <ul>
                                <li><a href="list.mi">이념과 미션</a></li>
                                <li><a href="list.bu">사업</a></li>
                                <li><a href="peopleLink.in">조직도</a></li>
                                <li><a href="locationLink.in">오시는 길</a></li>
                            </ul>
                        </li>
                        <li>
                            <a>후원</a>
                            <ul>
                                <li><a href="introDona.do">후원하기</a></li>
                            </ul>
                        </li>
                        <li>
                            <a>이벤트</a>
                            <ul>
                                <li><a href="list.news?currentPage=1">뉴스</a></li>
                                <li><a href="list.challenge">챌린지</a></li>
                                <li><a href="list.re?currentPage=1">리워드</a></li>
                            </ul>
                        </li>
                        <li>
                            <a>고객센터</a>
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
                    <div id="login">
                        <ul>
                            <li>
                                <a href="LoginForm.me">로그인</a>
                            </li>
                            <li>
                                <a href="joinForm.me">회원가입</a>
                            </li>
                            <li>
                                <a>영수증</a>
                                <ul>
                                    <li><a href="LoginForm.me">일시 후원</a></li>
                                    <li><a href="LoginForm.me">정기 후원</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <% } else { %>
                    <!-- 로그인 후 화면 -->
           	        <div id="login">
                        <ul>
                            <li>
                                <a href="logout.me">로그아웃</a>
                            </li>
                            <li>
                                <a href="myMenu.me">마이페이지</a>
                            </li>
                            <li>
                                <a>영수증</a>
                                <ul>
                                    <li><a href="temList.do?currentPage=1">일시 후원</a></li>
                                    <li><a href="regList.do?currentPage=1">정기 후원</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <% } %>
                    
                </div>
            </div>
            <div id="header">
                <p>"SAVE OUR PLANET"</p>
                <div id="dona">
                    <% if(loginMember != null) { %> 
                        <button class="donation" onclick="toDona();">후원하기</button>
                    <% } else { %> 
                        <button class="donation" onclick="toLogin();">후원하기</button>
                    <% } %>
                </div>
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
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1">
                    <div id="blank"></div>
                    <div id="calendar">

                        <div id="demo" class="carousel slide" data-ride="carousel">

                            <!-- Indicators -->
                            <ul class="carousel-indicators">
                              <li data-target="#demo" data-slide-to="0" class="active"></li>
                              <li data-target="#demo" data-slide-to="1"></li>
                              <li data-target="#demo" data-slide-to="2"></li>
                            </ul>
                          
                            <!-- The slideshow -->
                            <div class="carousel-inner" align="center">
                              <div class="carousel-item active">
                                <a href="page.news?nno=16"><img src="<%= contextPath %>/resources/images/news/뉴스 10번.jpg"></a>
                                <p>그린란드 '녹을 운명' 좀비빙하 110조t…해수면 27㎝ 상승</p>
                              </div>
                              <div class="carousel-item">
                                <a href="page.news?nno=14"><img src="<%= contextPath %>/resources/images/news/뉴스 8번.jpg"></a>
                                <p>3300m 알프스 빙하 무너졌다…‘10도’ 역대 최고기온 하루 만에..</p>
                              </div>
                              <div class="carousel-item">
                                <a href="page.news?nno=15"><img src="<%= contextPath %>/resources/images/news/뉴스 9번.jpg"></a>
                                <p>유럽 폭염 이유 있다…30년간 세계평균 갑절로 기온 올라</p>
                              </div>
                            </div>
                          
                            <!-- Left and right controls -->
                            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                              <span class="carousel-control-prev-icon"></span>
                            </a>
                            <a class="carousel-control-next" href="#demo" data-slide="next">
                              <span class="carousel-control-next-icon"></span>
                            </a>
                        </div>
                    </div>
                    <div id="dona_graph">
                        <img src="<%= contextPath %>/resources/images/polar4.jpg">
                        <p>SAVE ICE<br>SAVE POLAR BEAR</p>
                    </div>
                </div>
                <div id="blank1">
                    <p>현재 진행중인 챌린지에 함께 참여해보세요!</p>
                </div>
                <div id="content_2_2">
                    <div id="challenge_1">
                        <div id="imggg_1"><a href="comment.challenge?cno=5&currentPage=1"><img src="resources/images/challenge_3.png"></a></div>
                        <div id="texttt_1">
                            <p align="center"><a href="comment.challenge?cno=5&currentPage=1" style="color:#124275;">용기내 챌린지 - 텀블러, 다회용 용기 사용</a></p>
                        </div>
                    </div>
                    <div id="challenge_2">
                        <div id="imggg_2"><a href="comment.challenge?cno=3&currentPage=1"><img src="resources/images/challenge_2.png"></a></div>
                        <div id="texttt_2">
                            <p align="center"><a href="comment.challenge?cno=3&currentPage=1" style="color:#838b94;">쓰레기 분리배출 챌린지</a></p>
                        </div>
                    </div>
                    <div id="challenge_3">
                        <div id="imggg_3"><a href="comment.challenge?cno=4&currentPage=1"><img src="resources/images/challenge_1.png"></a></div>
                        <div id="texttt_3">
                            <p align="center"><a href="comment.challenge?cno=4&currentPage=1" style="color:#838b94;">대중 교통 이용 챌린지</a></p>
                        </div>
                    </div>
                </div>
                <div id="blank2"></div>
                <div id="content_2_3">
                    <div id="content_2_3_1">
                        <p>후원금은 어떻게 사용되나요?</p>
                    </div>
                    <div id="content_2_3_2">
                        <div id="imgg">
                            <div id="imgg_1"><img src="resources/images/warming.gif"></div>
                            <div id="imgg_2"><img src="resources/images/whale.gif"></div>
                            <div id="imgg_3"><img src="resources/images/experiment.gif"></div>
                            <div id="imgg_4"><img src="resources/images/globe-earth.gif"></div>
                            <div id="imgg_5"><img src="resources/images/rise.gif"></div>
                        </div>
                        <div id="textt">
                            <div id="textt_1"><p>기후위기대응</p></div>
                            <div id="textt_2"><p>해양, 삼림 보호</p></div>
                            <div id="textt_3"><p>연구 및 과학 조사</p></div>
                            <div id="textt_4"><p>글로벌 캠페인</p></div>
                            <div id="textt_5"><p>미디어 활동</p></div>
                        </div>
                    </div>
                </div>
                <div id="blank3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <div id="footer">
            <div id="footer_1">
                <a href="locationLink.in">오시는 길</a> |
                <a href="list.no?currentPage=1">공지사항</a> |
                <a href="faqSelect.in">FAQ</a> |
                <a href="inquiryInsertForm.in">1:1문의하기</a>
            </div>
            <div id="footer_2">
                <p id="p1">
                    대표자 : 이경미<br>
                    사업자등록번호 : 202-07-25123<br>
                    대표전화 : 0725-1223<br>
                    팩스 : 02-725-1223<br>
                    [07212] 서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F
                </p>
                <p id="p2">
                    Copyright © Blue ice Korea All Right Reserved
                </p>
            </div>
        </div>
    </div>
</body>
</html>