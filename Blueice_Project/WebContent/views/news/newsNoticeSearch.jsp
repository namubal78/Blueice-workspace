<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.blueice.news.model.vo.*, com.blueice.common.model.vo.PageInfo"%>
<%
	ArrayList<News> searchList = (ArrayList<News>)request.getAttribute("searchList");
	String keyword = request.getAttribute("keyword") == null ? "" : (String)request.getAttribute("keyword");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스</title>
<style>

	div {
		/*border : 1px solid rgb(40, 78, 122);*/
		box-sizing : border-box;
	}

	/* 전체를 감싸는 wrap */
	.wrap {
		width: 98%;
		height: 2100px;
		margin : auto; /* 가운데로 좌, 우 자동 정렬*/
	}

	.wrap>div { width: 100%; }

	#navigator2 { height: 150px; }

	#content { height: 1565px; }
	#content_2>div { width: 100%;}
	#content_2_1 { height: 10%; float: left;}
	#content_2_2 { 
		height: 80%; 
		float: left;
		margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
        padding: 50px 50px;
	}

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
	#content_2 { width: 70%; }
	#content_3 { width: 15%; }

	/* 주건우님의 CSS 영역 시작 */
	#newsmain{ /* 모든 요소들을 담고 있는 div Id */
		width: 100%;
		height: 100%;
	}

	#search { height: 5%; width: 100%; margin: auto;}
	
	.news{ /* 뉴스 게시판의 요소들을 담고 있는 div class */
		width: 95%;
		height: 45%;
		padding: 10px 10px;
	}

	.newsfeed{ /* 뉴스 게시판 안에 가로 두줄을 맡고 있는 div class */
		width: 33%;
		height: 100%;
		display: inline-block;
	}

	.img{ /* 이미지를 담고 있는 div class */
		width: 80%;
		height: 70%;
		margin: auto;
	}

	.content{ /* 게시글 제목을 담고 있는 div class */
		width: 100%;
		height: 20%;
		padding-top: 8px;
		font-size: 20px;
		font-weight: bold;
		padding-left: 40px;
		padding-right: 10px;
	}

	.date{ /* 게시글 날짜를 담고 있는 div class */
		width: 100%;
		height: 10%;
		font-weight: bold;
		font-size: 16px;
		padding-left: 40px;
	}



	.input-group { margin: auto; }
	.form-button {  width: 11%; float: right;  }

	#number{ /* 하단 페이지 번호를 담고 있는 div Id */
		width: 30%;
		height: 5%;
		margin: auto;
		margin-top: 15px;
	}


        
</style>
</head>
<body>

 	
	<div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
	     <div id="navigator2"></div>
	     <div id="header">
	         <div id="header_1">
	             <p>뉴스</p>
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

					
	             
	             
					<!-- 주건우님의 작성 시작 -->
				 	<div id="newsmain">
						<div id="search">
							<!-- Another variation with a button -->
							<form action="<%= contextPath %>/search.news?currentPage=1" method="post">
								<div class="input-group" style="width: 280px;">
									<input type="text" name="keyword" class="form-control" placeholder="검색어를 입력해주세요" value="<%= keyword %>">
									<div class="input-group-append">
										<button class="btn btn-secondary" type="submit">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</form>

							<!-- 글작성 버튼 -->
							<% if(loginMember != null && loginMember.getMemNo() == 1){%>							
								<div class="form-button">
									<a href="<%= contextPath %>/enroll.news"><button class="btn btn-primary">글작성</button></a>
								</div>
							<% } %>



						</div><br clear="both">

						<!-- 뉴스기사 게시판 -->
				        <div class="news">
				        
				        	<% if(!searchList.isEmpty()) {%>
				        	
				        		<% for(News n : searchList){ %>
						            <div class="newsfeed">
						                <div class="img"><img src="<%= n.getTitleImg() %>" style="background-size: cover; width: 100%; height: 100%;"></div>
						                <div class="content"><a href="page.news?nno=<%= n.getNewsNo() %>"><%= n.getNewsTitle() %></a></div>
						                <div class="date">작성일 : <%= n.getNewsDate() %>&nbsp;&nbsp;|&nbsp;&nbsp;조회수 : <%= n.getNewsHit() %></div>
						            </div>
								<% } %>
				        
				        	<% } else{ %>
				        
				        	<div align="center" style="height: 300px; padding-top: 120px;">등록된 게시글이 없습니다</div>
				        	<% } %>
				        	<%--
				            <div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다.</div>
				                <div class="date">2022.10.20&emsp;|&emsp;조회수 10</div>
				            </div
							><div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다.</div>
				                <div class="date">2022.10.20</div>
				            </div
				            ><div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다.</div>
				                <div class="date">2022.10.20</div>
				            </div>
				        </div>
				        <div class="news">
				            <div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다. 그리고 집에 가고 싶다</div>
				                <div class="date">2022.10.20</div>
				            </div
				            ><div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다.</div>
				                <div class="date">2022.10.20</div>
				            </div
				            ><div class="newsfeed">
				                <div class="img"></div>
				                <div class="content">무엇을 살 사람은 봄바람을 우리 아니다.</div>
				                <div class="date">2022.10.20</div>
				            </div>
				        </div>
				         --%>

							<!-- 페이징 처리 -->
	                        <nav aria-label="Page navigation example">
	                            <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
	                                
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() != 1){ %>
	                                    <a class="page-link" href="<%= contextPath %>/search.news?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
	                                        <span aria-hidden="true">&laquo;</span>
	                                        <span class="sr-only">Previous</span>
	                                    </a>
	                                    <% } %>
	                                </li>
	                                
	                                <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
	                                
	                                	<% if(p != pi.getCurrentPage()){ %>
	                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/search.news?currentPage=<%= p %>"><%= p %></a></li>
	                                	<% } else{ %>
	                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 -->
	                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
	                                	<% } %>
	                              	<% } %>
	                              	
	                                <li class="page-item">
	                                    <% if(pi.getCurrentPage() != pi.getMaxPage()){%>
	                                    	<a class="page-link" href="<%= contextPath %>/search.news?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
		                                        <span aria-hidden="true">&raquo;</span>
		                                        <span class="sr-only">Next</span>
	                                    	</a>
	                                    <% } %>
	                                </li>
	                                
	                            </ul>
	                        </nav>
						</div>
				    </div>
					<!-- 주건우님의 작성 종료-->
				    
				    
				    
				    
				</div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>
	
	
</body>
</html>