<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.common.model.vo.PageInfo, java.util.ArrayList, com.blueice.information.model.vo.Notice"%>
<% 
	PageInfo pi = (PageInfo)request.getAttribute("pi"); // 페이징바 만들기 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	String searchText = request.getAttribute("searchText") == null ? "" : (String)request.getAttribute("searchText");
	// request 에 담았던 list 키값의 값을 뽑아오기 (== 공지사항 전체 리스트 조회한 결과물) 
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1780px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 1250px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 15%; float: left; }
    #content_2_2 { height: 70%; float: left; }
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

    #chalBtn { height: 10%; float: left;}
    #chalTable { height: 90%; float: left; }

    .table { text-align: center; height: 50px; margin-top: 20px;}

    thead>tr { font-weight: bolder; font-size: medium; }
    
    .form-group { margin: auto; text-align: center; }
	.input-group { margin-left: 490px; margin-top: 25px;}
	
	.table>tbody>tr:hover {
        opacity: 0.7;
        cursor: pointer;
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
                    <div id="chal">
                        <div id="chalBtn">
	 
                            <div class="form-group row">
                                
                                <!-- 검색창 -->
                               <form action="<%= contextPath %>/list.no" method="get">
                               	<input type="hidden" name="currentPage" value="1">
	                                <div class="input-group" style="width: 280px;">
	                                    <input type="text" value="<%= searchText %>" class="form-control" name="searchText" placeholder="검색어를 입력하세요">
	                                    <div class="input-group-append">
	                                        <button class="btn btn-secondary" type="submit">
	                                        <i class="fa fa-search"></i>
	                                        </button>
	                                    </div>
	                                </div>
								</form>
								
                                <!-- 글작성 버튼 -->
                                <% if(loginMember != null && loginMember.getMemId().equals("blueice123")) { %>
                                <div class="form-button" style="margin-left:1130px; margin-bottom: 10px;">
                                    <a class="btn btn-primary" href="<%= contextPath %>/enrollForm.no">글작성</a>
                                </div>
                                <% } %>
                                
                            </div>
                        </div>
                      
                  
                        <div id="chalTable" style="margin-top:10px;">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>번호</td>
                                        <td>글제목</td>
                                        <td>조회수</td>
                                        <td>작성일</td>
                                    </tr>
                                </thead>
                               
                                <tbody>
                                     <% if(list.isEmpty()) { %>
										<!-- 조회된 공지사항이 없을 경우 -->
										<tr>
										<td colspan="4">존재하는 공지사항이 없습니다.</td>
										</tr>
                                     <% } else { %>
										<!-- 조회된 공지사항이 적어도 한 건 이상 있을 경우 -->
	                                     <% for(Notice n : list) { %>
	                                     	<tr>
	                                     		<td><%= n.getNoticeNo() %></td>
	                                     		<td><%= n.getNoticeTitle() %></td>
	                                     		<td><%= n.getHit() %></td>
	                                     		<td><%= n.getCreateDate() %></td>
	                                     	</tr>
                                     	<% } %>
                                     <% } %>
                                </tbody>
                            </table>
                        
                        	<script>
                        		$(function() {
                        			
                        			$(".table>tbody>tr").click(function() {

                        				let nno = $(this).children().eq(0).text(); // -> noticeNo (String 타입)
                        				location.href = "<%= contextPath %>/detail.no?nno=" + nno;
                        			});
                        		});
                        	</script>
                            
                            
                            <!-- 페이징 처리 -->
                            <nav aria-label="Page navigation example">
                                <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
                                    <li class="page-item">
                                    <% if(pi.getCurrentPage() != 1) { %>
                                    <a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <% } %>
                                    </li>
                                    
                                     <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
	                                
	                                	<% if(p != pi.getCurrentPage()){ %>
	                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= p %>"><%= p %></a></li>
	                                	<% } else{ %>
	                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 -->
	                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
	                                	<% } %>
	                              	<% } %>
	                              	
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() == 1) { %>
	                                    
	                                    <% } else if(pi.getCurrentPage() != pi.getMaxPage()){%>
	                                    	<a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
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