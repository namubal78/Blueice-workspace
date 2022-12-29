<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.common.model.vo.PageInfo, java.util.ArrayList, com.blueice.member.model.vo.Member" %>
<%
	// 필요한 데이터 뽑기
	PageInfo pi = (PageInfo)request.getAttribute("pi"); // 페이징바 만들기
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); // 조회된 내용물 출력하기
    String memName = request.getAttribute("memName") == null ? "" : (String)request.getAttribute("memName");

	// 자주 쓸 필드값만 미리 빼둠
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /*border : 1px solid rgb(40, 78, 122); */
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
    #donaTable { height: 90%; float: left; padding: 50px; }

    #donaText>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }
    tbody>tr { <% if(list.size() != 0) { %> 
    	cursor:pointer;
    	<% } %>
    }

</style>
</head>
<body>

	<div class="wrap">
	    <%@ include file="../common/z-index_menubar.jsp" %>
	    <div id="navigator2"></div>
	    <div id="header">
	        <div id="header_1">
	            <p>회원 관리</p>
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
	                <form id="search1" method="post" action="">
	                	<div id="myDona">
							<div id="donaText"><p>단체회원 관리</p></div>
							<div id="donaTable">
								<div class="admin-donation-search" style="z-index:100 !important;">
									<div class="form-group row" style="margin-left: 380px; margin-top: -20px;">
										<div style="margin-right:10px;">
											<!-- 검색 카테고리 설정 칸 --> 
											<select id="selectBox" class="selectpicker show-tick" data-style="btn-primary" data-width="130px" onchange="OnChange();">
												<option value="default">구분</option>
												<option value="optionNo">회원 번호</option>
												<option value="optionEnrollDate" >가입일</option>
												<option value="optionName" selected>단체 이름</option>
											</select>
										</div>	                                
									<!-- 검색내용 입력 칸 -->
									<div class="input-group" style="width: 250px;">
										<input type="text" id="search2" class="form-control" name="" placeholder="내용을 입력해주세요" value="<%= memName %>">
										<div class="input-group-append">
											<button class="btn btn-secondary" type="submit" onclick="return validate();">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</div>
									<div style="margin-left:150px;">
										<button type="button" onclick="window.location.href='groupMemberListAll.me?currentPage=1'" class="btn btn-primary">전체목록으로</button>
									</div>
								</div>
							</div> 
							
							<script>
							
							function OnChange() {
							        	
								let gubun = document.getElementById("selectBox").options[document.getElementById("selectBox").selectedIndex].value;
								let search1 = document.getElementById("search1");
								let search2 = document.getElementById("search2");
								        	
								if(gubun == "optionNo"){
									search1.action = "<%= contextPath %>/groupMemberListMemNo.me?currentPage=1";
									search2.name = "memNo";
								} 
								else if(gubun == "optionEnrollDate"){
									search1.action = "<%= contextPath %>/groupMemberListDate.me?currentPage=1";
									search2.name = "enrollDate";
								} 
								else if(gubun == "optionName"){
									search1.action = "<%= contextPath %>/groupMemberListMemName.me?currentPage=1";
									search2.name = "memName";
								}
							}
							</script>
							   
							<table class="table table-hover">
								<thead>
									<tr>
									    <td>회원번호</td>
									    <td>전화번호</td>
									    <td>아이디</td>
									    <td>기업/단체이름</td>
									    <td>가입일</td>
									</tr>
								</thead>
								<tbody>
								<% if(list.isEmpty()) { %>
									<tr>
										<td colspan="5">조회된 리스트가 없습니다.</td>
									</tr>
								<% } else { %>
									<% for(int i = 0; i < list.size(); i++) { %>
										<tr>
											<td><%= list.get(i).getMemNo() %></td>
											<td><%= list.get(i).getMemName() %></td>
											<td><%= list.get(i).getPhone() %></td>
											<td><%= list.get(i).getMemId() %></td>
											<td><%= list.get(i).getEnrollDate() %></td>
										</tr>
									<% } %>
								<% } %>
								</tbody>
							</table>
							     
							<script>
							
							// 상세조회용 함수
							$(function() {
								
								$("#donaTable>table>tbody>tr").click(function() {
									
									<% if(!list.isEmpty()) { %>
										location.href = "<%= contextPath %>/detail.me?mno=" + $(this).children().eq(0).text();	
									<% } %>
								});
							});
							</script>
							
							<script>
							
							function validate() {
							           	
								let gubun = document.getElementById("selectBox").options[document.getElementById("selectBox").selectedIndex].value;
								let search1 = document.getElementById("search1");
								let search2 = document.getElementById("search2");
								
								/* 카테고리 창이 구분일경우 서브밋 안넘어가게  */
								if(gubun == "default") {
									alert("카테고리값을 변경해주세요.");
									return false;
								}
								            
								/* 회원넘버 정규식 */
								if(gubun == "optionNo") {
									regExp = /^[0-9]+$/;
									if(!regExp.test(search2.value)) {
										alert("숫자값만 입력해주세요.");
										search2.select(); // 재입력 유도
										return false;
									}
								}
								
								/* 날짜 정규식 */
								if(gubun == "optionEnrollDate") {
									regExp = /[0-9]{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])/;
									if(!regExp.test(search2.value)) {
										alert("yyyy-mm-dd 형식으로 입력해주세요.");
										search2.select(); // 재입력 유도
										return false;
									}
								}
							}
							</script>	
							
							<br>
							  
							<!-- 페이징 처리 -->
							<nav aria-label="Page navigation example">
								<ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
									<li class="page-item">
										<% if(currentPage != 1){ %>
											<a class="page-link" href="<%= contextPath %>/groupMemberListMemName.me?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Previous</span>
											</a>
										<% } %>
									</li>
									
									<% for(int p = startPage; p <= endPage; p++){ %>
									<% if(p != currentPage){ %>
									<li class="page-item"><a class="page-link" href="<%= contextPath %>/groupMemberListMemName.me?currentPage=<%= p %>"><%= p %></a></li>
										<% } else{ %>
											<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 --> 
											<li class="page-item"><a class="page-link"><%= p %></a></li>
										<% } %>
									<% } %>
									  
									<li class="page-item">  
										<% if(currentPage != maxPage){%>
											<a class="page-link" href="<%= contextPath %>/groupMemberListMemName.me?currentPage=<%= currentPage + 1 %>" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Next</span>
											</a>
										<% } %>
									</li>
								</ul>
							</nav>
							</div>        
						</div>
	               	</form>
	            </div>
	            <div id="content_2_3"></div>
	        </div>
	        <div id="content_3"></div>
	    </div>
	    <%@ include file="../common/footerbar.jsp" %>
	</div>
	
</body>
</html>