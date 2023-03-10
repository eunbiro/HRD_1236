<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>백신접종예약</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">백신접종예약</div>
		<div class="wrap">
			<form name="frm" action="insert">
				<table>
					<tr>
						<th>접종예약번호</th>
						<td><label><input type="text" name="resvno" />예) 20210001</label></td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td><label><input type="text" name="jumin" />예) 710101-1000001</label></td>
					</tr>
					<tr>
						<th>백신코드</th>
						<td><label><input type="text" name="vcode" />예) V001 ~ V003</label></td>
					</tr>
					<tr>
						<th>병원코드</th>
						<td><label><input type="text" name="hcode" />예) H001</label></td>
					</tr>
					<tr>
						<th>예약일자</th>
						<td><label><input type="text" name="resvdate" />예) 20211231</label></td>
					</tr>
					<tr>
						<th>예약시간</th>
						<td><label><input type="text" name="resvtime" />예) 1230</label></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" onclick="fn_submit(); return false;">등록</button>
							<button class="btn" type="reset" onclick="alert('정보를 지우고 처음부터 다시 입력합니다!');">다시쓰기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>