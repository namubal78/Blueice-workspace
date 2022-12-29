<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.information.model.vo.Faq" %>
<%
	ArrayList<Faq> list = (ArrayList<Faq>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
	/* [샛별] 파일 받으면 이 부분 지우기 */
	div {
	    /* border : 1px solid rgb(40, 78, 122); */
	    box-sizing : border-box;
	}
	
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
	
	.outer {
		width: 100%;
		height: 100%;
		padding: 50px;
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
	}
	
	table {
		width: 100%;
		height: 100%;
	  
	}
	
	tbody {
		width: 100%;
		height: 100%;
        
	}
	
	/* 질문 스타일 */
	.question {
		cursor: pointer;
		color: #163A66;
		font-size: 28px;
		font-weight: bold;
	}
	
    .lowHr {
        border-bottom: 2px solid lightgrey;

    }
	
	/* 답변 스타일 */
	td>p {
		margin: 10px;
		padding: 10px;
		border-radius : 20px;
		box-sizing : border-box;
		display: none;
		/* background-color: rgb(200, 239, 252); */
		line-height: 35px;
		font-size: 17px;
		height: 150px;
	}
	
</style>
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
    <script type="text/javascript">
        window.history.forward();
        function noBack(){window.history.forward();}
    </script>
	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>FAQ</p>
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
                    <div class="outer">
                        
                        <!-- [영섭작업영역시작] -->
				        <table align="center" class="list-area">
				            <!-- faq 전체조회용 향상된 for 문 -->
				            <tbody>
				            	<% int count = 0; %>
				                <% for(Faq f : list) { %>
				                	<tr class="lowHr">
				                		<td class="question"> Q.  <%= f.getFaqQuestion() %></td> <!-- 질문 -->
				                		
				                		<!-- 관리자일 때만 보이는 삭제 버튼 -->
				                		<% if(loginMember != null && loginMember.getMemId().equals("blueice123")){ %>
					                		<td>
	                                            <input type="button" class="btn btn-danger btn-sm" value="삭제" data-toggle="modal" data-target="#deleteForm<%= count %>">
												<div class="modal" id="deleteForm<%= count++ %>">
													<div class="modal-dialog">
														<div class="modal-content">
															<!-- Modal Header -->
															<div class="modal-header">
																<h4 class="modal-title"></h4>
																<button type="button" class="close" data-dismiss="modal"></button>
															</div>
															<!-- Modal body -->
															<div class="modal-body" align="center">
																<b>
																	FAQ 삭제 후 복구가 불가능합니다.<br>
																	정말로 삭제하시겠습니까?<br><br>
																</b>
																<form action="<%= contextPath %>/faqDelete.in?fno=<%= f.getFaqNo() %>" method="post">
																	<button type="submit" class="btn btn-danger btn-sm" style="margin: auto;">삭제하기</button>
																</form>
																<script>
																	$(function() {
																		console.log(<%= f.getFaqNo() %>);
																	})
																	
																</script>
															</div>
														</div>
													</div>
												</div>
	                                        </td>
                                        <% } else {%>
                                        	<td></td>
                                       	<% } %>
				                	</tr>
				                	<tr>                                        
			                			<td colspan="2"><p> A.  <%= f.getFaqConts() %></p></td> <!-- 답변 -->
			                		</tr>
				                <% } %>
				            </tbody>
				        </table>
				        
				        <script>
				        	// 질문 클릭시 아코디언 함수
                            $(function() {
                                $(".question").click(function() {

                                	// 답변 변수 지정
                                    let $answer = $(this).parent().next().children().children();

                                	// 답변 스타일 none if 문
                                    if($answer.css("display") == "none") { // 답변 접혀있을 때

                                        $(this).parent().nextAll("tr").children().children("p").slideUp(100); // 아래에 다른 답변 접기
                                        $(this).parent().prevAll("tr").children().children("p").slideUp(100); // 위에 다른 답변 접기
                                        $answer.slideDown(100); // 답변 펴기
                                    } else {
                                        $answer.slideUp(100); // 답변 다시 접기
                                    }
                                });
                            });
                        </script>
		        		<!-- [영섭작업영역끝] -->
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