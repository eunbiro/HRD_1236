<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접종예약조회</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<h2 class="title" style="font-size: 30px;">예약번호:의 접종예약조회</h2>
		<div class="wrap">
			<table>
				<tr>
					<th>이름</th>
					<th>주민번호</th>
					<th>성별</th>
					<th>전화번호</th>
					<th>예약일자</th>
					<th>예약시간</th>
					<th>병원명</th>
					<th>대표전화</th>
					<th>병원주소</th>
					<th>백신종류</th>
				</tr>
			</table>
		<button class="btn" onclick="location='search';">돌아가기</button>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>