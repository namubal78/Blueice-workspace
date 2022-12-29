<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.challenge.model.vo.*, java.util.ArrayList, com.blueice.common.model.vo.PageInfo "%>
<%
	Challenge ch = (Challenge)request.getAttribute("ch");

	ArrayList<ChallengeComment> chList = (ArrayList<ChallengeComment>)request.getAttribute("chList");
		
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	ChallengeComment chModal = (ChallengeComment)request.getAttribute("chModal");
		
	// System.out.println(chModal);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챌린지 댓글</title>
<style>

    div {
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 4000px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 3470px; }
    #content_2>div { width: 100%;}
    #content_2_1 { height: 5%; float: left;}
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
    #content_2 { width: 70%; }
    #content_3 { width: 15%; }


    /* 주건우님의 CSS 작성 시작 */
    #content_2_2>div { width: 100%; float: left; } /* challenge-full, chalCmt-full 가로속성 */

    #challenge-full { /* 전체 요소를 담고 있는 div */
        height: 64%;
        border: solid skyblue;
        border-radius: 45px; 
    }

    #chalCmt-full { 
        height: 35%;
        border: solid skyblue;
        border-radius: 45px;
        margin-top: 20px;
    }

    #challenge-title { height: 15%; } /* 제목을 담고 있는 div Id */

    #challenge-title>div { width: 100%; float: left; }
    #challenge-title1 { height: 70%; }
    #challenge-title2 { height: 15%; }

    #challenge-title1>p {  /* 챌린지 제목 글씨와 관련된 CSS */
        width: 100%;
        height: 100%;
        font-size: 50px;
        text-align: center;
        line-height: 105px;
        margin-top: 50px;
    }

    #challenge-title2>p {
        width: 100%;
        height: 100%;
        text-align: left;
        font-size: 18px;
        margin-left: 38px;
    }

    #challenge-content { height: 90%; } /* 챌린지 내용을 담고 있는 div Id */
    #challenge-content>div { width: 100%; float: left; } /* challenge-img, challenge-content-sm 가로 속성 */

    #challenge-img { height: 40%; width: 50%; mragin: auto; } /* 이미지를 담고 있는 div Id */

    #small-img { /* #challenge-img 안의 이미지만 담을 수 있는 div */
        width: 80%;
        height: 100%;
        margin: auto;
    }

    #challenge-content-sm { /* #challenge-content안의 글만 담을 수 있는 div */
        height: 50%;
        margin: auto;
        padding: 60px 40px;
        font-size: 20px;
        word-spacing: 7px;
        letter-spacing: 1px;
    }

    #challenge-content-sm>p {
        width: 100%;
        height: 100%;
        font-size: 23px;
    }

    #challenge-comment { height: 10%; width: 100%; }

    #comment { height: 100%; width: 80%; float: left; }
    #comment-button { height: 100%; width: 20%; float: left; }


    #chalcontent { height: 85%; width: 100%; float: left; margin: auto; }
    

    .table { /* 게시판 관련된 table */
        margin: auto;
        text-align: center;
    }

    thead { font-weight: bold; font-size: 16px;}

    table>tbody>tr{ /* 게시글 내용 위아래 수평 조절 */
        padding-top: 50px;
        line-height: 100px;
    }

    #search { /* 검색창 관련된 Id */
        width: 50%;
        height: 50px;
        float: right;
    }

    .img-sm { /* 게시글 사진 들어가는 이미지 관련된 Id */
        height: 100px;
        cursor: pointer;
    }

    tbody>tr>.click:hover { cursor: pointer; opacity: 0.5; }

    #comment-textarea{
        width: 100%;
        height: 100%;
        margin-left: 50px;
        margin-top: 17px;        
    }

    #comment{
        width: 100%;
        height: 100%;
        position: relative;
    }

    #div-button{
        width: 30%;
        display: inline;
        padding-left: 100px;
        padding-bottom: 10px;
        padding-top: 45px;
        position:absolute;
    }

    #div-textarea{
        width: 80%;
        height: 100px;
        display: inline-block;
    }

    #comment-file{
        margin-left: 50px;
        margin-top: 10px;
    }
    
	.modal-dialog-delete{
		z-index: 1050;
	}


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
                        
                        <!-- 주건우님의 작성 시작-->
                        <div id="challenge-full">
                            <div id="challenge-title">
                                <div id="challenge-title1"><p><%= ch.getChalTitle() %></p></div>
                                <div id="challenge-title2">
                                	<p style="width: 300px; margin-left: 60px; margin-top: 35px;">작성일 : <%= ch.getChalStart() %>&nbsp;&nbsp;|&nbsp;&nbsp;조회수 : <%= ch.getChalHit() %></p>
                                </div>
                                <div id="challenge-title3">
                                	<div class="form-button" style="margin-left: 1000px; width: 250px;">
                                		<% if(loginMember != null && loginMember.getMemNo() == 1){%>
                                        	<a href="<%= contextPath %>/updateForm.challenge?cno=<%= ch.getChalNo() %>"><button class="btn btn-warning">수정</button></a>
                                        	<button class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">종료</button>
                                        	<a href="<%= contextPath %>/list.challenge"><button class="btn btn-secondary">목록으로</button></a>
                                        <% } else { %>
                                        	<a href="<%= contextPath %>/list.challenge"><button class="btn btn-secondary" style="margin-left: 100px;">목록으로</button></a>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <!-- 챌린지 게시글 삭제 시 사용할 모달창 -->
                             <div class="modal" id="deleteForm">
						      <div class="modal-dialog">
						        <div class="modal-content">
						          <!-- Modal Header -->
						          <div class="modal-header">
						            <h4 class="modal-title"></h4>
						            <button type="button" class="close" data-dismiss="modal">&times;</button>
						          </div>
						          <!-- Modal body -->
						          <div class="modal-body" align="center">
						          	<b>챌린지 종료 시 복구가 불가능합니다. <br>
						          	정말로 종료 시키겠습니까? <br><br></b>
						          	<form action="<%= contextPath %>/delete.challenge?cno=<%= ch.getChalNo() %>" method="post">
						          		<button type="submit" class="btn btn-danger btn-sm" style="margin: auto;">삭제하기</button>
						          	</form>
						          </div>
						        </div>
						      </div>
						    </div>
                            
                            <hr>
                            <div id="challenge-content">
                                <div id="challenge-img">
                                	<div id="small-img"><img src="<%= ch.getTitleImg() %>" style="background-size: cover; width: 100%; height: 100%;"></div>
                                </div>
                                <div id="challenge-content-sm">
                                    <p>
                                    	<%= ch.getChalConts() %>
                                    </p>
                                </div>
                            </div>
                        </div>
                        
                        <div id="chalCmt-full">

                            <form action="insert.challengeComment?cno=<%= ch.getChalNo() %>" method="post" enctype="multipart/form-data">
                                   <% if(ch.getChalStatus().equals("N")) {%>
	                                   <div id="comment">
	                                       <div id="div-textarea">
	                                       		<textarea id="comment-textarea" name="comment" style="resize: none;" placeholder="해당 챌린지가 종료되어 댓글 등록이 불가합니다." disabled></textarea>
	                                       </div>
	                                       <div id="div-button">
	                                       		<button type="submit" class="btn btn-primary btn-lg" disabled>댓글등록</button>
	                                       </div>
	                                   </div>
		                                <div>
		                                    <div id="comment-file"><input type="file" name="upfile" required disabled></div>
		                                </div>
		                            <% } else if(loginMember == null) { %>    
		                                <div id="comment">
	                                       <div id="div-textarea">
	                                       		<textarea id="comment-textarea" name="comment" style="resize: none;" placeholder="로그인 후 이용 가능한 서비스입니다." disabled></textarea>
	                                       </div>
	                                       <div id="div-button">
	                                       		<button type="submit" class="btn btn-primary btn-lg" disabled>댓글등록</button>
	                                       </div>
	                                   </div>
		                                <div>
		                                    <div id="comment-file"><input type="file" name="upfile" required disabled></div>
		                                </div>
	                                <% } else { %>
		                                <div id="comment">
	                                       <div id="div-textarea">
	                                       		<textarea id="comment-textarea" name="comment" style="resize: none;" placeholder="댓글을 입력해주세요."></textarea>
	                                       </div>
	                                       <div id="div-button">
	                                       		<button type="submit" class="btn btn-primary btn-lg">댓글등록</button>
	                                       </div>
	                                   </div>
		                                <div>
		                                    <div id="comment-file"><input type="file" name="upfile" required accept="image/jpeg, image/gif, image/png"></div>
		                                </div>
		                           <% } %>
                            </form>
							
							
                            <div id="chalcontent">
                                <br clear="both"><br>
                                <!-- table 에 클래스를 table, table-hover 추가해주면 스타일이 바뀜 (부트스트랩) -->
                                <table class="table">
                                    <thead>
                                        <tr> 
                                            <td width="7%">댓글번호</td>
                                            <td width="15%">사진</td>
                                            <td width="50%">댓글내용</td>
                                            <td width="13%">작성자</td>
                                            <td width="15%">작성일</td>
                                        </tr>
                                    </thead>
                                    <tbody> 
										<% if(!chList.isEmpty()){ %>
											<% int count = 0; %>
											<% for(ChallengeComment chco : chList) { %>
		                                        <tr>
		                                            <td id="chalComModalDetailNo"><%= chco.getChalComNo() %></td>
		                                            <td>
		                                                <div class="img-sm">
		                                                	<img src="<%= chco.getTitleImg() %>" style="background-size: cover; width: 100%; height: 100%;">
		                                                </div>
		                                            </td>
		                                            
		                                            <% int text = chco.getChalComConts().length(); %>
														<%if (text >= 40) { %>
														     <td class="click" data-toggle="modal" data-target="#commentModal<%= count %>">
		                                            				<%= chco.getChalComConts().substring(0, 30) %>
		                                            		 </td>
														 <% } else { %>
														   <td class="click" data-toggle="modal" data-target="#commentModal<%= count %>">
		                                            				<%= chco.getChalComConts() %>
		                                            		</td>
														<%	} %>
													
		                                            
		                                            
		                                            <td><%= chco.getMemNo() %></td>
		                                            <td><%= chco.getChalComDate() %></td>
		                                        </tr>
		                                        
		                                        <!-- 게시글 클릭 시 나오는 모달창 -->
												<div class="modal" id="commentModal<%= count++ %>" style="margin-top: 50px; margin-left: -130px;">
												  <div class="modal-dialog">
												    <div class="modal-content" style=" width: 700px; height: 800px;">
												
												      <!-- Modal header -->
												      <div class="modal-header">
												        <div style="width: 550px; height: 350px; margin: auto;">
												        	<img src="<%= chco.getTitleImg() %>" style="background-size: cover; width: 100%; height: 100%;">
												        </div>
												      </div>
												      
												      <!-- Modal body -->
												      
												      <div class="modal-body">
												      	<p class="modal-title" align="center">작성일 : <%= chco.getChalComDate() %> &nbsp;&nbsp;|&nbsp;&nbsp;작성자 : <%= chco.getMemNo() %></p>
												      	<br>
												        <p class="modal-title" style="font-size: 15px;">
												        	<div style="width: 650px; margin: auto;"><%= chco.getChalComConts() %></div>
												        </p>
												      </div>
												
												      <!-- Modal footer -->
												      <div class="modal-footer">
													      <div style="margin: auto;">
													     	 <% if(loginMember != null && loginMember.getMemName().equals(chco.getMemNo())){%>
														        <button type="button" class="btn btn-danger" data-dismiss="modal" style="margin-right: 7px;">
														        	닫기
														        </button>
														        <a href="delete.challengeComment?cno=<%= ch.getChalNo() %>&ccno=<%= chco.getChalComNo() %>">
															        <button type="button" class="btn btn-danger" style="margin-left: 7px;">
															        	삭제
															        </button>
														        </a>
														    <% } else {%>
														    	<button type="button" class="btn btn-danger" data-dismiss="modal" style="margin: auto;">
														        	닫기
														        </button>
														    <% } %>
													        </div>
												      </div>
												
												    </div>
												  </div>
												</div>
		                                        
                                        	<% } %>
                                        <% } else{ %>
                                        	<tr>
                                        		<td colspan=5 style="padding-top: 150px;"> 등록된 댓글이 없습니다. </td>
                                        	</tr>
                                        <% } %>
                                       
                                        
                                          
                                    </tbody>
                                </table>

                                
								
                            
                                <!-- 페이징 처리 -->
                                <% if(!chList.isEmpty() || chList.isEmpty() && pi.getCurrentPage() > 1) { %>
		                        <nav aria-label="Page navigation example">
		                            <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
		                                
		                                <li class="page-item">
		                                	<% if(pi.getCurrentPage() != 1){ %>
		                                    <a class="page-link" href="<%= contextPath %>/comment.challenge?cno=<%= ch.getChalNo() %>&currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
		                                        <span aria-hidden="true">&laquo;</span>
		                                        <span class="sr-only">Previous</span>
		                                    </a>
		                                    <% } %>
		                                </li>
		                                
		                                <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
		                                
		                                	<% if(p != pi.getCurrentPage()){ %>
		                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/comment.challenge?cno=<%= ch.getChalNo() %>&currentPage=<%= p %>"><%= p %></a></li>
		                                	<% } else{ %>
		                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 -->
		                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
		                                	<% } %>
		                              	<% } %>
		                              	
		                                <li class="page-item">		
		                                	<% if(pi.getMaxPage() == pi.getCurrentPage()) { %>
		                                	                                    
		                                    <% } else if(pi.getCurrentPage() != pi.getMaxPage()){%>
		                                    	<a class="page-link" href="<%= contextPath %>/comment.challenge?cno=<%= ch.getChalNo() %>&currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
			                                        <span aria-hidden="true">&raquo;</span>
			                                        <span class="sr-only">Next</span>
		                                    	</a>
		                                    <% } %>
		                                </li>
		                                
		                            </ul>
		                        </nav>
		                        <% } else if(chList.isEmpty() && pi.getCurrentPage() == 1) { %>
		                        
		                        <% } %>
                        </div>
                        <!-- 건우 영역 끝 -->
                        

					</div>
	                <div id="content_2_3"></div>
	            </div>
	            <div id="content_3"></div>
	        </div>
	        <%@ include file="../common/footerbar.jsp" %>
	    </div>
	    
	     <script>
	      	$(function(){
	      		
	      		$("#chalcontent>table>tbody>tr>.click").click(function() {
	      			
	      			// let nono1 = document.getElementById("#chalComModalDetailNo").innerText();
	      			// let nono1 = $("#chalComModalDetailNo").text();
	      			//let commentNo = $(this).parent().children().eq(0).text();
	      			
	      			//let src = $(this).parents(".modal-header").children().children("img");
	      			//img.src = ""
	      			// console.log(commentNo);
	      			
	      			//let nono2 = document.getElementById('chalComModalDetailNo').innerText;
	      		
	      			// console.log(nono2);
	      		});
	      	});
	    </script>

</body>
</html>