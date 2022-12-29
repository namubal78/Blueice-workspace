<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    /* footer 영역 */
    #footer { 
        width : 100%;
        height: 150px;
        background-color: #edebeb;
        margin: auto;
    }

    /* footer 자식의 개별적인 속성 : 세로길이 */
    #footer_1 { height: 20%; }
    #footer_2 { height: 80%; }

    /* footer_2 자식의 공통적인 속성 : p 태그들 */
    #footer_2>p {
        width: 100%;
        /* border: 1px solid red; */
        margin: 0px; /* p 태그에는 기본적으로 위 아래 마진이 있음 */
        box-sizing: border-box; /* p 태그도 마찬가지로 테두리를 포함한 width, height 속성을 적용시킬것 */
        font-size: 13px; /* 글자크기 줄이기 */
    }

    /* footer_2 자식의 개별적인 속성 : 세로길이 */
    #p1 { 
        height: 80%;
        padding: 5px 15px; /* padding 으로 상하, 좌우의 여백 넣어주기 */
    }
    #p2 { 
        height: 20%;
        text-align: center;
    }

    #footer_1>a {
        text-decoration: none;
        color: black;
        font-weight: 600;
        margin: 15px;
        vertical-align: middle; /* 수직 구조에서 가운데로 맞추는 속성 */
    }

</style>
</head>
<body>

    <div id="footer">
        <div id="footer_1">
            <a href="locationLink.in">오시는 길</a> |
            <a href="list.no?currentPage=1">공지사항</a> |
            <a href="faqSelect.in">FAQ</a> |
            <a href="inquiryInsertForm.in">1:1문의하기</a>
        </div>
        <div id="footer_2">
            <p id="p1">
                대표자 : 이경미<br>
                사업자등록번호 : 202-07-25123<br>
                대표전화 : 0725-1223<br>
                팩스 : 02-725-1223<br>
                [07212] 서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F
            </p>
            <p id="p2">
                Copyright © Blue ice Korea All Right Reserved
            </p>
        </div>
    </div>

</body>
</html>