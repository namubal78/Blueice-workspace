<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업소개</title>
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

    #content_2>div>div{height: 100%;} /* 3등분 한 영역  */
    #content_2_2_1{width: 31%; float: left;}
    #content_2_2_2{width: 31%; float: left; margin-left: 50px;}
    #content_2_2_3{width: 31%; float: right;}

    #content_2>div>div>div{width: 100%;}
    #content_2-pic{height: 70%;}
    #content_2-text{height: 30%;}

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

    #header_2>img { height: 60%; margin-left: 150px; margin-top: 70px; }


    /* content 영역 */
    #content>div { height: 100%; float: left; }
    #content_1 { width: 15%; }
    #content_2 { width: 70%; }
    #content_3 { width: 15%; }

    /* 동그라미 스타일 */
    .circle { 
        border-radius:100%;
        background-color: #95b8e5;
        width: 80px;
        height: 80px;
        margin: auto;
        margin-top: 440px;
        position: absolute; 
        margin-left: 165px;
    }

    /* 동그라미 안 숫자 스타일 */
    .number {
        font-size: 40px;
        color: white;
        user-select: none;
        font-family: HanSans;
        box-sizing: content-box; 
        margin: auto;
        text-align: center;
        margin-top: 7px;           
    }

    #content_2-pic>img { width: 100%; height: 100%; object-fit: cover; }

</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>사업</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1">
                
            </div>
            <div id="content_2">
                <div id="content_2_1">
                    
                </div>
                <div id="content_2_2">
                    <div id="content_2_2_1">
                        
                        <div id="content_2-pic">
                            <div class="circle"><div class="number">01</div></div>
                            <img src="<%= contextPath %>/resources/images/polar3.jpg">
                            
                        </div>
                        <div id="content_2-text">
                            <br><br> 
                            <p style="font-size: 24px; " align="center">주요 서식지 보호 및 <br> </p>
                            <p style="font-size: 24px;  color: #225ca8;" align="center">지속 가능한 사냥 자원 확보</p>
                        </div>
                        
                        
                    </div>
                    <div id="content_2_2_2">
                        <div id="content_2-pic">
                            <div class="circle"><div class="number">02</div></div>
                            <img src="<%= contextPath %>/resources/images/polar2.jpg">
                        </div>
                        <div id="content_2-text">
                            <br><br> 
                            <p style="font-size: 24px; " align="center">지나친 산업화가 미치는 <br> </p>
                            <p style="font-size: 24px;  color: #225ca8;" align="center">영향 줄이기</p>
                        </div>
                        
                        
                    </div>
                    <div id="content_2_2_3">
                        <div id="content_2-pic">
                             <div class="circle"><div class="number">03</div></div>
                             <img src="<%= contextPath %>/resources/images/ppolar.jpg">
                        </div>
                        <div id="content_2-text">
                            <br><br> 
                            <p style="font-size: 24px; " align="center">북극 연구 지원 <br> </p>
                            <p style="font-size: 24px;  color: #225ca8;" align="center">더 안전한 커뮤니티 생성</p>
                        </div>
                       
                        
                    </div>
                    
                </div>
                <div id="content_2_3">
                    
                </div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>