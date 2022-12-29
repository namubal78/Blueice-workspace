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
    
</style>

<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.css"
  rel="stylesheet"
/>
<!-- MDB -->
<script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.js"
></script>
</head>
<body>

	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>자주묻는질문</p>
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
                        
                        <!-- faq 부트스트랩 -->
                        <div class="accordion accordion-flush" id="accordionFlushExample">
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="flush-headingOne">
                                <button
                                  class="accordion-button collapsed"
                                  type="button"
                                  data-mdb-toggle="collapse"
                                  data-mdb-target="#flush-collapseOne"
                                  aria-expanded="false"
                                  aria-controls="flush-collapseOne"
                                >
                                후원금은 투명하게 쓰이나요? <!-- 질문이 들어가면 됩니다-->
                                </button>
                              </h2>
                              <div
                                id="flush-collapseOne"
                                class="accordion-collapse collapse"
                                aria-labelledby="flush-headingOne"
                                data-mdb-parent="#accordionFlushExample"
                              >
                                <div class="accordion-body"> <!-- 답변 내용이 들어가면 됩니다-->
                                  답변 내용
                                </div>
                              </div>
                            </div>
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="flush-headingTwo">
                                <button
                                  class="accordion-button collapsed"
                                  type="button"
                                  data-mdb-toggle="collapse"
                                  data-mdb-target="#flush-collapseTwo"
                                  aria-expanded="false"
                                  aria-controls="flush-collapseTwo"
                                >
                                  질문 내용
                                </button>
                              </h2>
                              <div
                                id="flush-collapseTwo"
                                class="accordion-collapse collapse"
                                aria-labelledby="flush-headingTwo"
                                data-mdb-parent="#accordionFlushExample"
                              >
                                <div class="accordion-body">
                                  답변 내용
                                </div>
                              </div>
                            </div>
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="flush-headingThree">
                                <button
                                  class="accordion-button collapsed"
                                  type="button"
                                  data-mdb-toggle="collapse"
                                  data-mdb-target="#flush-collapseThree"
                                  aria-expanded="false"
                                  aria-controls="flush-collapseThree"
                                >
                                  질문 내용
                                </button>
                              </h2>
                              <div
                                id="flush-collapseThree"
                                class="accordion-collapse collapse"
                                aria-labelledby="flush-headingThree"
                                data-mdb-parent="#accordionFlushExample"
                              >
                                <div class="accordion-body">
                                  답변 내용
                                </div>
                              </div>
                            </div>
                          </div>
                    
                   
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