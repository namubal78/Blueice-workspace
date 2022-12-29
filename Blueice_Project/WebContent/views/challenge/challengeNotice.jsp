<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.blueice.challenge.model.vo.Challenge"%>
<%
	ArrayList<Challenge> list = (ArrayList<Challenge>)request.getAttribute("list");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챌린지</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 2080px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1550px;}
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


    /* 주건우님의 CSS 작성 시작 */
    #chal { 
        width: 100%; 
        height: 100%; 
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
        padding: 30px 30px;
    }

    #chal>div { width: 100%; }

    #chalBtn { height: 7%; float: left; padding-top: 10px;}
    #chalTable { height: 90%; float: left; padding-top: 30px;}

    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }
    
	.click:hover{ cursor: pointer; opacity: 0.5;}
	
	table>tbody>tr>td{ line-height: 40px;}

</style>
</head>
<body>

    <div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>챌린지</p>
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
                    <div id="chal">
                        <div id="chalBtn">

                            <!-- 글작성 버튼 -->
                            <% if(loginMember != null && loginMember.getMemNo() == 1){%>
                            	<div class="form-button" style="margin-left:1130px;">
                              		<a href="enroll.challenge"><button class="btn btn-primary">글작성</button></a>
                           		</div>
                           <% } %>

                        </div>
                        <div id="chalTable">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>진행현황</td>
                                        <td>제목</td>
                                        <td>조회수</td>
                                        <td>시작일</td>
                                        <td>종료일</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <% if(!list.isEmpty()) {%>
                                	<% for(int i = 0; i < list.size(); i++) { %> 
	                                    <tr>
	                                    	
	                                        <% if(list.get(i).getChalStatus().equals("Y")){ %>
	                                        	  <td><button class="btn btn-primary" style="cursor: default;">진행중</button></td>
	                                        <% } else { %>
	                                        	 <td><button class="btn btn-secondary" style="cursor: default;">종료</button></td>
	                                        <% } %>
		                                        <td class="click">
		                                        	<a href="<%= contextPath %>/comment.challenge?cno=<%= list.get(i).getChalNo() %>&currentPage=1" style="text-decoration: none; color: black">
		                                        		<%= list.get(i).getChalTitle() %>
		                                        	</a>	
		                                        </td>		    
		                                        <td><%= list.get(i).getChalHit() %></td>
		                                        <td><%= list.get(i).getChalStart() %></td>
		                                        <td><%= list.get(i).getChalEnd() %></td>
	                                    </tr>
                                    <% } %>
                                 <% } else { %>
                                 	<tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
                                 <% } %>
                                 <%--
                                    <tr>
                                        <td><button class="btn btn-secondary" style="cursor: default;">종료</button></td>
                                        <td>챌린지 제목@@@</td>
                                        <td>78</td>
                                        <td>2022-08-26</td>
                                        <td>2022-09-21</td>
                                    </tr>
                                    <tr>
                                        <td><button class="btn btn-secondary" style="cursor: default;">종료</button></td>
                                        <td>챌린지 제목@@@</td>
                                        <td>78</td>
                                        <td>2022-08-26</td>
                                        <td>2022-09-17</td>
                                    </tr>
                                    <tr>
                                        <td><button class="btn btn-secondary" style="cursor: default;">종료</button></td>
                                        <td>챌린지 제목@@@</td>
                                        <td>78</td>
                                        <td>2022-08-26</td>
                                        <td>2022-09-02</td>
                                    </tr>
                                    <tr>
                                        <td><button class="btn btn-secondary" style="cursor: default;">종료</button></td>
                                        <td>챌린지 제목@@@챌린지 제목@@@챌린지 제목@@@챌린지 제목@@@챌린지 제목@@@</td>
                                        <td>78</td>
                                        <td>2022-08-26</td>
                                        <td>2022-08-26</td>
                                    </tr>
                                     --%>
                                </tbody>
                            </table>
                        
                       
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