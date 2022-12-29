<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueice.challenge.model.vo.*"%>
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
		height: 1500px;
		margin : auto; /* 가운데로 좌, 우 자동 정렬*/
	}

	.wrap>div { width: 100%; }

	#navigator2 { height: 150px; }

	#content { height: 965px; }
	#content_2>div { width: 100%;}
	#content_2_1 { height: 15%; float: left;}
	#content_2_2 { 
		height: 70%; 
		float: left;
		margin: auto;
        border: solid skyblue;
        border-radius: 45px;
	}

	#content_2_3 { height: 15%; float: left;}

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


	#main { width: 100%; height: 100%; }

    #notice { /* 글작성, 버튼을 감싸고 있는 div */
        width: 100%;
        height: 100%;
        margin: auto;
    }

	#enroll-form { width: 100%; height: 100%; }

    table { /* 게시판 */
        width: 90%;
        height: 90%;
        border-collapse: separate;
	    border-spacing: 0 20px;
        text-align: center;
		margin-left: 70px;
    }

	table th { width: 10%; font-size: 15px; }

	#btns { height: 10%;}

    #enroll-form>table input[type=text], #enroll-form>table textarea { /* 제목과 내용을 입력할 칸 */
        width: 96%;
        box-sizing: border-box;
    }

    input[type=text] { /* 제목을 입력할 칸 */
        height: 35px;
    }




        
</style>

</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">

 	
	<div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
	     <div id="navigator2"></div>
	     <div id="header">
	         <div id="header_1">
	             <p>리워드</p>
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

					<div id="main">
                        <div id="notice">
                            <form id="enroll-form" action="<%= contextPath %>/update.re?rno=<%= r.getChalRewardNo() %>" method="post" enctype="multipart/form-data">
								<input type="hidden" name="rno" value="<%= r.getChalRewardNo() %>">
                                <table>
                                    <tr>
                                        <th>제목</th>
                                        <td><input type="text" name="chalRewardTitle" class="form-control" value="<%= r.getChalRewardTitle() %>" required></td>
                                    </tr>

                                    <br>

                                    <tr>
                                        <th>내용</th>
                                        <td>
                                            <textarea name="chalRewardConts" class="form-control" rows="19" required style="resize:none"><%= r.getChalRewardConts() %></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>현재 첨부파일</th>
                                        <td>
                                        <% if(ra != null){ %>
                                        	<p style="padding-right: 920px; padding-top: 15px;"><%= ra.getReOriginName() %></p>
	                                        <input type="hidden" name="ReOriginFileNo" value="<%= ra.getReFileNo() %>">
	                                        <input type="hidden" name="ReOriginFileName" value="<%= ra.getReChangeName() %>">
                                        <% } %>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<th>변경할 첨부파일</th>
                                    	<td>
											<input type="file" name="reUpfile" style="margin-right: 700px;">				                                    	
										</td>
                                    </tr>
                                </table>

                                <div id="btns" align="center">
                                    <button type="submit" class="btn btn-primary">수정하기</button>
                                    <button type="button" class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
                                </div>
                    
                            </form>
                        </div>

                    </div>
	             
    
				</div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>
    
    
    <script type="text/javascript">
        window.history.forward();
        function noBack(){window.history.forward();}
    </script>
	  
	
	
</body>
</html>