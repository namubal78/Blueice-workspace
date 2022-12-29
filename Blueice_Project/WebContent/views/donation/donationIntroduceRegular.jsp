<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정기후원 안내</title>
<style>

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 2280px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1750px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 20%; float: left; }
    #content_2_2 { height: 65%; float: left; }
    #content_2_3 { height: 15%; float: left; }

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

    
    /* 폰트 */
    body { font-family: 'Noto Sans KR', sans-serif !important; }

    /* content_2_1 영역 ------------------------------ */
    #content_2_1>div { height: 100%; }

    .text-area {
        font: 14px;
        margin: 0;
        outline: none;
        float: left;
        width: 40%;
        padding: 50px 0;
        text-align: center;
    }
    
    .text-area>p {
        font-size: 18px;
        line-height: 30px;
    }

    .media-area {
        width: 60%;
        float: left;
    }

    .media-area>#bear { width:100%; height:100%;}

    /* 부트스트랩4 primary 버튼에 대한 css */
    button[class*=btn-primary] { background-color: #3970B3; height:40px; }


    /* content_2_2 영역 ----------------------- */

    dl, dt, dd { display: block; }

    .content_2_2>div {
        width: 100%;
    }

    .column3-list { height : 90%;}

    .column3-list>dl {
        float: left;
        margin: 25px 25px;
        width: 45%;
        vertical-align: top;
    }

    .column3-list>dl>dt {
        margin-bottom: 10px;
        font-size: 22px;
        font-weight: bold;
    }

    .column3-list>dl>dd {
        margin-top: 10px;
        font-size: 18px;
        line-height: 32px;
        color: #666;
        letter-spacing: -1.5px;
    }

    .column3-list img {
        display: block;
        width: 100%;
        height: 280px;
        margin-bottom: 15px;
    }


</style>
</head>
<body>

	<div class="wrap">

        <%@ include file="../common/menubar.jsp" %>

        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>후원</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1" style="margin-top:50px;">
                    <div class="text-area">
                        <span style="font-size:44px;"><i class="fa-solid fa-calendar-days" style="color:#163A66;"></i> 후원하기는</span><br><br>
                        <p>원하시는 금액을 후원하는 일시 후원과<br>
                        매월 약정된 금액을 후원하는 정기후원으로 나뉩니다.<br>후원금은 기후위기와 환경파괴를 막기 위해<br>소중히 사용됩니다. 
                            여러분의 도움으로 지구와<br>북극곰이 살아갈 터전을 지킬 수 있습니다.</p>
                            <%-- 로그인 안했을 시 로그인 페이지, 로그인 했을 시 후원 페이지로 이동 --%>
                            <% if(loginMember == null) { %>
                                <div class="btn"><button type="button" id="next-btn" class="btn btn-primary" onclick="toLogin();">후원하기</button></div>
                            <% } else { %>
                                <div class="btn"><button type="button" id="next-btn" class="btn btn-primary" onclick="toDona();">후원하기</button></div>
                            <% } %>
                    </div>
                    <div class="media-area">
                        <img src="<%= contextPath %>/resources/images/donation/polar_bear_3d.jpg" id="bear">
                    </div>
                </div>
                <br>
                <div id="content_2_2" style="margin-top:50px;">
                    <h4 style="margin: 30px 25px; font-size: 40px;">후원금, 이렇게 사용됩니다.</h4>
                    <div class="column3-list">
                        <!-- 220428 텍스트 교체 시작 -->
                        <dl>
                            <dt>
                                <img src="resources/images/donation/introduce_investigation.png">
                                조사
                            </dt>
                            <dd>환경문제 해결을 위해서는 과학적인 자료와 근거가 필요합니다.<br>블루아이스는 과학적 조사를 통해 환경 문제를 연구하고<br> 이를 해결하기 위한 방안을 찾습니다.</dd>
                        </dl>
                        <dl>
                            <dt>
                                <img src="resources/images/donation/introduce_record.png">
                                기록
                            </dt>
                            <dd>
                                우리는 환경 파괴 현장의 증인이 되어<br> 현장의 사진과 영상을 증거로 남기고 희생자들을 인터뷰합니다.<br> 또한 전문가 및 과학자들과 협력해<br> 환경파괴에 대한 구체적 수치가 담긴 보고서를 발표합니다.
                            </dd>
                        </dl>
                        <dl>
                            <dt>
                                <img src="resources/images/donation/introduce_peaceful.png">
                                평화적 직접행동
                            </dt>
                            <dd>블루아이스는 '평화적 직접행동'을 통해<br> 정부와 기업이 시민들의 목소리에 귀를 기울이게 만들고,<br> 미디어의 관심을 끌어내어<br> 더 많은 대중들이 변화에 동참할 수 있도록 합니다.</dd>
                        </dl>
                        <dl>
                            <dt>
                                <img src="resources/images/donation/introduce_lobby.png">
                                변화 도출
                            </dt>
                            <dd>블루아이스는 환경 문제를 지적하는 데서 그치지 않고,<br> 실현 가능한 해결책을 정부 기관과 기업에 제시하여<br> 적극적이고 구체적인 변화를 만들도록 설득하고 요구합니다.</dd>
                        </dl>
                    </div>
                    <br clear="both">
                </div>

                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>
</body>
</html>