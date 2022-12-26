package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Money;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
		// 데이터 베이스 연결	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		return con;
	}
	
		// insert 부분(회원등록)
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		
		int result = 0;
		
		try {
			conn = getConnection();			// db연결
			String sql = "insert into member_tbl_02 values(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custno);
			ps.setString(2, custname);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, joindate);
			ps.setString(6, grade);
			ps.setString(7, city);
			
				// insert, update, delete: 성공한 레코드의 갯수를 반환
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String nextCustno(HttpServletRequest request, HttpServletResponse response) {
		try {
			conn = getConnection();
			String sql = "select max(custno)+1 custno from member_tbl_02";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int custno = 0;
			
			if (rs.next()) custno = rs.getInt(1);
			
			request.setAttribute("custno", custno);
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "add.jsp";
	}
	
		// select부분 (회원목록조회/수정)
	public String selectAll(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = getConnection();
			
			String sql = "select custno, custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') joindate, "
					+ "DECODE(grade, 'A', 'VIP', 'B', '일반', '직원') grade, city from member_tbl_02 order by custno";
					// DECODE(컬럼명, n을, A로, m을, B로, 나머지는 C로) 바꾸겠다. : switch문과 비슷함
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				member.setCustno(rs.getInt(1));
				member.setCustname(rs.getString(2));
				member.setPhone(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setJoindate(rs.getString(5));
				member.setGrade(rs.getString(6));
				member.setCity(rs.getString(7));
				
				list.add(member);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list.jsp";
	}
	
	public String selectResult(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Money> list = new ArrayList<Money>();
		try {
			conn = getConnection();
			String sql = "SELECT m1.custno, m1.custname, "
					+ "DECODE(grade, 'A', 'VIP', 'B', '일반', '직원') grade, "
					+ "SUM(m2.price) price "
					+ "FROM member_tbl_02 m1, money_tbl_02 m2 "
					+ "WHERE m1.custno = m2.custno "
					+ "GROUP BY (m1.custno, m1.custname, grade) "
					+ "ORDER BY price DESC";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Money money = new Money();
				money.setCustno(rs.getInt(1));
				money.setCustname(rs.getString(2));
				money.setGrade(rs.getString(3));
				money.setPrice(rs.getInt(4));
				
				list.add(money);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "result.jsp";
		
	}
	
		// 회원정보 수정
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			conn = getConnection();
			int custno = Integer.parseInt(request.getParameter("custno"));
			
			String sql = "select custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') joindate, grade, city"
					+ " from member_tbl_02 where custno=" + custno;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Member member = new Member();
			
			if (rs.next()) {
				member.setCustno(custno);
				member.setCustname(rs.getString(1));
				member.setPhone(rs.getString(2));
				member.setAddress(rs.getString(3));
				member.setJoindate(rs.getString(4));
				member.setGrade(rs.getString(5));
				member.setCity(rs.getString(6));
			}
			
			request.setAttribute("member", member);
			request.setAttribute("custno", custno);
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modify.jsp";
	}
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		
		int result = 0;
		
		try {
			conn = getConnection();			// db연결
			String sql = "update member_tbl_02 set"
					+ " custname = ?, "
					+ " phone = ?, "
					+ " address = ?, "
					+ " joindate = TO_DATE(?, 'YYYY-MM-DD'), "
					+ " grade = ?, "
					+ " city = ? "
					+ " where custno = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, custname);
			ps.setString(2, phone);
			ps.setString(3, address);
			ps.setString(4, joindate);
			ps.setString(5, grade);
			ps.setString(6, city);
			ps.setInt(7, custno);
			
			result = ps.executeUpdate();		// 성공하면 1을 리턴
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			conn = getConnection();
			String custno = request.getParameter("custno");
			String sql = "delete from member_tbl_02 where custno = " + custno;
			
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();		// 성공하면 1을 리턴
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
