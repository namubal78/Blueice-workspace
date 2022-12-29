<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.blueice.challenge.model.vo.*" %>
<%
	Reward r = (Reward)request.getAttribute("r");
	RewardAttachment ra = (RewardAttachment)request.getAttribute("ra");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리워드</title>
<style>
    
    div {
        /*border : 1px solid rgb(40, 78, 122);*/
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 2000px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1470px; }
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

    #news-img { /* 이미지를 담고 있는 div Id */
        width: 100%;
        height: 50%;
    }

    #small-img { /* #news-img 안의 이미지만 담을 수 있는 div */
        width: 70%;
        height: 100%;
        margin: auto;
        width: 70%;
        height: 100%;
        background-size: cover;
    }

    #news-content { /* 뉴스 기사를 담고 있는 div Id */
        width: 100%;
        height: 35%;
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
        line-height: 140px;
    }

    #news-title2>p {
        width: 100%;
        height: 100%;
        text-align: left;
        font-size: medium;
        margin-left: 20px;
        line-height: 100px;
    }



</style>
</head>
<body>

    <div class="wrap">
	    <%@ include file="../common/menubar.jsp" %>
		     <div id="navigator2"></div>
		     <div id="header">
		         <div id="header_1">
		             <p>리워드</p>
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
		             
		             
                            <!-- 주건우님의 작성 시작-->
					 	    <div id="news-full">
						        <div id="news-title">
                                    <div id="news-title1"><p><%= r.getChalRewardTitle() %></p></div>
                                    <div id="news-title2">
                                        <p>작성일 : <%= r.getChalRewardDate() %>&emsp;|&emsp;조회수 : <%= r.getChalRewardHit() %></p>
                                    </div>

                                    
                                        <div class="form-button" style="margin-left: 1100px;">
                                            <button onclick="location.href='<%= contextPath %>/list.re?currentPage=1'" class="btn btn-secondary">목록으로</button>
                                            <% if(loginMember != null && loginMember.getMemId().equals("blueice123")) { %>
	                                            <button onclick="location.href='<%= contextPath %>/updateForm.re?rno=<%= r.getChalRewardNo() %>'" class="btn btn-warning">수정</button>
	                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">삭제</button>
                                            <% } %>
                                        </div>
                                    

						        </div>

                                <hr>
                        		<% if(ra != null) { %>
						        <div id="news-img">
						            <div id="small-img"><img src="<%= r.getTitleImg() %>" style="background-size: cover; width: 100%; height: 100%;"></div>
						        </div>
						       <% } %>
						        
						        <div id="news-content">
						            <div id="news-content-sm">
                                        <p>
                                           <%= r.getChalRewardConts() %>
                                        </p>
                                    </div>
						        </div>
						    </div>
                            <!-- 주건우님의 작성 종료 -->
					 	
					 	
					    
					    
					    
					    
					</div>
	                <div id="content_2_3"></div>
	            </div>
	            <div id="content_3" style=""></div>
	        </div>
	        <%@ include file="../common/footerbar.jsp" %>
	    </div>

		<!-- 1:1문의삭제용 모달창 -->
	    <!-- The Modal -->
	    <div class="modal" id="deleteForm">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <!-- Modal Header -->
	          <div class="modal-header">
	            <h4 class="modal-title">게시글을 삭제하시겠습니까?</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	          </div>
	          <!-- Modal body -->
	          <div class="modal-body" align="center">
	          	<b>게시글 삭제후 복구가 불가능합니다. <br>
	          	정말로 삭제하시겠습니까? <br><br></b>
	          	<% if(ra != null) { %>
	          		<form action="delete.re?rno=<%= r.getChalRewardNo() %>&rano=<%= ra.getReFileNo() %>" method="post">
	          			<button type="submit" class="btn btn-danger btn-sm" style="margin: auto;">삭제하기</button>
	          		</form>
	          	<% } else { %>
	          		<form action="delete.re?rno=<%= r.getChalRewardNo() %>&rano=0" method="post">
	          			<button type="submit" class="btn btn-danger btn-sm" style="margin: auto;">삭제하기</button>
	          		</form>
	          	<% } %>

	          	
	          	<script>
	          	/*
	          	$(function() {
	          		let chalNo = <%= r.getChalRewardNo() %>;
	          		
	          		console.log(chalNo)
	          		<% if(ra != null) { %>
	          			let ra = <%= ra.getReFileNo() %>;
	          			$("form").attr("action", ("<%= contextPath %>/delete.re?rno=<%= r.getChalRewardNo() %>&rano="+ ra));
	          		<% } %>
	          	})
	          	*/
	          	</script>
	          </div>
	        </div>
	      </div>
	    </div>



</body>
</html>







































