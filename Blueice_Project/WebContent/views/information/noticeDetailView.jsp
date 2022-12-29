<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.information.model.vo.*" %>
<% 
// 필요한 데이터들 먼저 뽑기
Notice n = (Notice)request.getAttribute("n");
//게시글번호, 글제목, 글내용, 작성일, 작성자 (조회시 작성자 아이디값 "blueice123" / 작성하기시 로그인한회원번호 "1")

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
    
    div {
        /*border : 1px solid rgb(40, 78, 122);*/
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1800px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1270px; }
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
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
    }

    #news-title{ /* 제목을 담고 있는 div Id */
        width: 100%;
        height: 15%;
    }

    #news-title1 { width: 100%; height: 60%; float: left; }
    #news-title2 { width: 100%; height: 20%; float: left; }



    #news-content { /* 뉴스 기사를 담고 있는 div Id */
        width: 100%;
        height: 85%;
    }

    #news-content-sm { /* #news-content안의 뉴스 기사만 담을 수 있는 div */
        width: 70%;
        height: 100%;
        margin: auto;
        padding-top: 100px;
        font-size: 20px;
        word-spacing: 7px;
        letter-spacing: 1px;
    }

    #news-title1>p {  /* 뉴스 제목 글씨와 관련된 CSS */
        width: 100%;
        height: 100%;
        font-size: 40px;
        text-align: center;
        line-height: 135px;
    }

    #news-title2>p { /* 작성일 & 조회수 글씨와 관련된 CSS */
        width: 100%;
        height: 100%;
        text-align: left;
        font-size: medium;
        margin-left: 50px;
        margin-top: 30px;
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
		             <img src="<%= contextPath %>/resources/images/logo/logo3_2.png" id="logo2">
		         </div>
		     </div>
		     <div id="content">
		         <div id="content_1"></div>
		         <div id="content_2">
		             <div id="content_2_1"></div>
		             <div id="content_2_2">
		             
		             
                            <!-- 공지사항 게시글 틀 시작 -->
					 	    <div id="news-full">
						        <div id="news-title">
                                    <div id="news-title1">
                                    	<p><%= n.getNoticeTitle() %></p>
                                    </div>
                                    <div id="news-title2">
                                        <p>작성일 : <%= n.getCreateDate() %>&emsp;|&emsp;조회수 : <%= n.getHit() %></p>
                                    </div>
									
                                    <div class="form-button" style="margin-left: 1180px;">
                                       <% if(loginMember != null && loginMember.getMemId().equals("blueice123")) { %>
            
								            <a href="<%= contextPath %>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="btn btn-warning">수정</a>
								            <a href="<%= contextPath %>/delete.no?nno=<%= n.getNoticeNo() %>" class="btn btn-danger">삭제</a>
                                        	<a href="<%= contextPath %>/list.no?currentPage=1" class="btn btn-secondary">목록</a>
						            	<% } else { %>
                                        	<a href="<%= contextPath %>/list.no?currentPage=1" class="btn btn-secondary">목록</a>
                                       	<% } %>
                                    </div>
						        </div>

                                <hr>

						        <div id="news-content">
						            <div id="news-content-sm">
                                        <p>
                                            <%= n.getNoticeContent() %>
                                        </p>
                                    </div>
						        </div>
						    </div>
                            <!-- 공지사항 게시글 틀 종료 -->
					 	
					</div>
	                <div id="content_2_3"></div>
	            </div>
	            <div id="content_3" style=""></div>
	        </div>
	        <%@ include file="../common/footerbar.jsp" %>
	    </div>

</body>
</html>







































