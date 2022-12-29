<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.challenge.model.vo.Reward" %>
<%
	// request 에 담았던 list 키값 뽑아오기 (== 리워드 전체 리스트 조회한 결과물)
	ArrayList<Reward> list = (ArrayList<Reward>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리워드</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1480px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }

    #content { height: 950px;}
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

    .input-group{ float: right; }

    .table { 
        text-align: center; 
        height: 50px; 
    }

    .table:hover { cursor: pointer; }

    thead>tr { font-weight: bolder; font-size: medium; }
    


</style>
</head>
<body>

    <div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>리워드</p>
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

                            <!-- 글작성, 삭제 버튼 -->
                            <% if(loginMember != null && loginMember.getMemId().equals("blueice123")) { %>
	                            <div class="form-button" style="margin-left:1130px">
	                                <button type="button" onclick="location.href='<%= contextPath %>/enrollForm.re'" class="btn btn-primary">글작성</button>
	                            </div>
                            <% } %>

                        </div>
                        <div id="chalTable">
                            <table id="list-area" class="table table-hover">
                                <thead>
                                    <tr>
                                        <td>제목</td>
                                        <td>조회수</td>
                                        <td>작성일</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if(list.isEmpty()) { %>
                                    	<tr>
                                    		<td colspan="3">존재하는 리워드가 없습니다.</td>
                                    	</tr>
                                    <% } else { %>
                                    	<!-- 리스트가 비어있지 않을 경우 : 조회된 공지사항이 한건이라도 있을 경우 -->
                                    	<% for(Reward r : list) { %>
                                    		<tr>
                                                <input type="hidden" name="rno" value="<%= r.getChalRewardNo() %>">
                                    			<td><%= r.getChalRewardTitle() %></td>
                                    			<td><%= r.getChalRewardHit() %></td>
                                    			<td><%= r.getChalRewardDate() %></td>
                                    		</tr>
                                    	<% } %>
                                    <% } %>
                                </tbody>
                            </table>

                            <script>
                                $(function() {
                                    $("#list-area>tbody>tr").click(function() {
    
                                        location.href = "<%= contextPath %>/detail.re?rno=" + $(this).children().eq(0).val();
                                    });
                                });
                            </script>
                        
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