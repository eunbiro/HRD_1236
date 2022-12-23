<%@page import="DTO.Vaccresv"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Vaccresv vacc = new Vaccresv();
	vacc = (Vaccresv)request.getAttribute("vacc");
%>
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
		<h2 class="title" style="font-size: 25px;">예약번호:<%=vacc.getResvno()%>의 접종예약조회</h2>
		<div class="wrap">
			<table style="width: 1000px;">
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
				<tr>
					<td><%=vacc.getPname()%></td>
					<td><%=vacc.getJumin()%></td>
					<td><%=vacc.getGender()%></td>
					<td><%=vacc.getTel()%></td>
					<td><%=vacc.getResvdate()%></td>
					<td><%=vacc.getResvtime()%></td>
					<td><%=vacc.getHospname()%></td>
					<td><%=vacc.getHosptel()%></td>
					<td><%=vacc.getHospaddr()%></td>
					<td><%=vacc.getVcode()%></td>
				</tr>
			</table>
		<button class="btn" onclick="location='search';">돌아가기</button>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>