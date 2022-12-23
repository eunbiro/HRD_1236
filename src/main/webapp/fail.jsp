<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색실패</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="wrap">
			<h2 class="title" style="font-size: 30px;">접종예약정보가 존재하지 않습니다!</h2>
			<button class="btn" onclick="location='search';">돌아가기</button>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>