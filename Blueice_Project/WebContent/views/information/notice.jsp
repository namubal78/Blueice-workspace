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
        margin: auto;
        padding: 100px;
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
                <p>공지사항</p>
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
                        <div class="admin-donation-search">
                            <div class="form-group row">
                                
                        
                                  <!-- Another variation with a button -->
                                <div class="input-group" style="width: 250px; margin-right: 30px;">
                                    <input type="text" class="form-control" placeholder="검색어를 입력하세요">
                                    <div class="input-group-append">
                                      <button class="btn btn-secondary" type="button">
                                        <i class="fa fa-search"></i>
                                      </button>
                                    </div>
                                  </div>
                                  <div style="margin-right : 30px;">
                                    <button type="button" class="btn btn-secondary">전체보기</button>
                                  </div>
                            </div>
                        </div> 
                    <!-- table 에 클래스를 table, table-hover 추가해주면 스타일이 바뀜 (부트스트랩) -->
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>번호</td>
                                <td colspan="3">글제목</td>
                                <td>등록일</td>
                                <td>조회수</td>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!-- (tr>td*5)*3 + Enter -->
                            <tr>
                                <td>1</td>
                                <td colspan="3">개인정보처리방침 변경 안내</td>
                                <td>2022-10-20</td>
                                <td>100</td>
                                
                            </tr>
                            <tr>
                                <td>2</td>
                                <td colspan="3">시스템 개선 및 점검 안내</td>
                                <td>2022-10-19</td>
                                <td>200</td>
                                
                            </tr>
                            <tr>
                                <td></td>
                                <td colspan="3"></td>
                                <td></td>
                                <td></td>
                                
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
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
                   
                    <!-- 페이징 처리 -->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="justify-content: center;"> <!-- justify-content: center; : 페이징 가운데 정렬-->
                          <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                              <span aria-hidden="true">&laquo;</span>
                              <span class="sr-only">Previous</span>
                            </a>
                          </li>
                          <li class="page-item"><a class="page-link" href="#">1</a></li>
                          <li class="page-item"><a class="page-link" href="#">2</a></li>
                          <li class="page-item"><a class="page-link" href="#">3</a></li>
                          <li class="page-item"><a class="page-link" href="#">4</a></li> <!-- 해당 코드 복붙해서 이어붙이면 (5,6,7,8..) 페이지수 늘어남 -->
                          <li class="page-item"><a class="page-link" href="#">5</a></li>
                          <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span>
                              <span class="sr-only">Next</span>
                            </a>
                          </li>
                        </ul>
                      </nav>
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