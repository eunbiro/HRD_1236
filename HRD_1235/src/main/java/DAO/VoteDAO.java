package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Vote;

public class VoteDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		return con;
	}

	public String rank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> list = new ArrayList<Member>();
		
		try {
			conn = getConnection();
			
			ps = conn.prepareStatement("SELECT A.M_NO, A.M_NAME, "
					+ "(SELECT COUNT(B.M_NO) COUNT "
					+ "FROM TBL_VOTE_202005 B "
					+ "WHERE A.M_NO = B.M_NO AND B.V_CONFIRM = 'Y') COUNT "
					+ "FROM TBL_MEMBER_202005 A");
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				
				member.setNo(Integer.parseInt(rs.getString(1)));
				member.setName(rs.getString(2));
				member.setCount(rs.getString(3));
				
				list.add(member);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "rank.jsp";
	}
	
	public String resultList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Vote> list = new ArrayList<Vote>();
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT V_NAME, "
					+ "'19' || SUBSTR(V_JUMIN, 1, 2) || '년' || SUBSTR(V_JUMIN, 3, 2) || '월' ||  SUBSTR(V_JUMIN, 5, 2) || '일생' V_JUMIN, "
					+ "SUBSTR(V_JUMIN, 1, 2) AGE, DECODE(SUBSTR(V_JUMIN, 7, 1), '1', '남', '2', '여') S, "
					+ "M_NO, SUBSTR(V_TIME, 1, 2) || ':' || SUBSTR(V_TIME, 3, 2) V_TIME, "
					+ "DECODE(V_CONFIRM, 'Y', '확인', 'N', '미확인') V_CONFIRM "
					+ "FROM TBL_VOTE_202005 "
					+ "WHERE V_AREA = '제1투표장'");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vote vote = new Vote();
				
				vote.setName(rs.getString(1));
				vote.setJumin(rs.getString(2));
				String age = String.valueOf(2020 - Integer.parseInt("19" + rs.getString(3)));
				vote.setAge(age);
				vote.setGender(rs.getString(4));
				vote.setNo(rs.getString(5));
				vote.setTime(rs.getString(6));
				vote.setConfirm(rs.getString(7));
				
				list.add(vote);
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
	
	
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> list = new ArrayList<Member>();
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT A.M_NO, A.M_NAME, B.P_NAME, "
					+ "DECODE(P_SCHOOL, '1', '고졸', '2', '학사', '3', '석사', '박사') P_SCHOOL, "
					+ "SUBSTR(A.M_JUMIN, 1, 6) || '-' || SUBSTR(A.M_JUMIN, 7) M_JUMIN, "
					+ "A.M_CITY, SUBSTR(B.P_TEL1, 1, 2) || '-' || B.P_TEL2 || '-'  AS P_TEL, B.P_TEL3 "
					+ "FROM TBL_MEMBER_202005 A, TBL_PARTY_202005 B "
					+ "WHERE A.P_CODE = B.P_CODE");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				
				member.setNo(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setCode(rs.getString(3));
				member.setSchool(rs.getString(4));
				member.setJumin(rs.getString(5));
				member.setCity(rs.getString(6));
				member.setTel(rs.getString(7));
				
				switch (Integer.parseInt(rs.getString(8))%10) {
				case 1:
					member.setTel("1111");
					break;
				case 2:
					member.setTel("2222");
					break;
				case 3:
					member.setTel("3333");
					break;
				case 4:
					member.setTel("4444");
					break;
				case 5:
					member.setTel("5555");
					break;
				}
				
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
	
	
	
	public int insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jumin = request.getParameter("jumin");
		String name = request.getParameter("name");
		String number = request.getParameter("number");
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String check = request.getParameter("check");
		
		int result = 0;
		
		System.out.println(name + ", " + number + ", " + check);
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO TBL_VOTE_202005 VALUES (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, jumin);
			ps.setString(2, name);
			ps.setString(3, number);
			ps.setString(4, time);
			ps.setString(5, place);
			ps.setString(6, check);
			
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
