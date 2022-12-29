<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오시는 길</title>
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
    #outer {
        border-radius: 45px;
        border: solid rgb(41, 128, 185);
        padding: 20px;
        height: 72%;
    }

	/* 지도, 기타 영역 나눔 */
    #outer>div {
        height: 100%;
    }
    #left, #right {
        width: 50%;
        float: left;
    }
    
	/* 카카오지도 api 스타일 */
    #map {
        width: 100%;
        height: 100%;
        margin: auto;
    }
    
    /* 타이틀, 아이콘&문구 영역 나눔 */
    #right>div {
        width: 100%;
    }

	/* 타이틀 스타일 */
    #title { 
        margin-top: 30px;
        height: 15%;
        font-size: 45px;
        font-weight: bolder;
        margin-left: 10px;
    }
    
    /* 아이콘 스타일 */
    i{
        font-size: 45px;
        color: rgb(41, 128, 185);
        margin-left: 15px;
        width: 100%;
    }

	/* 문구 영역 스타일 */
    .info {
        height: 15%;
        padding: 5px;
    }
    .info>div {
        height: 100%;
        float:left; 
    }
    .infoLeft { width: 10%; }
    .infoRight { width: 90%; }
    .infoRight>p {
        font-size: 18px;
        color: black;
        line-height: 45px;
        margin-left: 10px;
    }
    hr {
        background: lightblue;
        height:1px;
        border:0;
    }

</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/z-index_menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>오시는 길</p>
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
                    <div id="outer">
                        <div id="left">
                            <br>
                            <div id="map" style="width:500px;height:400px;"></div> <!-- 카카오지도 api -->
                            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7309856caa67ba762792dc43fdff0fd8"></script>
                            <script>
                            	// 지도
                                var container = document.getElementById('map');
                                var options = {
                                    center: new kakao.maps.LatLng(37.5338, 126.897),
                                    level: 3
                                };
                        
                                var map = new kakao.maps.Map(container, options);
                                
                                // 지도 위 마커
                                var imageSrc = "resources/images/logo/logo1_1.png";
                                var imageSize = new kakao.maps.Size(60, 60);
                                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                                var marker = new kakao.maps.Marker({
                                    map: map, 
                                    position: new kakao.maps.LatLng(37.5338, 126.897),
                                    image : markerImage, 
                                });
                            </script>
                            
                        </div>
                        <div id="right">
                            <div id="title">
                                <p>Blueice</p>
                            </div>
                            <hr>
                            <div class="info">
                                <div class="infoLeft">
                                    <i class="fa-sharp fa-solid fa-location-dot" ></i>
                                </div>
                                <div class="infoRight">
                                    <p>서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F</p>
                                </div>
                            </div>
                            <hr>
                            <div class="info">
                                <div class="infoLeft">
                                    <i class="fa-solid fa-phone" style="font-size: 40px"></i>
                                </div>
                                <div class="infoRight">
                                    <p>0725-1223(평일 오전 9시 ~ 오후 6시)</p>
                                </div>
                            </div>
                            <hr>
                            <div class="info">
                                <div class="infoLeft">
                                    <i class="fa-solid fa-fax" style="font-size: 40px;"></i>
                                </div>
                                <div class="infoRight">
                                    <p>02-725-1223</p>
                                </div>
                            </div>
                            <hr>
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