<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
	
	// System.out.println(errorMsg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 오류 발생</title>
<style>

	div { box-sizing: border-box; }

	#outer { 
		width: 900px; 
		height: 400px; 
		border: solid skyblue;
        border-radius: 45px; 
        padding: 30px 30px;
		margin: auto;
		margin-top: 50px;
	}


	#logo { width: 35%; height: 100%; float: left; }
	#text { width: 65%; height: 100%; float: left; }

	#logo>img {width: 100%; height: 100%; object-fit: cover; }
	#text>p {
		width: 100%;
		height: 100%;
		padding: 0px;
		font-size: 18px;
		font-weight: 200;
		color: #9db1ca;
		margin-top: 150px;
		margin-left: 20px;
		line-height: 1.5;
	}

	body { font-family: 'Noto Sans KR', sans-serif !important; }

</style>

	<!-- 폰트 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

</head>
<body>

	<div id="outer">
		<div id="logo"><img src="resources/images/logo/logo2.png"></div>
		<div id="text">
			<p>
				<span style="font-size: 30px; color: #163A66; font-weight: bold;">죄송합니다.<br> 현재 서비스 이용이 원활하지 않습니다.</span> <br>
				자세한 내용은 사이트 관리자에게 문의하시기 바랍니다.
			</p>
		</div>
	</div>

</body>
</html>