<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.blueice.donation.model.vo.Donation, com.blueice.donation.model.vo.DonationRegular" %>
<%
	String way = String.valueOf(request.getAttribute("way"));
	String method = String.valueOf(request.getAttribute("method"));

	DonationRegular dr = null;
	Donation d = null;
	if(way.equals("정기")) {
		dr = (DonationRegular)request.getAttribute("dr");
	}
	else {
		d = (Donation)request.getAttribute("d");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원완료</title>
<style>

    /* 전체를 감싸는 wrap */
	.wrap {
	    width: 98%;
	    height: 1680px;
	    margin : auto; /* 가운데로 좌, 우 자동 정렬*/
	}

    /* header 영역 */
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

    
	/* content 영역 ---------------------------------------------- */
	#content { height: 1250px;} /* 세로를 100px 정도 늘렸습니다. */
	#content>div {
		height : 100%; 
		float: left;
	}
	#content_1 { width : 15%; }
	#content_2 { width : 70%; }
	#content_2>div { width: 100%; }
	#content_2_1 { height: 15%; float: left; position: relative;}
	#content_2_2 { height: 75%; float: left; }
	#content_2_3 { height: 10%; float: left; }
	#content_3 { width : 15%; }

	/* donationEnrollFrom_3.jsp 영역 ------------------------------------------------*/

    /* 부트스트랩4 h1을 감싸는 div CSS */
	#content_2_1>div {
        position: absolute;
        height: 30%;
		margin: auto;
        top: 0px;
        bottom: 0px;
        left: 0px;
        right: 0px;
	}
	
    /* #logo3 이미지를 감싸는 div 영역*/
	.container>div {
		width:50%;
        height:50%;
		margin:auto;
	}
    /* 이미지 로고 */
    .container>div>#logo3 {
        width:100%;
        height: 100%
    }
    
    /* 테이블 영역 */
    tr>th { text-align: center; }
    tr>td { padding-left: 50px; }

    /* 부트스트랩4 primary 버튼에 대한 css */
    a[class*=btn-primary] { 
        background-color: #3970B3; 
        height:50px; 
    }

    /* 홈으로, 마이페이지로 버튼에 대한 css */
    .container>#way-area { width : 100%; }
    
    /* h4에 대한 css 영역 */
    h4 { color: #163A66; }

    /* hr 가로줄에 대한 css 영역*/
	hr { border-top: 2px solid #163A66 !important; }
</style>

</head>
<body>

    <div class="wrap">

        <!-- menubar -->
        <%@ include file="../common/donation_menubar.jsp" %>
        <script>
            $(function() {
                $("#navigator_1>a").attr("data-toggle", false);
            })
        </script>


        <!-- header -->
        <div id="header">
            <div id="header_1">
                <p>후원하기</p>
            </div>
            <div id="header_2">
                <img src="<%= contextPath %>/resources/images/logo3_2.png" id="logo2">
            </div>
        </div>

        <!-- content -->
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1">
                    <div>
                        <h1 class="display-3" align="center" style="color:#3970B3 !important;">후원이 완료되었습니다.</h1>
                    </div>
                </div>
                <div id="content_2_2" style="margin-top:5px;">
                    <div class="container">
                        <h4 style="padding-left:30px;">후원신청 내역</h4>
                        <hr>
                        <div>
                            <img src="<%= contextPath %>/resources/images/logo8_1.png" alt="" id="logo3">
                        </div>
                        <br>
                        <div>
                            <table style="font-size: 20pt;">
                                <tr>
                                    <th width="150">후원 방법</th>
                                    <td width="450"><%= way %></td>
                                </tr>
                                <tr>
                                    <th>후원 금액</th>
                                    <% if(d != null) { %>
                                    	<td><%= d.getDoBill() %>원</td>
                                    <% } else { %>
                                    	<td><%= dr.getDoRegBill() %>원</td>
                                    <% } %>
                                </tr>
                                <tr>
                                    <th>결제 정보</th>
                                    <td><%= method %></td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <hr>
                        <p style="color:#3970B3; font-size: 30px; text-align:center;">
                            북극곰이 살아갈 터전을 지켜주셔서 감사합니다. <br>
                            보내주신 후원금은 소중하게 사용하겠습니다.
                        </p>
                        <hr>
                        <div class="btn-group btn-group-lg" id="way-area" style="margin-top:30px;">
                            <a href="<%= contextPath %>" class="btn btn-primary" style="width:33%;">홈으로</a>
                            <% if(way.equals("일시")) { %>
                            	<a href="<%= d.getReceiptURL() %>" target="_blank" class="btn btn-light" style="width:34%;">결제 영수증</a>
                                <a href="temList.do?currentPage=1" class="btn btn-light" style="width:33%;">나의 후원내역</a>
                            <% } else { %>
                            	<a href="list.challenge" class="btn btn-light" style="width:34%;">챌린지 페이지로</a>                     
                                <a href="regList.do?currentPage=1" class="btn btn-light" style="width:33%;">나의 후원내역</a>
                            <% } %>

                        </div>
                        
                    </div>
                </div>
            </div>
            <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>

        <!-- footer-->
        <%@ include file="../common/footerbar.jsp"%>
    </div>

</body>
</html>