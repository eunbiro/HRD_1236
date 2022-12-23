<%@page import="DTO.Hosp"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<Hosp> hosps = new ArrayList<Hosp>();
	hosps = (ArrayList<Hosp>)request.getAttribute("hosps");
	int count = 0;
%>
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
				<%for (Hosp h : hosps) {%>
				<tr>
					<td><%=h.getHospcode() %></td>
					<td><%=h.getHospname() %></td>
					<td><%=h.getCount() %></td>
				</tr>
				<%} %>
				<tr>
					<td></td>
					<td>총 누계</td>
					<%
					for (Hosp h : hosps) {
						count += Integer.parseInt(h.getCount());
					}
					%>
					<td><%=count %></td>
				</tr>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>