<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이념과 미션</title>
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
        #content_2_1 { height: 33%; float: left; }
        #content_2_2 { height: 33%; float: left; }
        #content_2_3 { height: 33%; float: left; } /* content 영역 3등분 */
        
        /* 내용물영역입니다 */
        #content_2>div>div{height: 100%;}
        #content_2_1_1 {width: 15%; float: left;}
        #content_2_1_2 {width: 15%; float: left;}
        #content_2_1_3 {width: 70%; float: left;}

        #content_2_2_1 {width: 15%; float: left;}
        #content_2_2_2 {width: 70%; float: left;}
        #content_2_2_3 {width: 15%; float: left;}

        #content_2_3_1 {width: 15%; float: left;}
        #content_2_3_2 {width: 70%; float: left;}
        #content_2_3_3 {width: 15%; float: left;}

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
 

        /* 회색 동그라미 스타일 */
        .circle{border-radius:100%;
                background-color: rgb(224, 224, 224); 
                width: 135px;
                height: 135px;
                margin: auto;
                margin-top: 100px; }

        /* 동그라미 안 숫자 스타일 */
        .number{
            font-size: 40px;
            line-height: 1.42857143;
            color: rgb(52, 152, 219);;
            user-select: none;
            font-family: HanSans;
            box-sizing: content-box;
            padding-top: 37px;
            margin: auto;
        }


</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>이념과 미션</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1">
                    <div id="content_2_1_1">
                        <div class="circle" align="center"><p class="number">01</p></div>
                    </div>
                    <div id="content_2_1_2"><img src="<%= contextPath %>/resources/images/logo/logo8_1.png" width="100%" height="40%" style="margin-top: 100px;"></div>
                    <div id="content_2_1_3">
                        <p align="center" style="float: left; margin-top: 100px;" ><span style="font-weight: bold; color: rgb(41, 128, 185); font-size: x-large;">북극곰</span>은 먹이사슬의 최상위 포식자라는 것 알고 계셨나요?<br>
                            그렇기에 북극곰이 북극 생태계의 균형을 유지하는 데 중요한 역할을 합니다.<br>
                            북극곰의 위기는 곧 북극 생태계 전체의 위기이며 지구 전체의 위기입니다.<br>
                            <br>
                            기후위기는 북극곰과 지역 생태계에만 부정적 영향을 미치는 것이 아닙니다.<br>
                            기후위기를 대처하는 일은 북극곰을 지키는 것 뿐만 아니라 우리의 문제를<br>
                            해결하기 위한 일입니다.
                        </p>
                    </div>
                </div>
                <div id="content_2_2">
                    <div id="content_2_2_1"></div>
                    <div id="content_2_2_2">
                        <p align="center" style=" margin-top: 100px; "><span style="color: rgb(52, 152, 219); font-size: x-large; font-weight: bold;">매년 2월 27일은 "국제 북극곰의 날"</span><br>
                            2008년 5월 북극곰은 멸종위기 종으로 지정되었습니다. <br>
                            지구온난화로 북극의 빙하가 녹는 속도는 가속도가 붙었고, 빙하의 감소로 북극곰은<br>
                            사냥터와 쉴 곳이 줄어들어 힘든 시간을 보내고 있습니다. <br>
                            <br>
                            매년 2월 27일 국제 북극곰의 날은 북극곰에 대한 인식과 북극곰과 인간이 직면할<br>
                            위험을 환기하기 위한 날입니다.
                        </p>
                    </div>
                    <div id="content_2_2_3">
                        <div class="circle" align="center"><p class="number">02</p></div>
                    </div>
                </div>
                <div id="content_2_3">
                    <div id="content_2_3_1">
                        <div class="circle" align="center"><p class="number">03</p></div>
                    </div>
                    <div id="content_2_3_2">
                        <p align="center" style="margin-top: 100px;"><span style="font-weight: bold; font-size: x-large;">우리는 더 이상</span><br>
                            <span style="font-size: x-large; font-weight: bold; color: rgb(52, 152, 219);">북극곰</span><span style="font-size: x-large; font-weight: bold;">의 위기를 방관 할 수 없습니다</span>. <br>
                            <br>
                            <span style="font-weight: bold; font-size: x-large; color: rgb(52, 152, 219);">Blue Ice </span>와 함께 #챌린지명 으로 기후위기를 막고 <br>
                            멸종 위기에 처한 <span style="font-weight: bold; font-size: x-large; color: rgb(52, 152, 219);">북극곰</span>을 지켜주세요.
                        </p> <br>
                    </div>
                    <div id="content_2_3_3"></div>
                </div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>