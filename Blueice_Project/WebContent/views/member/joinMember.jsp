<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

    #content { height: 1350px;}
    #content_2>div { width: 100%;}
    #content_2_1 { height: 17%; float: left; }
    #content_2_2 { height: 63%; float: left; }
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
    
    /* 내부 가입틀 CSS 요소들 */
    #enroll-form table { margin : auto; }
    #enroll-form input { margin : 8px; }
    /* #enroll-form td { border : 1px solid black;} */

    #enroll-text>p {
        font-size: 30px;
        font-weight: bold;
        text-align: center;
    }

    #enroll-form { height: 90%; float: left; }

    #enroll-form>form  {
        height: 700px;
        width: 60%;
        border: 2px solid skyblue;
        border-radius: 45px;
        margin: auto;
    }

    #signup-form>table { margin: auto; margin-top: 60px; }

    button[class*=btn-primary] { background-color: #3970B3; }


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
                <p>회원가입</p>
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
                        <div id="enroll-text"><p style="line-height: 70px;">북극곰이 발을 딛을 수 있는 빙하를 선물해주세요.</p></div>    
                        <div id="enroll-form">
                            <form id="signup-form" action="<%= contextPath %>/insert.me" method="post">
                                <table>
                                    <tr>
                                        <th>* 아이디</th>
                                        <td colspan="3"><input type="text" class="form-control" id="memId" name="memId" minlength="7" maxlength="15" style="width:300px" placeholder="영문자 숫자로 7 ~ 15자 이내로 입력하세요." required ></td>
                                        <td><button type="button" id="checkbutton" class="btn btn-primary" onclick="idCheck();">중복확인</button></td>
                                        
                                        <!-- 중복확인 나중에 AJAX 라는 기술을 배우고 할 것 -->
                                    </tr>
                                    <tr>
                                        <th>* 비밀번호</th>
                                        <td colspan="3"><input type="password" class="form-control" id ="memPwd" name="memPwd" maxlength="30" style="width:300px" placeholder="영문자,숫자,특수문자로된 총 8 ~ 15자 이내로 입력하세요." required></td>
                                    </tr>
                                    <tr>
                                        <th>* 비밀번호 확인</th>
                                        <td  colspan="3"><input type="password" class="form-control" id="checkPwd" name="checkPwd" maxlength="30" style="width:300px" required></td>
                                        <!-- 서버로 넘기지는 않을것이기 때문에 name 속성은 생략 -->
                                    </tr>
                                    <tr>
                                        <th>* 성명</th>
                                        <td  colspan="3"><input type="text" class="form-control" id="memName" name="memName" maxlength="18" style="width:300px" placeholder="한글로 된 2~6자리 이름을 입력해주세요." required></td>
                                    </tr>
                                    <tr>
                                        <th>* 주민등록번호</th>
                                        <td colspan="3">
                                            <input type="text" id="personalBno" name="personalBno" class="form-control" maxlength="6" style="width: 136px; display:inline-block;">-
                                            <input type="password" id="personalAno" name="personalAno" class="form-control" maxlength="7" style="width:136px; display:inline-block;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>* 전화번호</th>
                                        <td  colspan="3"><input type="text" class="form-control" id="phone" name="phone" minlength="11" maxlength="11" style="width:300px" placeholder="-제외해서 입력해주세요." required></td>
                                    </tr>
                                    <tr>
                                        <th>* 이메일</th> <!-- 이메일 선택해서 인풋에 넣기 -->
                                        <td colspan="3">
                                            <input type="text" id="email_id" name= "emailBno" class="form-control" maxlength="18" value="" style="width:133px; display:inline-block;" required>@
                                            <select class="custom-select" name="emailAno" title="이메일 도메인 주소 선택" style="width:130px; display:inline-block;" onclick="setEmailDomain(this.value);return false;" required>
                                                <option>선택</option>
                                                    <option value="naver.com">naver.com</option>
                                                    <option value="gmail.com">gmail.com</option>
                                                    <option value="hanmail.net">hanmail.net</option>
                                                    <option value="hotmail.com">hotmail.com</option>
                                                    <option value="korea.com">korea.com</option>
                                                    <option value="nate.com">nate.com</option>
                                                    <option value="yahoo.com">yahoo.com</option>
                                            </select>
                                        </td>
                                        <td width="80px" style="display:inline-block;"></td>
                                    </tr>
                                    <tr>
                                        <th>* 우편번호</th>
                                        <td colspan="3">
                                            <input type="text" class="form-control" name="zip" id="sample6_postcode" maxlength="5" placeholder="우편번호" style="width:154px; display:inline-block;" required>
                                            <input type="button" name="address_search" class="btn btn-primary" style="display:inline-block; background-color:#3970B3; color:white;" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>* 주소</th>
                                        <td  colspan="3"><input type="text" class="form-control" name="address1" id="sample6_address" placeholder="주소" style="width:300px;" required></td>
                                    </tr>
                                    <tr>
                                        <th>* 상세주소</th>
                                        <td colspan="4">
                                            <input type="text" class="form-control" name="address2" id="sample6_detailAddress" placeholder="상세주소" style="width:250px; display:inline-block;" required>
                                            <input type="text" class="form-control" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목" style="width:180px; display:inline-block;">
                                        </td>
                                    </tr>
                                </table>
                                
                                <script>
							    	function idCheck() {
							    		
							    		// 아이디 중복체크 전 유효성 검사 들어감
							    		var memId = document.getElementById("memId");
							    		
							    		// 아이디를 입력하는 input 요소 객체
							    		var $memId = $("#signup-form input[name=memId]");
							    		// name 속성이 userId 인 요소가 menubar.jsp 에도 있기 때문에
							    		// 확실하게 어디에 속해있는 요소인지 잘 적어둬야함
							    		
						                var regExp = /^[a-z\d]{7,15}$/i;
					                    if(!regExp.test(memId.value)) {
					                        alert("아이디를 영문자, 숫자로만 총 7 ~ 15자로 입력해주세요.");
					                        memId.select(); // 재입력 유도
					                        return false;
					                    }
					                    
					                    else {
					                    	
								    		$.ajax({
								    			url : "idCheck.me",
								    			data : {checkId : $memId.val()},
								    			success : function(result) {
								    				
								    				// result 의 값은 "NNNNN" 또는 "NNNNY" 가 담겨있음
								    				if(result == "NNNNN") { // 사용 불가
								    					
								    					alert("이미 존재하거나 탈퇴한 회원의 아이디입니다.");
								    					$memId.focus(); // 재입력 유도
								    				} else { // 사용 가능
								    					
								    					if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")) { // 사용하겠다.
								    						
								    						// 아이디값 확정 => 다시 수정 못하게 readonly 속성 추가
								    						$memId.attr("readonly", true);
								    						
								    						// 회원가입버튼 활성화
								    						$("#signup-form button[type=submit]").removeAttr("disabled");
								    						
								    					} else { // 사용하지 않겠다.
								    						
								    						// 재입력 유도
								    						$memId.focus();
								    					}
								    				}
								    			}, 
								    			error : function() {
								    				console.log("아이디 중복체크용 ajax 통신 실패!");
								    			}
								    		});	                    	
					                    }
							    	}
  							   </script>

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
                
                                <div align="center">
                                    <button type="submit" class="btn btn-primary" style="width:200px;" onclick="return validate();" disabled>가입하기</button>
                                </div>
                            </form>               
                        <!-- 유효성 검사를 위한 정규식 -->
                        <script>
			                function validate() {
			                	
			                	var memId = document.getElementById("memId");
			                	var memPwd = document.getElementById("memPwd");
			                	var checkPwd = document.getElementById("checkPwd");
			                	var memName = document.getElementById("memName");
			                	var personalBno = document.getElementById("personalBno");
			                	var personalAno = document.getElementById("personalAno");
			                	var phone = document.getElementById("phone");
			                	
			                	// 아이디 정규식 영문자, 숫자로만 총 7 ~ 15자로 이루어지게
			                    var regExp = /^[a-z\d]{7,15}$/i;
			                    if(!regExp.test(memId.value)) {
			                        alert("아이디를 영문자, 숫자로만 총 7 ~ 15자로 입력해주세요.");
			                        memId.select(); // 재입력 유도
			                        return false;
			                    }
			                	
			                    // 비밀번호 정규식
			                    // 영문자, 숫자, 특수문자(!@#$%^) 로 총 8 ~ 15 자 로 이루어져야함
			                    regExp = /^[a-z\d!@#$%^]{8,15}$/i;
			                    if(!regExp.test(memPwd.value)) {
			                        alert("비밀번호를 영문자,숫자,특수문자로 총 8 ~ 15 자로 입력해주세요.");
			                        memPwd.value = "";
			                        memPwd.focus(); // 재입력 유도
			                        return false;
			                    }
			                    
			                	// 비밀번호 유효성 검사
			                    if($("input[name=memPwd]").val() != $("input[name=checkPwd]").val()) {
			                        alert("비밀번호가 일치하지 않습니다.");
			                        checkPwd.select(); // 재입력 유도
			                        return false;
			                    }
			                	
			                    // 이름검사 2~6자리 한글만 들어갈수 있게
			                    regExp = /^[가-힣]{2,6}$/;
			                    if(!regExp.test(memName.value)) {
			                        alert("한글로 된 2~6자리 이름을 입력해주세요.");
			                        memName.select(); // 재입력 유도
			                        return false;
			                    }
			                    
			                    // 주민등록번호 앞자리 정규식
			                    regExp = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])$/;
			                    if(!regExp.test(personalBno.value)) {
			                        alert("유효한 주민등록번호를 입력해주세요.");
			                        personalBno.select(); // 재입력 유도
			                        return false;
			                    }
			                    
			                    // 주민등록번호 뒷자리 정규식
			                    regExp = /^[1-4][0-9]{6}$/;
			                    if(!regExp.test(personalAno.value)) {
			                        alert("유효한 주민등록번호를 입력해주세요.");
			                        personalAno.select(); // 재입력 유도
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
    


</body>
</html>