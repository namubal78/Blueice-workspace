<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조직도</title>
<script src="https://kit.fontawesome.com/5f017ff87e.js" crossorigin="anonymous"></script>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1680px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1150px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 20%; float: left; }
    #content_2_2 { height: 60%; float: left; }
    #content_2_3 { height: 20%; float: left; }

    #header { 
        height: 230px;
        background: #163A66;
    }

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

	/* 본문 영역 스타일 */
    #content_2_2>div {
        width: 100%;
    }
    #top {
        height: 60%;
        display: block;
    }
    #bottom {
        height: 40%;
        display: block;
    }
    
    #top>div {
        height: 100%;
        width: 70%;
    }

    #bottom>div {
        height: 100%;
    }
    #left, #right {
        width: 20%;
        float: left;
    }
    #center {
        width: 60%;
        float: left;
    }
    #center>p {
        font-size: 20px;
        line-height: 40px;
    }
    
    /* 인물 소개 문구 스타일 */
    p {
    	color: black;
    	font-weight: bolder;
   	}
    h5 {
    	color: black;
    	font-weight: bolder;
   	}
   	
</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/z-index_menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>조직도</p>
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
                    <div class="top">
                        <!-- 이미지 슬라이드 -->
                        <div id="outer">                        
                            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                            crossorigin="anonymous"></script>
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                            crossorigin="anonymous"></script>
                            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                            crossorigin="anonymous"></script>
                            <script>
                            $('.carousel').carousel({
                            interval: 2000 //기본 5초
                            })
                            </script>
                            <div class="container"><p style="font-size: 45px; color: #163A66;" align="center">Blueice 임원진</p></div>
                            <div id="demo" class="carousel slide" data-ride="carousel">
                            
                                <div class="carousel-inner" align="center">
                                    <!-- 슬라이드 쇼 -->
                                    <div class="carousel-item active">
                                        <img class="d-block" 
                                        src="resources/images/information/lkm.png"
                                        alt="First slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>이경미</h5>
                                            <p>Blueice의 대표이자 사무총장입니다.</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block"
                                        src="resources/images/information/ksb.png"
                                        alt="Second slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>구샛별</h5>
                                            <p>Blueice의 프로그램 국장입니다.</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block"
                                        src="resources/images/information/sdm.png"
                                        alt="Third slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>신동민</h5>
                                            <p>Blueice의 모금 국장입니다.</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block"
                                        src="resources/images/information/jgw.png"
                                        alt="Third slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>주건우</h5>
                                            <p>Blueice 조직 지원 국장입니다.</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block"
                                        src="resources/images/information/hys.png"
                                        alt="Third slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>한영섭</h5>
                                            <p>Blueice의 지역 개발 국장입니다.</p>
                                        </div>
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block"
                                        src="resources/images/information/hhj.png"
                                        alt="Third slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>황혜진</h5>
                                            <p>Blueice의 대외 협력 국장입니다.</p>
                                        </div>
                                    </div>
                                    <!-- / 슬라이드 쇼 끝 -->
                                
                                    <!-- 왼쪽 오른쪽 화살표 버튼 -->
                                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span style="color:#163A66">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#demo" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span style="color:#163A66">Next</span>
                                    </a>
                                    <!-- / 화살표 버튼 끝 -->
                                    
                                </div>
                            </div>
                        </div>    
                    </div>
                    <div id="bottom">
                        <div id="left"></div>
                        <div id="center">    
                            <p>
                                <br><br>
					                                임원진은 블루아이스 한국 지부 전체에 나아갈 방향을 제시하고 이끄는 역할을 합니다.<br>
					                                글로벌 블루아이스 공동체의 일부로서 글로벌 회의에 참석하고, 캠페인 활동 개발 및 방향 결정 과정에도 참여합니다.<br>
					                                사무총장과 프로그램 국장, 모금 국장, 조직 지원 및 지역 개발 국장으로 구성되어 있습니다.<br>
                            </p>
                        </div>
                        <div id="right"></div>
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