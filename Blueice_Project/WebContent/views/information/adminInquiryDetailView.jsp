<%@page import="com.sun.org.apache.bcel.internal.generic.IADD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.information.model.vo.Inquiry"%>
<%
	Inquiry adminI = (Inquiry)request.getAttribute("i");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
<style>
    
    div {
       /* border : 1px solid rgb(40, 78, 122); */ 
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

    #news-title { width: 100%; height: 15%; }

    #news-title1 { width: 100%; height: 60%; float: left; }
    #news-title2 { width: 100%; height: 20%; float: left; }

    #news-content { width: 100%; height: 80%; }
    #news-comment { width: 100%; height: 40%; }

    #news-comment>div { width: 100%; float: left; }
    #news-content_1 { height: 15%; }
    #news-content_2 { height: 85%; }

    #news-content-sm {
        width: 90%;
        height: 60%;
        margin: auto;
        padding-top: 35px;
        font-size: 20px;
        word-spacing: 7px;
        letter-spacing: 1px;
    }

    #news-content-sm>p {
        width : 100%;
        height : 100%;
    }


    #news-content_2>div { width: 100%; float: left; }
    #inquiryReply { height: 40%; }

    #inquiryReply>p { 
        padding-left: 60px; 
        padding-right: 60px;
        font-size: 17px; 
        font-weight: bold; 
    }

    #replyy { height: 40%; }
    #replyy>div { height: 100%; float: left; }
    #answer { width: 70%; margin-left: 130px; }
    #adminReply { width: 10%; margin-top: 25px; margin-left: 5px; }

    #news-content_1>p {
        width: 100%;
        height: 100%;
        font-size: 25px;
        padding-left: 60px; 
    }

    #news-content_2>p {
        width: 100%;
        height: 100%;
        font-size: 18px;
        padding-left: 60px;
        padding-right: 60px;
    }

    

    #news-title1>p {  /* 뉴스 제목 글씨와 관련된 CSS */
        width: 100%;
        height: 100%;
        font-size: 30px;
        text-align: center;
        line-height: 135px;
    }

    #news-title2>p {
        width: 100%;
        height: 100%;
        text-align: left;
        font-size: medium;
        margin-left: 60px;
    }



</style>
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">

    <div class="wrap">
	    <%@ include file="../common/menubar.jsp" %>
		     <div id="navigator2"></div>
		     <div id="header">
		         <div id="header_1">
		             <p>1:1 문의</p>
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
		             
		             
					 	    <div id="news-full">
						        <div id="news-title">
                                    <div id="news-title1"><p><%= adminI.getInquiryTitle() %></p></div>
                                    <div id="news-title2">
                                        <p>작성일 : <%= adminI.getInquiryDate() %> &emsp;|&emsp;작성자 : <%= adminI.getMemNo() %></p>
                                    </div>

                                    <div class="form-button" style="margin-left: 1200px;">
                                    	<button class="btn btn-secondary" onclick="location.href='<%= contextPath %>/adminList.in?currentPage=1'">목록으로</button>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">삭제</button>
                                    </div>

						        </div>

                                <hr>

						        <div id="news-content">
						            <div id="news-content-sm">
                                        <p>
                                           <%= adminI.getInquiryConts() %>
                                        </p>
                                    </div>

                                    <hr>

									<!-- 답변 작성전 -->
									<% if(adminI.getAnswerConts() == null) { %>
                                    <div id="news-comment">
                                        <div id="news-content_1"><p>1:1 문의 답변</p></div>
                                        <div id="news-content_2">
                                            <div id="inquiryReply">
                                                <p>
							                                                    답변일&emsp;:&emsp; <br>
							                                                    답변내용 <br>
                                                </p>
                                            </div>
                                            
                                            
                                            <form id="answer-form" action="<%= contextPath %>/adminInsert.in" method="post">
                                            <div id="replyy">
                                                <div id="answer"><textarea class="form-control" name="answerConts" id="answerConts" cols="115" rows="3" style="resize: none;"></textarea></div>
                                                <input type="hidden" name="ino" value="<%= adminI.getInquiryNo() %>">
                                                <div id="adminReply"><button class="btn btn-primary" type="submit">답변하기</button></div>
                                            </div>
                                           </form> 
                                        </div>
                                    </div>
                                    
                                    <% } else { %>
                                    
                                    <!-- 답변 작성후 -->
                                    <div id="news-comment">
                                        <div id="news-content_1"><p>1:1 문의 답변</p></div>
                                        <div id="news-content_2">
                                            <div id="inquiryReply">
                                                <p>
							                                                    답변일&emsp;:&emsp; <%= adminI.getAnswerDate() %> <br>
							                       <%= adminI.getAnswerConts() %> <br>
                                                </p>
                                            </div>
                                            
                                            
                                            <form id="answerUpdate-form" action="<%= contextPath %>/adminUpdate.in" method="post">
                                            <div id="replyy">
                                                <div id="answer"><textarea class="form-control" name="answerUpdateConts" cols="115" rows="3" style="resize: none;"></textarea></div>
                                                <input type="hidden" name="updateIno" value="<%= adminI.getInquiryNo() %>">
                                                <div id="adminReply">
                                                <button class="btn btn-warning" type="submit">수정하기</button>
                                                <br>
                                               	</div>
                                            </div>
                                            </form>
                                        </div>
                                    </div>                 
                                    <% } %>
                                    
                                    
						        </div>
						    </div>	    
					
							<!-- 1:1문의삭제용 모달창 -->
						    <!-- The Modal -->
						    <div class="modal" id="deleteForm">
						      <div class="modal-dialog">
						        <div class="modal-content">
						          <!-- Modal Header -->
						          <div class="modal-header">
						            <h4 class="modal-title">1:1문의내역 삭제</h4>
						            <button type="button" class="close" data-dismiss="modal">&times;</button>
						          </div>
						          <!-- Modal body -->
						          <div class="modal-body" align="center">
						          	<b>1:1문의내역 삭제후 복구가 불가능합니다. <br>
						          	정말로 삭제하시겠습니까? <br><br></b>
						          	<form action="<%= contextPath %>/deleteInquiry.in?ino=<%= adminI.getInquiryNo() %>" method="post">
						          		<button type="submit" class="btn btn-danger btn-sm" style="margin: auto;">삭제하기</button>
						          	</form>
						          </div>
						        </div>
						      </div>
						    </div>
		    
					</div>
	                <div id="content_2_3"></div>
	            </div>
	            <div id="content_3" style=""></div>
	        </div>
	        <%@ include file="../common/footerbar.jsp" %>
	    </div>
	    
	<!-- 뒤로가기 버튼 막기 -->   
	<script type="text/javascript">
        window.history.forward();
        function noBack(){window.history.forward();}
    </script>

</body>
</html>







































