<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.common.model.vo.PageInfo, com.blueice.donation.model.vo.DonationRegular, com.blueice.member.model.vo.Member" %>
<% 
    ArrayList<DonationRegular> list = (ArrayList<DonationRegular>)request.getAttribute("list");
    ArrayList<Member> list2 = (ArrayList<Member>)request.getAttribute("list2");
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    int listCount = Integer.parseInt(String.valueOf(request.getAttribute("listCount")));
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

    #content { height: 1450px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 10%; float: left; }

    #content_2_2 { 
        height: 75%; 
        float: left; 
        margin: auto;
        border: solid skyblue;
        border-radius: 45px; 
        padding: 30px 30px;
    }

    #content_2_3 { height: 20%; float: left; }

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
    #content>div { height : 100%; float : left; }
    #content_1 { width : 15%; }
    #content_2 { width : 70%; }
    #content_3 { width : 15%; }

    #myDona { width: 100%; height: 100%; }
    #myDona>div { width: 100%; }

    #donaText { height: 20%; float: left; }
    #donaTable { height: 80%; float: left; padding: 70px; }

    #donaText>div { width: 100%; }
    #donaText_1 { height: 60%; float: left; }
    #donabtn { height: 40%; float: left; }

    #donaText_1_1>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    #donaText_1_2>p {
        font-size: 20px;
        text-align: center;
    }


    #donabtn p {
        font-size: 18px;
        padding-left: 20px;
    }


    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }
    
    #searchCategory { text-align: center; }

    #reward {
        margin: auto;
        display: block;
        width: 100%;
        background-size: cover;
        object-fit: cover;
    }

    tbody>tr { line-height: 35px;}
    .table>tbody>tr:hover { cursor: pointer ;}

    .btn-primary { background-color: #3970B3 !important; }


</style>

<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>



</head>
<body>

    <div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>마이페이지</p>
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
                    <div id="myDona">
                        <div id="donaText">
                            <div id="donaText_1">
                                <div id="donaText_1_1"><p>나의 정기 후원 내역</p></div>
                                <div id="donaText_1_2"><p>후원 내역을 누를 시 해당 내역의 결제 영수증 출력 페이지로 넘어가고<br>
                                영수증 버튼을 누를 시 기부금 영수증 페이지로 이동합니다.<br>
                                일시 후원내역은 마이페이지에서 이동하실 수 있습니다.</p></div>
                            </div>
                            <br><br>
                            <div id="donabtn">

                                <div class="form-button" style="margin-left:980px">
                                    <p> 후원금 영수증 출력</p>
                                    <a href="yearReceiptList.do?type=1" target="_blank" class="btn btn-primary">금년 내역</a>
                                    <a href="fullReceiptList.do?type=1" target="_blank" class="btn btn-success">모든 내역</a>
                                </div>

                            </div>
                        </div>
                        <div id="donaTable">

                            <!-- 날짜 검색 버튼, 영수증 출력 버튼 추가 -->

                            <table class="table table-hover">
                                <form action="">
                                    <thead>
                                        <tr>
                                            <td>후원 횟수</td>
                                            <td>후원자명</td>
                                            <td>총 후원 금액</td>
                                            <td>후원 시작일</td>
                                            <td>
                                                기부금 영수증 버튼
                                            </td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% if(list.isEmpty()) { %>
                                        <!-- 조회된 후원내역이 없을 경우 -->
										<tr>
                                            <td colspan="6">존재하는 후원 내역이 없습니다.</td>
                                        </tr>
                                    <% } else { %>
	                                    <% for(DonationRegular dr : list) { %>
	                                        <tr>
	                                            <td><%= dr.getDoRegCount() %></td>
	                                            <td><%= loginMember.getMemName() %></td>
	                                            <td><%= dr.getDoRegBill() %></td>
	                                            <td><%= dr.getDoRegDate() %></td>
	                                            <td width="15%">
	                                            	<a href="oneReceiptList.do?type=1&dId=<%= dr.getDoRegId() %>" class="btn btn-primary">기부금영수증</a> 
	                                            </td>
	                                            <td id="url" style="display:none;"><%= dr.getReceiptRegURL() %></td>
	                                            <input type="hidden" name="doId" value="<%= dr.getDoRegId() %>">
	                                        </tr>
	                                    <% } %>
	                                <% } %>
                                    </tbody>
                                </form>
                            </table>

                            <script>
                                $(function() {
                                    $(".table>tbody>tr").click(function() {
                                        let url = $(this).children().eq(5).text();
                                        location.href=url;
                                    });
                                });
                            </script>
                        
                            <!-- 페이징 처리 -->
                            <nav aria-label="Page navigation example">
                                <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
                                    
                                    <li class="page-item">
                                        <% if(pi.getCurrentPage() != 1){ %>
                                        <a class="page-link" href="<%= contextPath %>/temList.do?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <% } %>
                                    </li>
                                    
                                    <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
                                    
                                        <% if(p != pi.getCurrentPage()){ %>
                                            <li class="page-item"><a class="page-link" href="<%= contextPath %>/temList.do?currentPage=<%= p %>"><%= p %></a></li>
                                        <% } else{ %>
                                            <!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 --> 
                                            <li class="page-item"><a class="page-link"><%= p %></a></li>
                                        <% } %>
                                      <% } %>
                                      
                                    <li class="page-item">
                                        <% if(pi.getCurrentPage() == 1) { %>
                                        
                                        <% } else if(pi.getCurrentPage() != pi.getMaxPage()){%>
                                            <a class="page-link" href="<%= contextPath %>/temList.do?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
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


    <!-- 영수증 체크박스 -->
    <script>
        $(document).ready(function() {
			$("#checkAll").click(function() {
				if($("#checkAll").is(":checked")) $("input[name=check]").prop("checked", true);
				else $("input[name=check]").prop("checked", false);
			});
			
			$("input[name=check]").click(function() {
				var total = $("input[name=check]").length;
				var checked = $("input[name=check]:checked").length;
				
				if(total != checked) $("#checkAll").prop("checked", false);
				else $("#checkAll").prop("checked", true); 
			});
		});
    </script>




</body>
</html>