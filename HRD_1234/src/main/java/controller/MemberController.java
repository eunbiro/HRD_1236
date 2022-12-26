package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();			// 톰캣의 context path를 가져온다.(server/server.xml에서 확인)
		String command = request.getServletPath();			// 주소중 맨 끝의 파일명만 가져온다. ex) http://localhost:8090/HRD_1234/index.jsp 에서 index.jsp
		String site = null;
		
		System.out.println(context + ", " + command);
		
		MemberDAO member = new MemberDAO();
		
		switch (command) {
		case "/home" :
			site = "index.jsp";
			break;
			
		case "/insert" :
//			site = member.insert(request, response);
			
			int result3 = member.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if (result3 == 1) {		// update성공
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('회원등록이 완료 되었습니다!'); location.href='add';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('등록실패!'); location.href='add';");
				out.println("</script>");
				out.flush();
			}
			break;
			
		case "/list" :
			site = member.selectAll(request, response);
			break;
			
		case "/add" :
			site = member.nextCustno(request, response);
			break;
			
		case "/modify" :
			site = member.modify(request, response);
			break;
			
		case "/result" :
			site = member.selectResult(request, response);
			break;
			
		case "/update" :
			int result1 = member.update(request, response);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			
			if (result1 == 1) {		// update성공
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('수정실패!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			}
			break;
			
		case "/delete" :
			int result2 = member.delete(request, response);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			
			if (result2 == 1) {		// delete성공
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('회원삭제가 완료 되었습니다!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");				// location.href='HRD1234';
				out.println("alert('삭제실패!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			}
			break;
		}
		getServletContext().getRequestDispatcher("/" + site)
		.forward(request, response);
	}
}
