<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	int custno = Integer.parseInt(request.getParameter("custno"));
	String custname = request.getParameter("custname");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	String joindate = request.getParameter("joindate");
	String grade = request.getParameter("grade");
	String city = request.getParameter("city");
	
	String gubun = request.getParameter("gubun");
	String ment = "회원등록이 완료되었습니다!";
	String error = "회원등록 실패!";
	
	int result = 0;
	
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		Statement stmt = con.createStatement();		// 디비 커넥션 준비 끝
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (gubun.equals("insert")) {
			String sql = "insert into member_tbl_02 values(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, custname);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, joindate);
			ps.setString(6, grade);
			ps.setString(7, city);
		} else if (gubun.equals("modify")) {
			ment = "회원정보수정이 완료되었습니다.";
			error = "회원정보수정 실패!";
			String sql = "update member_tbl_02 set"
					+ " custname = ?, "
					+ " phone = ?, "
					+ " address = ?, "
					+ " joindate = TO_DATE(?, 'YYYY-MM-DD'), "
					+ " grade = ?, "
					+ " city = ? "
					+ " where custno = ? ";

			ps = con.prepareStatement(sql);
			ps.setString(1, custname);
			ps.setString(2, phone);
			ps.setString(3, address);
			ps.setString(4, joindate);
			ps.setString(5, grade);
			ps.setString(6, city);
			ps.setInt(7, custno);
		}
		
		result = ps.executeUpdate();
		
		con.close();
		ps.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	if (result == 1) {
%>
		<script>
			alert('<%=ment%>');
			location='index.jsp';
		</script>
<%
	} else {
%>
		<script>
			alert('<%=error%>');
			location='index.jsp';
		</script>
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>