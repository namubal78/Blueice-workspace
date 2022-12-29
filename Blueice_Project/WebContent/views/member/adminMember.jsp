<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 - 개인회원</title>
<style>
	
	div {
		/* border: 1px solid rgb(40, 78, 122); */
        box-sizing: border-box;
    }

    /* 전체를 감싸는 wrap */
    .wrap {
        width: 80%;
        height: 900px;
        margin : auto; /* 가운데로 좌, 우 자동 정렬*/
    }

    .wrap>div { height : 100%; }
    
    /* 컨테이너 영역 */
    #container {
        width: 85%;
        float: left;
        background-color: #f3f5f9;
     }

    #container>div { width: 100%; float: left;}
    #header { height: 15%; }
    #content { height: 85%; margin: auto; }

    /* 헤더 영역 */
    #header>div { height: 100%; float: left; }

    #header_1 { width: 30%; }
    #header_2 { width: 55%; }
    #header_3 { width: 15%;}

    #header_1>p {
        width: 100%;
        height: 100%; 
        box-sizing: border-box;
        color: #163A66;
        font-weight: bolder;
        font-size: 23px;
        margin-top: 45px;
        margin-left: 60px; 
    }

    #header_3>p {
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        margin-top: 50px;
        text-align: center;
    }

    #header_3 a {
        text-decoration: none;
        font-size: 17px;
        color: #163A66;
    }
    
    /* container: 우측상단 서치바부터 페이징까지 */

    /* table: 게시글 카테고리부터 마지막 10번째 게시글까지 */
    .table { text-align: center; height: 50px; }
    
    /* 게시글 한 줄 한 줄 */
    tbody>tr:hover { cursor: pointer; }

    /* 게시글 카테고리 (첫줄) */
    thead>tr { font-weight: bolder; }

    td { height: 50px;}

    /* 우측 상단 검색 카테고리, 내용 검색 칸 2개 묶음 */
    #content_1 { height: 10%; }
    #content_1>div {height: 100%; float: right; }

    .admin-donation-search { width: 65%; }
    .admin-donation-search>div { 
        width: 100%; 
        margin-left: 0px;
        margin-top: 10px;
    }

    #content_2 { height: 90%;}
    #content_2>div { height: 100%; position: relative; }

    #list { 
        width: 90%;
        position: absolute;
        margin: auto;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }


    /* 폰트 */
    body { font-family: 'Noto Sans KR', sans-serif !important; }

</style>

</head>
<body>
	<div class="wrap">
        <%@ include file="../common/admin_menubar.jsp" %>
        <div id="container">
            <div id="header">
                <div id="header_1">
                    <p>회원관리 - 개인회원</p>
                </div>
                <div id="header_2"></div>
                <div id="header_3">
                    <p>
                        <a>로그아웃</a>
                    </p>
                </div>
            </div>
            <div id="content">
                <div id="content_1">
                    <div class="admin-donation-search">
                        <div class="form-group row" >

                            <div style="margin-right:10px;">
                                <!-- 검색 카테고리 설정 칸 -->
                                <select class="selectpicker show-tick" data-style="btn-primary" data-width="100px">
                                    <option>회원 번호</option>
                                    <option>후원금액</option>
                                    <option>가입일</option>
                                    <option selected>이름</option>
                                </select>
                            </div>
                    
                            <!-- 검색내용 입력 칸 -->
                            <div class="input-group" style="width: 250px;">
                                <input type="text" class="form-control" placeholder="내용을 입력해주세요">
                                <div class="input-group-append">
                                    <button class="btn btn-secondary" type="button">
                                    <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>

                            <!-- 글작성, 수정, 삭제 버튼 -->
                            <div class="form-button" style="margin-left:300px">
                                <button class="btn btn-success">수정</button>
                                <button class="btn btn-danger">삭제</button>
                            </div>

                        </div>
                    </div> 
                </div>
                <div id="content_2">
                    <div id="list">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>회원번호</td>
                                    <td>이름</td>
                                    <td>전화번호</td>
                                    <td>아이디</td>
                                    <td>가입일</td>
                                    <td>총 후원 금액</td>
                                    <td> <!-- 상단 체크 박스 누르면 전체 선택, 전체 선택 시 아래 중 하나라도 풀리면 전체 선택 해제 -->
                                        <input type="checkbox" class="check-all custom-control-input" id="checkAll" name="checkAll">
                                        <label class="custom-control-label" for="checkAll"></label>
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>KH</td>
                                    <td>010-1111-2222</td>
                                    <td>kh01</td>
                                    <td>2021-09-21</td>
                                    <td>50,000</td>
                                    <td>
                                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="check">
                                        <label class="custom-control-label" for="customCheck1"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>유니세프</td>
                                    <td>010-3333-4444</td>
                                    <td>unicef</td>
                                    <td>2021-08-24</td>
                                    <td>150,000</td>
                                    <td>
                                        <input type="checkbox" class="custom-control-input" id="customCheck2" name="check">
                                        <label class="custom-control-label" for="customCheck2"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>그린피스</td>
                                    <td>010-5555-6666</td>
                                    <td>greenpeace</td>
                                    <td>2021-08-01</td>
                                    <td>200,000</td>
                                    <td>
                                        <input type="checkbox" class="custom-control-input" id="customCheck3" name="check">
                                        <label class="custom-control-label" for="customCheck3"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>7</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>8</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>9</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>10</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                        
                            </tbody>
                        </table>
            
                        <br>
                          
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
                                <li class="page-item"><a class="page-link" href="#">4</a></li> 
                                <li class="page-item"><a class="page-link" href="#">5</a></li> <!-- 해당 코드 복붙해서 이어붙이면 (6,7,8..) 페이지수 늘어남 -->
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
        </div>
    </div>

</body>
</html>