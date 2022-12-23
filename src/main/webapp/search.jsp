<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접종예약조회</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">접종예약조회</div>
		<div class="wrap">
			<form name="frm" action="select">
				<table>
					<tr>
						<th>예약번호를 입력 하시오</th>
						<td><label><input type="text" name="resvno" /></label></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" onclick="fn_search(); return false;">예약조회</button>
							<button class="btn" type="button" onclick="location='home';">홈으로</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>