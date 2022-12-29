<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
<style>
    
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 2800px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 2270px; }
    #content_2>div { width: 100%;}
    #content_2_1 { height: 10%; float: left;}
    #content_2_2 { height: 80%; float: left;}
    #content_2_3 { height: 10%; float: left;}

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
    #content_2 { width: 75%; }
    #content_3 { width: 10%; }


    /* 주건우님의 CSS 작성 시작 */
    #news-full{ /* 전체 요소를 담고 있는 div */
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
    }

    #news-title{ /* 제목을 담고 있는 div Id */
        width: 100%;
        height: 10%;
        box-sizing: border-box;
    }

    #news-title>div { width: 100%; float: left; }
    #news-title1 { height: 80%; }
    #news-title2 { height: 20%; }

    #news-img{ /* 이미지를 담고 있는 div Id */
        width: 100%;
        height: 30%;
        box-sizing: border-box;
    }

    #small-img{ /* #news-img 안의 이미지만 담을 수 있는 div */
        width: 70%;
        height: 100%;
        margin: auto;
        box-sizing: border-box;
        background-image: url(https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5260%2F2022%2F04%2F09%2F0000008651_001_20220523143801804.jpg&type=l340_165);
        width: 70%;
        height: 100%;
        background-size: cover;
    }

    #news-content{ /* 뉴스 기사를 담고 있는 div Id */
        width: 100%;
        height: 60%;
        box-sizing: border-box;
    }

    #news-content-sm{ /* #news-content안의 뉴스 기사만 담을 수 있는 div */
        width: 70%;
        height: 100%;
        box-sizing: border-box;
        margin: auto;
        padding-top: 100px;
        font-size: 20px;
        word-spacing: 7px;
        letter-spacing: 1px;
    }

    #news-title1>p{  /* 뉴스 제목 글씨와 관련된 CSS */
        width: 100%;
        height: 100%;
        font-size: 40px;
        text-align: center;
        line-height: 140px;
    }

    #news-title2>p {
        width: 100%;
        height: 100%;
        text-align: left;
        font-size: medium;
        margin-left: 10px;
    }


</style>
</head>
<body>

    <div class="wrap">
	    <%@ include file="../common/menubar.jsp" %>
		     <div id="navigator2"></div>
		     <div id="header">
		         <div id="header_1">
		             <p>공지사항</p>
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
		             
					 	    <div id="news-full">
						        <div id="news-title">
                                    <div id="news-title1"><p>공지사항 제목</p></div>
                                    <div id="news-title2"><p>작성일 : 2022-10-23&nbsp;&nbsp;|&nbsp;&nbsp;조회수 : 30</p></div>
						        </div>

                                <hr>
						        <div id="news-img">
						            <!-- 이미지 넣어버려! -->
						            <div id="small-img"></div>
						        </div>
						        <div id="news-content">
						            <div id="news-content-sm">
                                        <p>
                                            유소년에게서 원질이 심장은 무엇을 아름다우냐? 낙원을 방황하였으며, 그들은 운다. 가는 피고 주는 보배를 있는 방지하는 인류의 간에 아름다우냐? 끓는 끝에 구하지 우는 붙잡아 밝은 거친 운다. 있을 봄바람을 모래뿐일 천자만홍이 이 못할 실현에 예수는 그들은 그리하였는가? 든 우리 실로 운다. 우리 있을 두손을 트고, 돋고, 웅대한 천지는 황금시대다. 이상은 역사를 같은 때문이다. 않는 우리의 꽃이 가지에 곧 사랑의 전인 얼음과 인간의 이것이다. 가치를 예가 우리의 되려니와, 풍부하게 찾아다녀도, 그들은 보는 구하지 봄바람이다. 사는가 봄날의 노래하며 우리 현저하게 품에 피에 쓸쓸하랴?
                                            <br><br>
이상의 위하여서 바이며, 끝까지 충분히 끓는 보는 있는 위하여서. 청춘의 군영과 목숨을 행복스럽고 미인을 장식하는 그들을 이상의 고행을 보라. 너의 긴지라 같지 부패뿐이다. 내는 일월과 광야에서 이것이야말로 피가 있으며, 무한한 커다란 때에, 때문이다. 부패를 우리 그들의 이것이다. 풍부하게 장식하는 뛰노는 평화스러운 어디 품으며, 뭇 사막이다. 웅대한 오아이스도 가지에 않는 못할 뿐이다. 별과 실로 용기가 말이다. 붙잡아 피는 우리의 무엇을 얼마나 하여도 얼음 칼이다.
                                            <br><br>
피에 없으면, 고동을 때까지 사람은 살 그들은 그것을 같은 사막이다. 못할 인생에 긴지라 동산에는 풀이 풀이 설레는 듣는다. 우는 열매를 못할 품고 넣는 평화스러운 기쁘며, 찾아다녀도, 봄바람이다. 예가 인생을 찾아다녀도, 옷을 원대하고, 날카로우나 맺어, 속에 피다. 살 곳이 사람은 영원히 있다. 이상은 수 힘차게 대중을 이것이야말로 얼마나 힘있다. 안고, 이것이야말로 심장의 눈이 같이 앞이 못하다 용감하고 있는가? 얼음 인간은 붙잡아 피어나기 내는 청춘의 것이다. 길지 작고 청춘 남는 같은 시들어 위하여서. 인생의 있음으로써 이것을 약동하다. 위하여 청춘 이상의 생의 힘있다.
                                        </p>
                                    </div>
						        </div>
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







































