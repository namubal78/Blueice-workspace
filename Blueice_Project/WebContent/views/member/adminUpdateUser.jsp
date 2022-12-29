<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.member.model.vo.Member" %>
<%
	//필요한 데이터 뽑기
	int memNo = (int)request.getAttribute("memNo");
	Member list = (Member)request.getAttribute("list");	

	// 현재 클릭한 회원의 정보를 변수로 담기
	// => 이름, 아이디, 전화번호, 이메일, 주소1, 주소2
	String memName = list.getMemName();
	String memId = list.getMemId();
	String phone = list.getPhone();
	String email = list.getEmail();
	String zip = list.getZip();
	String address1 = list.getAddress1();
	String address2 = list.getAddress2();
	
	// 이메일 주소값(@ 앞 뒤를 나누기 위해 배열에 넣음)
	String[] email2 = email.split("@");
%>
        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>

    /* [샛별] 파일 받으면 이 부분 지우기 */
    div {
        /* border : 1px solid rgb(40, 78, 122); */
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

    #enroll { width: 100%; height: 100%; }
    #enroll>div { width: 100%; }
    #enroll-text { height: 10%; float: left; }

    #enroll-text>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    #enroll-form { height: 90%; float: left; }

    #enroll-form>form {
        height: 90%;
        width: 50%;
        border: solid skyblue;
        border-radius: 45px;
        margin: auto;
        margin-top: 20px;
    }

    #mypage-form>table { margin: auto; margin-top: 60px; }

    #enroll-form input { margin : 8px; }

    table td { text-align: left; }

</style>
</head>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <!-- 우편번호 검색을 위한 API -->

<body>
 
	<div class="wrap">
        <%@ include file="../common/menubar.jsp" %>
        <div id="navigator2"></div>
        <div id="header">
            <div id="header_1">
                <p>회원 관리</p>
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
                    <div id="enroll">
                        <div id="enroll-text"><p>회원 정보 수정</p></div>
                        <div id="enroll-form">
                            <form id="mypage-form" action="<%= contextPath %>/adminMemberUpdate.me" method="post" align="center">
                                <table>
                                    <tr>
                                        <th>회원/단체명</th>
                                        <td colspan="3"><input type="text" id="memName" name="memName" class="form-control" maxlength="6" required value="<%= memName %>" style="width:300px"></td>
                                    </tr>
                                    <tr>
                                        <th>아이디</th>
                                        <td colspan="3"><input type="text" name="memId" class="form-control" maxlength="12" required value="<%= memId %>" style="width:300px" readonly></td>
                                    </tr>
                                    <tr>
                                        <th>전화번호</th>
                                        <td colspan="3"><input type="text" id="phone" name="phone" class="form-control" value="<%= phone %>" style="width:300px"></td>
                                    </tr>
                                    <tr>
                                        <th>이메일</th>
                                        <td><input type="text" id="email_id" name="emailBno" class="form-control" value="<%= email2[0] %>" maxlength="18" value="" style="width: 133px; display:inline-block;"></td>
                                        <td>@</td>
                                        <td> <!-- 동민오빠의 갖은 노력으로 결실한 산물 -->
                                            <select class="select custom-select" name="emailAno" title="이메일 도메인 주소 선택" style="width:130px; display:inline-block;">
                                                <option value="">선택</option>
                                                    <option value="naver.com" <% if(email2[1].equals("naver.com")) { %> selected <%} %>>naver.com</option>
                                                    <option value="gmail.com" <% if(email2[1].equals("gmail.com")) { %> selected <%} %>>gmail.com</option>
                                                    <option value="hanmail.net" <% if(email2[1].equals("hanmail.net")) { %> selected <%} %>>hanmail.net</option>
                                                    <option value="hotmail.com" <% if(email2[1].equals("hotmail.com")) { %> selected <%} %>>hotmail.com</option>
                                                    <option value="korea.com" <% if(email2[1].equals("korea.com")) { %> selected <%} %>>korea.com</option>
                                                    <option value="nate.com" <% if(email2[1].equals("nate.com")) { %> selected <%} %>>nate.com</option>
                                                    <option value="yahoo.com" <% if(email2[1].equals("yahoo.com")) { %> selected <%} %>>yahoo.com</option>
                                            </select>
                                        </td>
                                        <td width="80px" style="display:inline-block;"></td>
                                    </tr>
                                    <tr>
                                        <th>우편번호</th>
                                        <td colspan="3">
                                            <input type="text" class="form-control" name="zip" value="<%= zip %>"id="sample6_postcode" maxlength="5" placeholder="우편번호" style="width:154px; display:inline-block;">
                                            <input type="button" name="address_search" class="btn btn-primary" style="display:inline-block; background-color:#3970B3; color:white;" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>주소</th>
                                        <td  colspan="3"><input type="text" class="form-control" name="address1" value="<%= address1 %>" id="sample6_address" placeholder="주소" style="width:300px;"></td>
                                    </tr>
                                    <tr>
                                        <th>상세주소</th>
                                        <td colspan="4">
                                            <input type="text" class="form-control" name="address2" value="<%= address2 %>" id="sample6_detailAddress" placeholder="상세주소" style="width:250px; display:inline-block;">
                                            <input type="hidden" class="form-control" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목" style="width:100px; display:inline-block;">
                                        </td>
                                    </tr>
                                </table>

                                <script>
                                    /* 우편번호 검색 API */
                                    function sample6_execDaumPostcode() {
                                        new daum.Postcode({
                                            oncomplete: function(data) {
                                                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                                
                                                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                                                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                                var addr = ''; // 주소 변수
                                                var extraAddr = ''; // 참고항목 변수
                                
                                                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                                                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                                    addr = data.roadAddress;
                                                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                                    addr = data.jibunAddress;
                                                }
                                
                                                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                                                if(data.userSelectedType === 'R'){
                                                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                                        extraAddr += data.bname;
                                                    }
                                                    // 건물명이 있고, 공동주택일 경우 추가한다.
                                                    if(data.buildingName !== '' && data.apartment === 'Y'){
                                                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                                    }
                                                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                                    if(extraAddr !== ''){
                                                        extraAddr = ' (' + extraAddr + ')';
                                                    }
                                                    // 조합된 참고항목을 해당 필드에 넣는다.
                                                    document.getElementById("sample6_extraAddress").value = extraAddr;
                                                
                                                } else {
                                                    document.getElementById("sample6_extraAddress").value = '';
                                                }
                                
                                                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                                document.getElementById('sample6_postcode').value = data.zonecode;
                                                document.getElementById("sample6_address").value = addr;
                                                // 커서를 상세주소 필드로 이동한다.
                                                document.getElementById("sample6_detailAddress").focus();
                                            }
                                        }).open();
                                    }
                                </script>
                                
                                <br>                            

                                <div class="btns" align="center">
                                    <button type="submit" class="btn btn-primary" style="background-color:#3970B3; color:white;" onclick="return validate();">수정완료</button>
                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                                </div>
                            </form>
                            
                            <script>
			                function validate() {
			                	
			                	var memId = document.getElementById("memId");
			                	var memName = document.getElementById("memName");
			                	var phone = document.getElementById("phone");
			                	              		                  
			                    // 이름검사 2~6자리 한글만 들어갈수 있게
			                    regExp = /^[가-힣]{2,6}$/;
			                    if(!regExp.test(memName.value)) {
			                        alert("한글로 된 2~6자리 이름을 입력해주세요.");
			                        memName.select(); // 재입력 유도
			                        return false;
			                    }
			                                                      	
			                    // 휴대폰 번호 정규식
			                    var regExp = /^(010)[0-9]{8}$/;
			                    if(!regExp.test(phone.value)) {
			                        alert("유효한 전화번호를 입력해주세요.");
			                        phone.select(); // 재입력 유도
			                        return false;
			                    }
		
			                }
			            </script>
			            
                        </div>
                    </div>
                </div>
                <div id="content_2_3"></div>
            </div>
            <div id="content_3"></div>
        </div>
        <%@ include file="../common/footerbar.jsp" %>
    </div>
    
    <!-- 회원 탈퇴용 모달창 -->
    <!-- The Modal -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">회원탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center">
	      	<b>
		      	탈퇴 후 복구가 불가능 합니다. <br>
		      	정말로 탈퇴하시겠습니까? <br><br>
	      	</b>
      		<a href="<%= contextPath %>/adminMemberDelete.me?mno=<%= memNo %>"><button type="button" class="btn btn-danger btn-sm">탈퇴하기</button></a>
	      </div>
	    </div>
	  </div>
	</div>
    
</body>
</html>