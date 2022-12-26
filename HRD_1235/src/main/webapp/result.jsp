<%@page import="DTO.Vote"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<Vote> list = new ArrayList<Vote>();
	list = (ArrayList<Vote>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>특수검수조회</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">특수검수조회</div>
		<div class="wrapper">
			<table>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
				<%for (Vote v : list) { %>
				<tr>
					<td><%=v.getName() %></td>
					<td><%=v.getJumin() %></td>
					<td><%=v.getAge() %></td>
					<td><%=v.getGender() %></td>
					<td><%=v.getNo() %></td>
					<td><%=v.getTime() %></td>
					<td><%=v.getConfirm() %></td>
				</tr>
				<%} %>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>