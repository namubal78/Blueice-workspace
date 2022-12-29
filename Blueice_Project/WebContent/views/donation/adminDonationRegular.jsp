<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.common.model.vo.PageInfo, com.blueice.donation.model.vo.*, com.blueice.member.model.vo.Member" %>
<% 
	ArrayList<DonationRegular> list = (ArrayList<DonationRegular>)request.getAttribute("list");
    ArrayList<Member> list2 = (ArrayList<Member>)request.getAttribute("list2");
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    String selection = request.getAttribute("selection") == null ? "" : (String)request.getAttribute("selection");
    String searchText = request.getAttribute("searchText") == null ? "" : (String)request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정기 후원 관리</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 98%;
        height: 1880px;
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
    #donabtn { height: 20%; float: left; }

    #donabtn p {
        font-size: 18px;
        padding-left: 20px;
    }

    #donaText { height: 10%; float: left; }
    #donaTable { height: 90%; float: left; padding: 50px; }

    #donaText>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }
    /*.table>tbody>tr:hover { cursor: pointer ;}*/
    tbody>tr { line-height: 35px;}
    a[class*=btn-primary] { background-color: #3970B3; }

</style>
</head>
<body>

<div class="wrap">
    <%@ include file="../common/menubar.jsp" %>
    <div id="navigator2"></div>
    <div id="header">
        <div id="header_1">
            <p>후원 관리</p>
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
                <form action="regAdminlist.do?currentPage=1" method="post">
                <div id="myDona">
                    <div id="donaText"><p>정기 후원 관리</p>
                        <div id="donabtn">
                        </div>
                    </div>

                    <div id="donaTable">
                        <div class="admin-donation-search" style="z-index:100;">
                            <div class="form-group row" style="margin-left:390px">
                                <div style="margin-right:10px;">
                                    <!-- 검색 카테고리 설정 칸 -->
                                    <select class="selectpicker show-tick" name="selection" id="selectBox" data-style="btn-primary" data-width="110px" onchange="changeFunc();">
                                            <option value="name" <%= selection == null || selection.equals("name")? "selected":"" %> selected>이름</option>
                                            <option value="memId" <%= selection.equals("memId")? "selected":"" %>>아이디</option>
                                            <option value="type" <%= selection.equals("type")? "selected":"" %>>회원 유형</option>
                                        <!-- <option value="period">후원 기간</option> -->
                                    </select>
                                </div>
    
                                <!-- <input id="selectDate" type="date" name="dateIn" style="display: none;" onclick="return false;"> -->
                                <!-- 날짜 선택 태그의 디폴트 상태: none -->
                                <select name="typeSelection" data-style="btn-light" style="display: none; width:110px;">
                                    <option value="personal">개인</option>
                                    <option value="group">단체</option>
                                    <option value="company">기업</option>
                                </select>
                        
                                <!-- 검색내용 입력 칸 -->
                                <div class="input-group" style="width: 250px;">
                                    <input type="text" name="searchText" class="form-control" placeholder="개인/기업/단체/담당자 이름" value="<%= searchText %>">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" type="submit">
                                        <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <script>
                    
                            function changeFunc() { 
                                let selectBox = document.getElementById("selectBox"); // selectBox : 검색 카테고리의 select 아이디  
                                let selectedValue = selectBox.options[selectBox.selectedIndex].value; // selectedValue : 선택된 option 의 value 
                                
                                /*
						        if(selectedValue == "period") { // value값이 period인 option (후원기간)을 선택했다면
                                    $("select[name=typeSelection]").css("display", "none");
                                    $(".form-control").css("display", "none");
                                    $(".form-control").val('');
                                    $(".row").css("margin-left", "430px");
						        	document.getElementById('selectDate').style.display = 'block'; // 날짜 선택 태그 표시 
						        } else*/ 
                                if(selectedValue == "type") {
                                    $(".form-control").css("display", "none");
                                    $(".form-control").val('');
                                    $("select[name=typeSelection]").css("display", "flex");
                                    $(".row").css("margin-left", "435px");
                                } else { // 이름을 선택한 경우(default)
                                    console.log($("#selectDate").val());
                                    $(".form-control").css("display", "block");
                                    $(".row").css("margin-left", "390px");
                                    $("select[name=typeSelection]").css("display", "none");
						        }
                            }
                            
                            /*
                            $(function(){
                                $('input[name="dateIn"]').daterangepicker();
                                console.log($("select").val());
                            });
                            */
                        </script>


                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>회원번호</td>
                                    <td>이름</td>
                                    <td>회원유형</td>
                                    <td>전화번호</td>
                                    <td>아이디</td>
                                    <td>후원 시작일</td>
                                    <td>후원 횟수</td>
                                    <td>총 후원 금액</td>
                                    <td>기부금 영수증</td>
                                </tr>
                            </thead>
                            <tbody>
                            <% for(int i=0; i<list.size(); i++) { %>
                                <tr>
                                    <td><%= list.get(i).getRegDonator() %></td>
                                    <% if(list2.get(i).getMemNo() == Integer.parseInt(list.get(i).getRegDonator())) {%>
                                        <td>
                                        <% if(list2.get(i).getMemType().equals("1")) { %>
                                            <%= list2.get(i).getMemName() %>
                                        <% } else if(list2.get(i).getMemType().equals("2")) { %>
                                            <%= list2.get(i).getGroupName() %>
                                        <% } else { %>
                                            <%= list2.get(i).getCompanyName() %>
                                        <% } %>
                                        </td>
                                        <td>
                                        <% if(list2.get(i).getMemType().equals("1")) { %>개인
                                        <% } else if(list2.get(i).getMemType().equals("2")) { %>단체
                                        <% } else { %>기업
                                        <% } %>
                                        </td>
                                        <td><%= list2.get(i).getPhone() %></td>
                                        <td><%= list2.get(i).getMemId() %></td>
                                    <% } %>
                                    <td><%= list.get(i).getDoRegDate() %></td>
                                    <td><%= list.get(i).getDoRegCount() %></td>
                                    <td><%= list.get(i).getDoRegBill() %></td>
                                    <td>
                                        <a href="oneReceiptList.do?type=1&dId=<%= list.get(i).getDoRegId() %>" target="_blank" class="btn btn-primary">기부금영수증</a> 
                                    </td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                        
							<!-- 페이징 처리 -->
	                        <nav aria-label="Page navigation example">
	                            <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
	                                
	                                <li class="page-item">
	                                	<% if(pi.getCurrentPage() != 1){ %>
	                                    <a class="page-link" href="<%= contextPath %>/regAdminlist.do?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
	                                        <span aria-hidden="true">&laquo;</span>
	                                        <span class="sr-only">Previous</span>
	                                    </a>
	                                    <% } %>
	                                </li>
	                                
	                                <% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
	                                
	                                	<% if(p != pi.getCurrentPage()){ %>
	                                		<li class="page-item"><a class="page-link" href="<%= contextPath %>/regAdminlist.do?currentPage=<%= p %>"><%= p %></a></li>
	                                	<% } else{ %>
	                                		<!-- 현재 내가 보고 있는 페이지일 경우 버튼 클릭 안됨 --> 
	                                		<li class="page-item"><a class="page-link"><%= p %></a></li>
	                                	<% } %>
	                              	<% } %>
	                              	
	                                <li class="page-item">
	                                    <% if(pi.getCurrentPage() != pi.getMaxPage()){%>
	                                    	<a class="page-link" href="<%= contextPath %>/regAdminlist.do?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
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