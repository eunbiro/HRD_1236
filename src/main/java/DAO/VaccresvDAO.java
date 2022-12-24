package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Hosp;
import DTO.Vaccresv;

public class VaccresvDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
		
		return con;
	}
	
	public int insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vaccresv vacc = new Vaccresv();
		
		int result = 0;
		
		vacc.setResvno(request.getParameter("resvno"));
		vacc.setJumin(request.getParameter("jumin"));
		vacc.setVcode(request.getParameter("vcode"));
		vacc.setHcode(request.getParameter("hcode"));
		vacc.setResvdate(request.getParameter("resvdate"));
		vacc.setResvtime(request.getParameter("resvtime"));
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into TBL_VACCRESV_202108 values (?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, vacc.getResvno());
			ps.setString(2, vacc.getJumin());
			ps.setString(3, vacc.getVcode());
			ps.setString(4, vacc.getHcode());
			ps.setString(5, vacc.getResvdate());
			ps.setString(6, vacc.getResvtime());
			
			result = ps.executeUpdate();
			
			System.out.println(result);
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resvno = request.getParameter("resvno");
		Vaccresv vacc = new Vaccresv();
		String result = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT B.PNAME, A.JUMIN, "
					+ "DECODE(SUBSTR(A.JUMIN, '8', '1'), '1', '남', '2', '여') GENDER, B.TEL, "
					+ "SUBSTR(A.RESVDATE, 1, 4) || '년' || SUBSTR(A.RESVDATE, 5, 2) || '월' || SUBSTR(A.RESVDATE, 7, 2) , "
					+ "SUBSTR(A.RESVTIME, 1, 2) || ':' || SUBSTR(A.RESVTIME, 3, 2), "
					+ "C.HOSPNAME, C.HOSPTEL, C.HOSPADDR, A.VCODE, A.RESVNO "
					+ "FROM TBL_VACCRESV_202108 A, TBL_JUMIN_201808 B, TBL_HOSP_202108 C "
					+ "WHERE A.JUMIN = B.JUMIN AND A.HOSPCODE = C.HOSPCODE "
					+ "AND A.RESVNO = " + resvno);
			
			
			if (rs.next()) {
				vacc.setPname(rs.getString(1));
				vacc.setJumin(rs.getString(2));
				vacc.setGender(rs.getString(3));
				vacc.setTel(rs.getString(4));
				vacc.setResvdate(rs.getString(5));
				vacc.setResvtime(rs.getString(6));
				vacc.setHospname(rs.getString(7));
				vacc.setHosptel(rs.getString(8));
				vacc.setHospaddr(rs.getString(9));
				vacc.setVcode(rs.getString(10));
				vacc.setResvno(rs.getString(11));
			}
			System.out.println(vacc.getPname());
			
			if (vacc.getPname() != null) {
				result = "successlist.jsp";
			} else {
				result = "fail.jsp";
			}
			
			request.setAttribute("vacc", vacc);
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String result(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Hosp> hosps = new ArrayList<Hosp>();
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT A.HOSPCODE, A.HOSPNAME, COUNT(B.HOSPCODE) "
					+ "FROM TBL_HOSP_202108 A, TBL_VACCRESV_202108 B "
					+ "WHERE A.HOSPCODE = B.HOSPCODE "
					+ "GROUP BY (A.HOSPCODE, A.HOSPNAME, B.HOSPCODE)");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Hosp hosp = new Hosp();
				
				hosp.setHospcode(rs.getString(1));
				hosp.setHospname(rs.getString(2));
				hosp.setCount(rs.getString(3));
				
				hosps.add(hosp);
				System.out.println(hosp.getHospcode() + ", " + hosp.getHospname() + "," + hosp.getCount());
			}
			
			request.setAttribute("hosps", hosps);
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "result.jsp";
	}
	
}
