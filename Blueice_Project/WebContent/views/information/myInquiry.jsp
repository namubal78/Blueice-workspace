<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.common.model.vo.PageInfo, java.util.ArrayList, com.blueice.information.model.vo.Inquiry" %>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi"); // 페이징바 만들기
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("pageList"); // 조회된 내용물 출력하기
	ArrayList<Inquiry> loginList = new ArrayList<>();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /*border : 1px solid rgb(40, 78, 122);*/
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
    #content_2_1 { height: 15%; float: left; }

    #content_2_2 { 
        height: 70%; 
        float: left;
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
        padding: 30px 30px;
    }

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

    
    #myDona { width: 100%; height: 100%; }
    #myDona>div { width: 100%; }

    #donaText { height: 10%; float: left; }
    #donaTable { height: 90%; float: left; padding: 70px; }

    #donaText>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }
    tbody>tr { cursor:pointer; }

</style>
</head>
<body>

<div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
    <div id="navigator2"></div>
    <div id="header">
        <div id="header_1">
            <p>마이페이지</p>
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
                <div id="myDona">
                    <div id="donaText"><p>1:1 문의 내역</p></div>
                    <div id="donaTable">
                      
                       <!-- 로그인 멤버 정보 가져와서 로그인 멤버만 리스트 조회 -->
                        <%
                    		for(int i = 0; i < list.size(); i++) {
                    		if(list.get(i).getMemNo().equals(String.valueOf(loginMember.getMemNo()))) {
                    			loginList.add(list.get(i));	
                    		}
                    	}	
                        %>

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>글번호</td>
                                    <td>답변현황</td>
                                    <td>제목</td>
                                    <td>작성일</td>
                                </tr>
                            </thead>
                            
                            <!-- 조회 게시물 -->
                            <tbody>
                            	<% if(loginList.isEmpty()) { %>
                                <tr>
                                	<td colspan="4">조회된 리스트가 존재하지 않습니다.</td>
                                </tr>
                                <% } else { %>
                                <% for(Inquiry i : loginList) { %>
                                <tr>
                                    <td><%= i.getInquiryNo() %></td>
                                    <td><% if(i.getAnswerConts() == null) { %>
                                    	답변대기
                                    	<% } else { %>
                                    	답변완료
                                    	<% } %>
                                    </td>
                                    <td><%= i.getInquiryTitle() %></td>
                                    <td><%= i.getInquiryDate() %></td>
                                </tr>
                               		 <% } %>
                                <% } %>
                                
                            </tbody>
                        </table>
                        
                        	<!-- 게시글 클릭시 상세조회로 넘어가는 메소드 -->
                            <script>
						    	$(function() {
						    		
						    		$(".table-hover>tbody>tr").click(function() {
						    			<% if(!loginList.isEmpty()) { %>
						    			location.href = "<%= contextPath %>/myInquiryDetail.in?ino=" + $(this).children().eq(0).text();	
						    			<% } %>
						    		});
						    		
						    	});
						    </script>
                        
                           
							<!-- 페이징 처리 -->
	                        <nav aria-label="Page navigation example">
	                            <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
	                                
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() != 1){ %>
	                                    <a class="page-link" href="<%= contextPath %>/myInquiry.in?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
	                                        <span aria-hidden="true">&laquo;</span>
	                                        <span class="sr-only">Previous</span>
	                                    </a>
	                                    <% } %>
	                                </li>
	                                
	                                <% for(int p = pi.getStartPage(); p <= pi.getEndPage() - 2; p++){ %>
	                                
	                                	<% if(p != pi.getCurrentPage()){ %>
	                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/myInquiry.in?currentPage=<%= p %>"><%= p %></a></li>
	                                	<% } else{ %>
	                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 -->
	                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
	                                	<% } %>
	                              	<% } %>
	                              	
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() == 1) { %>
	                                    <% } else if(pi.getCurrentPage() != pi.getMaxPage()){%>
	                                    	<a class="page-link" href="<%= contextPath %>/myInquiry.in?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
		                                        <span aria-hidden="true">&raquo;</span>
		                                        <span class="sr-only">Next</span>
	                                    	</a>
	                                    <% } %>
	                                </li>
                            
                            
                            </ul>
                        </nav>
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