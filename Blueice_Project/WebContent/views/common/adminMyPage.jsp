<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1650px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1120px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 20%; float: left; }
    #content_2_2 { height: 80%; float: left; }

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
    
    /* 본문 */
    #content_2_2_1 { 
        width: 40%;
        height: 80%;
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
    }

    #userP { width: 100%; height: 40%; float: left; }
    #userB { 
        width: 100%; 
        height: 55%;
        text-align: center;
        float: left;
        margin-top: 20px;
        padding: 20px;
    }

    #userPic { 
        width: 40%; 
        height: 100%; 
        float: left;
        display: flex;
        justify-content: center;
        align-items: center; 
    }
    
    #userN { width: 60%; height: 100%; float: left; }

    #userPic>img { width: 70%; }
    #userN>p {
        width: 100%;
        height: 100%;
        font-size: 30px;
        font-weight: bold;
        text-align: center;
        margin: 100px 0px;
    }


    /* content 영역 */
    #content>div { height : 100%; float : left; }
    #content_1 { width : 15%; }
    #content_2 { width : 70%; }
    #content_3 { width : 15%; }

    button[class*=btn-primary] { background-color: #3970B3; }

</style>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        
        
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>관리자 페이지</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1"></div>
                <div id="content_2_2">
                    <div id="content_2_2_1">
                        <div id="userP">
                            <div id="userPic"><img src="<%= contextPath %>/resources/images/user.png"></div>
                            <div id="userN"><p><%= loginMember.getMemName() %> 님 환영합니다!</p></div>
                        </div>
                        <div id="userB">
                            <button type="button" onclick="location.href='member.gr'" class="btn btn-primary btn-block btn-lg">통계 관리</button>
                            <button type="button" onclick="location.href='<%= contextPath %>/memberListAll.me?currentPage=1'" class="btn btn-primary btn-block btn-lg">개인 회원 관리</button>
                            <button type="button" onclick="location.href='<%= contextPath %>/groupMemberListAll.me?currentPage=1'" class="btn btn-primary btn-block btn-lg">단체 회원 관리</button>
                            <button type="button" onclick="location.href='<%= contextPath %>/temAdminlist.do?currentPage=1'" class="btn btn-primary btn-block btn-lg">일시 후원 관리</button>
                            <button type="button" onclick="location.href='<%= contextPath %>/regAdminlist.do?currentPage=1'" class="btn btn-primary btn-block btn-lg">정기 후원 관리</button>
                            <button type="button" onclick="location.href='adminList.in?currentPage=1'" class="btn btn-primary btn-block btn-lg">1:1 문의 관리</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>

</body>
</html>