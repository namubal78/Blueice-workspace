<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="https://kit.fontawesome.com/6cda7ccd12.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>

        /* [샛별] 파일 받으면 이 부분 지우기 */
        div {
            border : 1px solid rgb(40, 78, 122);
            box-sizing : border-box;
        }

        /* 전체를 감싸는 wrap */
        .wrap {
            width: 98%;
            height: 1680px;
            margin : auto; /* 가운데로 좌, 우 자동 정렬*/
        }

        .wrap>div { width : 100%; }

        #navigator2 { height: 150px; }

        #content { height: 1150px;}
        #content_2>div { width: 100%;}
        #content_2_1 { height: 1%; float: left; }
        #content_2_2 { height: 98%; float: left; }
        #content_2_3 { height: 1%; float: left; }

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
        height: 50%;
        margin: auto;
        padding: 30px;
        
        border: 1px solid black;
    }
    .table {
        text-align: center;
        height: 50px;
    }
    
    tbody>tr:hover {
        cursor : pointer;
        /*
        color : white;
        background-color : lightgray;
        */
    }

    thead>tr {
       font-weight: bolder; 
       
    }
    
    #searchCategory {
        text-align: center;
    }

    td { height: 50px;}

    .admin-donation-search { float: right }
</style>
<!-- 
    * 부트스트랩
    웹 사이트를 쉽게 꾸밀 수 있게 도와주는 HTML, CSS, JS 프레임워크 (규칙성이 강한 라이브러리)
-->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- bootstrap-select 라이브러리 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/i18n/defaults-*.min.js"></script>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>1:1 문의</p>
            </div>
            <div id="header_2">
                <img src="resources/images/logo3_2.png" id="logo2">
            </div>
        </div>
        <div id="content">
            <div id="content_1"></div>
            <div id="content_2">
                <div id="content_2_1"></div>
                <div id="content_2_2">
                    <div class="outer">
                        
                    <!-- table 에 클래스를 table, table-hover 추가해주면 스타일이 바뀜 (부트스트랩) -->
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>글제목</td>
                                <td colspan="3">~~~~</td>
                                <td>작성자</td>
                                <td align="left">~~~~~</td>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!-- (tr>td*5)*3 + Enter -->
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                    <p>글내용~~~~~~~</p>

                    </div>
                    <div align="right">
                        <button type="button" class="btn btn-secondary">작성</button>
                        <button type="button" class="btn btn-secondary">수정</button>
                        <button type="button" class="btn btn-secondary">삭제</button>
                        <button type="button" class="btn btn-secondary">1:1 문의내역</button>
                   
                    </div>

                    <div class="outer">
                        
                        <!-- table 에 클래스를 table, table-hover 추가해주면 스타일이 바뀜 (부트스트랩) -->
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td colspan="4">답변내용</td>
                                    <td>작성일</td>
                                    <td>작성자</td>
                                    
                                    
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <!-- (tr>td*5)*3 + Enter -->
                                <tr>
                                    <td colspan="4">답변내용~~~~~~</td>
                                    <td>2022-10-20</td>
                                    <td>관리자</td>
                                    
                                    
                                    
                                </tr>
                            </tbody>
                        </table>
                        
    
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