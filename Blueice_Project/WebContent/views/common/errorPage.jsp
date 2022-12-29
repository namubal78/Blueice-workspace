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
<title>요청 실패</title>
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
				<span style="font-size: 40px; color: #163A66; font-weight: bold;"><%= errorMsg %></span>
			</p>
		</div>
	</div>

</body>
</html>