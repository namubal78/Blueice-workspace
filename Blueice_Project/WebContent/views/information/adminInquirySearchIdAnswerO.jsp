<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.common.model.vo.PageInfo, java.util.ArrayList, com.blueice.information.model.vo.Inquiry" %>    

<%
	PageInfo pi = (PageInfo)request.getAttribute("pi"); // 페이징바 만들기
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("pageList"); // 조회된 내용물 출력하기
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
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
    tbody>tr { <% if(list.size() != 0) { %> 
    	cursor:pointer;
    	<% } %>
    }

</style>
</head>
<body>

<div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
    <div id="navigator2"></div>
    <div id="header">
        <div id="header_1">
            <p>관리자페이지</p>
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
                    <div id="donaText"><p>1:1 문의 관리</p></div>
                    <div id="donaTable">
                    
                    <form id="search1" method="post" action="">
                	<!-- 검색 카테고리 설정 칸 -->             	
                    <script> 
			            function OnChange() {
			            	
                            let gubun = document.getElementById("searchAdmin").options[document.getElementById("searchAdmin").selectedIndex].value;
							let search1 = document.getElementById("search1");
							let search2 = document.getElementById("search2");
							
			                if(gubun == "category") {
	                            $("#search2").attr("readonly", "true");
	                            $("form-control").val('');
	                            $("#search2").removeAttr("placeholder");
			                	search1.action = "<%= contextPath %>/adminList.in?currentPage=1";
			                }
							
			                else if(gubun == "answerO"){
			                	$("#search2").attr("readonly", "true");
	                            $("form-control").val('');
	                            $("#search2").removeAttr("placeholder");
								search1.action = "<%= contextPath %>/adminAnswer.in?currentPage=1&answer=O";
			                	
			                } 
			                else if(gubun == "answerX"){
			                	$("#search2").attr("readonly", "true");
	                            $("form-control").val('');
	                            $("#search2").removeAttr("placeholder");
			                	search1.action = "<%= contextPath %>/adminAnswer.in?currentPage=1&answer=X";
			                	
			                } 
			                else if(gubun == "memId"){
			                    $("#search2").removeAttr("readonly");
			                    $("#search2").attr("placeholder", "내용을 입력해주세요.");
			                	search1.action = "<%= contextPath %>/adminMemId.in?currentPage=1";
			                	search2.name = "memId";
			                }
			            }
			       </script>      
			       	              
                   <div class="form-group row" style="margin-left:380px; margin-top:-50px;">
                            <div style="margin-right:10px;">
                                <!-- 검색 카테고리 설정 칸 -->
                                <select id="searchAdmin" name="adminChoice" class="selectpicker show-tick" data-style="btn-primary" data-width="100px" onchange="return OnChange();">
                               		<option value="category">구분</option>
                                    <option value="answerO" selected>답변완료</option>
                                    <option value="answerX">답변대기</option>
                                    <option value="memId">아이디</option>
                                </select>
                            </div>
                            <!-- 검색내용 입력 칸 -->
                            <div class="input-group" style="width: 250px;">
                                <input type="text" id="search2" class="form-control" readonly>
                                <div class="input-group-append">
                                    <button class="btn btn-secondary" type="submit">
                                    <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>

                        <!-- 날짜 검색 버튼 -->

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>글번호</td>
                                    <td>답변현황</td>
                                    <td>제목</td>
                                    <td>작성일</td>
                                    <td>작성자</td>
                                </tr>
                            </thead>
                            
                            <!-- 조회 게시물 -->                
                            <tbody>
                                <% if(list.isEmpty()) { %>
                                <tr>
                                	<td colspan="5" disabled>조회된 리스트가 존재하지 않습니다.</td>
                                </tr>
                                <% } else { %>
                                <% for(Inquiry i : list) { %>
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
                                    <td><%= i.getMemNo() %></td>
                                </tr>
                               		 <% } %>
                                <% } %>
                            </tbody>
                        </table>
                        
                         	<!-- 게시글 클릭시 상세조회로 넘어가는 메소드 -->
                            <script>
					    	$(function() {
					    		
					    		
					    		
					    		$(".table-hover>tbody>tr").click(function() {
					    			
					    			<% if(!list.isEmpty()) { %>
					    			location.href = "<%= contextPath %>/adminDetail.in?ino=" + $(this).children().eq(0).text();	
					    			<% } %>
					    		});
					    		
					    	});
						    </script>
                    
							<!-- 페이징 처리 -->
	                        <nav aria-label="Page navigation example">
	                            <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
	                                
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() != 1){ %>
	                                    <a class="page-link" href="<%= contextPath %>/adminAnswer.in?currentPage=<%= pi.getCurrentPage() - 1 %>&answer=O" aria-label="Previous">
	                                        <span aria-hidden="true">&laquo;</span>
	                                        <span class="sr-only">Previous</span>
	                                    </a>
	                                    <% } %>
	                                </li>
	                                
	                                <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
	                                
	                                	<% if(p != pi.getCurrentPage()){ %>
	                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/adminAnswer.in?&currentPage=<%= p %>&answer=O"><%= p %></a></li>
	                                	<% } else{ %>
	                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 -->
	                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
	                                	<% } %>
	                              	<% } %>
	                              	
	                                <li class="page-item">
	                                    <% if(pi.getCurrentPage() != pi.getMaxPage()){%>
	                                    	<a class="page-link" href="<%= contextPath %>/adminAnswer.in?currentPage=<%= pi.getCurrentPage() + 1 %>&answer=O" aria-label="Next">
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