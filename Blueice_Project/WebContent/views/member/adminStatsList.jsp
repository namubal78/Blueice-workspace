<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.blueice.member.model.vo.BillGraph" %>
<%
	ArrayList list = (ArrayList)request.getAttribute("list");
    ArrayList<BillGraph> list2 = (ArrayList<BillGraph>)request.getAttribute("list2");
    Double result = (Double)request.getAttribute("result");
	int result2 = (int)request.getAttribute("result2");
	int result3 = (int)request.getAttribute("result3");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계관리</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
      
        box-sizing : border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap { width: 98%; height: 1880px; margin : auto; }
    .wrap>div { width : 100%; }

    #navigator2 { height: 150px; }
    #content { height: 1350px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 20%; float: left; }
    #content_2_2 { 
        height: 60%; 
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
    #header_2>img { height: 60%; margin-left: 150px; margin-top: 70px; }

    /* content 영역 */
    #content>div { height : 100%; float : left; }
    #content_1 { width : 15%; }
    #content_2 { width : 70%; }
    #content_3 { width : 15%; }

    #content_2_2>div { width: 100%; float: left; }
    #graph_1 { height: 30%; }
    #graph_2 { height: 70%; }

    #graph_1>div { height: 100%; width: 33%; float: left; }

    #total { width: 100%; height: 60%; margin-top: 40px; float: left; border-left: solid lightgray; border-right: solid lightgray;}
    #total>div, #inin>div, #chalReply>div { float: left; }

    #totalT, #inquiryT, #chalT { height: 40%; width: 70%; }
    #totalC, #inquiryC, #chalC { height: 60%; width: 70%; }
    #icon, #inquiryI, #chalI { height: 100%; width: 30%;  margin-top: -30px; }

    #inin, #chalReply { width: 100%; height: 60%; margin-top: 40px; float: left; }
    
    #totalT>p, #inquiryT>p, #chalT>p { 
        font-size: 25px; 
        font-weight: bold; 
        margin-left: 40px;
        margin-top: 10px;
    }
   
    #totalC>p, #inquiryC>p, #chalC>p {
        font-size: 20px;
        font-weight: 300;
        margin-left: 40px;
        margin-top: 10px;
        color: #4c77ab;
    }

    .fa-solid { font-size: 50px; color: #163A66; margin-left: 30px; }

    .fa-solid:hover { opacity: 0.7; }

    #graph_2>div { height: 100%; float: left; }
    #graph_2_1 { width: 60%; }
    #graph_2_2 { width: 40%; }


</style>
</head>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>


<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>통계관리</p>
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
                    <div id="graph_1">
                        <div id="graph_1_1">
                            <div id="chalReply">
                                <div id="chalT"><p>챌린지 댓글 현황</p></div>
                                <div id="chalC"><p><%= result3 %></p></div>
                                <div id="chalI"><a href="<%= contextPath %>/comment.challenge?cno=5&currentPage=1"><i class="fa-solid fa-images"></i></a></div>
                            </div>
                        </div>
                        <div id="graph_1_2">
                            <div id="total">
                               <div id="totalT"><p>총 후원금액 현황</p></div>
                               <div id="totalC"><p>₩ <%= result2 %></p></div>
                               <div id="icon"><a href="<%= contextPath %>/temAdminlist.do?currentPage=1"><i class="fa-solid fa-hand-holding-dollar"></i></a></div>
                            </div>
                        </div>
                        <div id="graph_1_3">
                            <div id="inin">
                                <div id="inquiryT"><p>1:1 문의 답변 현황</p></div>
                                <div id="inquiryC"><p><%= result %> %</p></div>
                                <div id="inquiryI"><a href="<%= contextPath %>/adminList.in?currentPage=1"><i class="fa-solid fa-circle-info"></i></a></div>
                            </div>
                        </div>
                    </div>
                    <div id="graph_2">
                        <div id="graph_2_1">
                            <canvas id="line-chart"></canvas>
                        </div>
                        <div id="graph_2_2">
                            <canvas id="doughnut-chart"></canvas>
                        </div>
                    </div>
                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>


    <script>
        new Chart(document.getElementById("doughnut-chart"), {
            type: 'doughnut',
            data: {
            labels: ["개인", "기업", "단체"],
            datasets: [
                {
                backgroundColor: ["#315b8e", "#3e74b5","#7aa2d3"],
                data: [<%= list.get(0) %>, <%= list.get(1) %>, <%= list.get(2) %>]
                }
            ]
            },
            options: {
                responsive: true,
                plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '회원 타입별 현황',
                    font: { size: 25}
                }
                }
            }
        });

        new Chart(document.getElementById("line-chart"), {
                type: 'line',
                data: {
                    labels: [<%= list2.get(0).getMonthDate() %> + '월', <%= list2.get(1).getMonthDate() %> + '월', <%= list2.get(2).getMonthDate() %> + '월', <%= list2.get(3).getMonthDate() %> + '월', <%= list2.get(4).getMonthDate() %> + '월', <%= list2.get(5).getMonthDate() %> + '월'],
                    datasets: [{
                        backgroundColor: ["#e5ebf2"],
                        borderColor: ["#5687c3"],
                        data: [<%= list2.get(0).getSumBill() %>, <%= list2.get(1).getSumBill() %>, <%= list2.get(2).getSumBill() %>, <%= list2.get(3).getSumBill() %>, <%= list2.get(4).getSumBill() %>, <%= list2.get(5).getSumBill() %>],
                        label: '2022년',
                        fill: 'origin',
                        
                    }
                    ]},

                    options: {
                        responsive: true,
                        plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: '월별 후원 통계',
                            font: { size: 25}
                        }
                        }
                    }
            });
    </script>
</body>
</html>