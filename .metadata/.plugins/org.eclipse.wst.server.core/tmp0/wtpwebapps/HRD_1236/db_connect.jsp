<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB연동</title>
</head>
<body>
	<h3>Data base 연동 샘플</h3>
	<hr>
	<%
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
			if (con != null) {
				out.print("Database Connect : [ " + " <b>success<b> ] <br>");
			} else {
				out.print("Database Connect : [ " + " <b>fail<b> ] <br>");
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sysdate from dual");
			while (rs.next()) {
				out.println("Today date : " + rs.getString(1) + "<hr>");
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<footer>
		<address>
		<center>
			Web site Layout DB Connection Page <br />
			Home page : http://www.hrdkorea.or.kr <br />
			mobile : 011-222-3333 <br />
		</center>
		</address>
	</footer>
</body>
</html>