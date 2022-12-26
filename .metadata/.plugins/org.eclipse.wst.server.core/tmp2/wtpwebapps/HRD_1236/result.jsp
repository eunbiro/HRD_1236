<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원별 접종건수 통계</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<h2 class="title" style="font-size: 30px;">병원별 접종건수 통계</h2>
		<div class="wrap">
			<table>
				<tr>
					<th>병원코드</th>
					<th>병원명</th>
					<th>접종건수</th>
				</tr>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>