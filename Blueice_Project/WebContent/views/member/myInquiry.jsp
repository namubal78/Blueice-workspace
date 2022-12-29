<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
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

    
    #myDona { width: 100%; height: 100%; }
    #myDona>div { width: 100%; }

    #donaText { height: 10%; float: left; }
    #donaTable { height: 90%; float: left; padding: 70px; }

    #donaText>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    .table { text-align: center; height: 50px; }

    thead>tr { font-weight: bolder; font-size: medium; }

</style>
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
                    <div id="donaText"><p>1:1 문의 내역</p></div>
                    <div id="donaTable">

                        <!-- 날짜 검색 버튼 -->

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>글번호</td>
                                    <td>답변현황</td>
                                    <td>문의타입</td>
                                    <td>제목</td>
                                    <td>작성일</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>5</td>
                                    <td>답변대기</td>
                                    <td>후원</td>
                                    <td>후원 문의합니다</td>
                                    <td>2022-10-20</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>답변완료</td>
                                    <td>이벤트</td>
                                    <td>챌린지 완료가 되었는지 궁금해요</td>
                                    <td>2022-09-21</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>답변완료</td>
                                    <td>홍보/자료</td>
                                    <td>뉴스 게시글 중 이런 부분은 수정되어야하지 않나요?</td>
                                    <td>2022-09-17</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>답변완료</td>
                                    <td>기타문의</td>
                                    <td>블루아이스에 대해서 궁금한 점이 있어요</td>
                                    <td>2022-09-02</td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>답변완료</td>
                                    <td>후원</td>
                                    <td>정기 후원 금액을 바꿀 수 있나요?</td>
                                    <td>2022-08-26</td>
                                </tr>
                                <tr>
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
                                </tr>
                                <tr>
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
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    
                        <nav aria-label="Page navigation example">
                            <ul class="pagination" style="justify-content: center;">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">4</a></li>
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
            </div>
            <div id="content_2_3"></div>
        </div>
        <div id="content_3"></div>
    </div>
    <%@ include file="../common/footerbar.jsp" %>
</div>

</body>
</html>