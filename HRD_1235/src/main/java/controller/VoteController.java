package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VoteDAO;

@WebServlet("/")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VoteController() {
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
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		
		System.out.println(context + ", " + command);
		
		VoteDAO vote = new VoteDAO();
		
		switch(command) {
		case "/home" :
			site = "index.jsp";
			break;
			
		case "/list" :
			site = vote.list(request, response);
			break;
			
		case "/result" :
			site = vote.resultList(request, response);
			break;
			
		case "/rank" :
			site = vote.rank(request, response);
			break;
			
		case "/add" :
			site = "add.jsp";
			break;
			
		case "/insert" :
			int result = vote.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if (result == 1) {
				out.println("<script>");
				out.println("alert('투표하기 정보가 정상적으로 등록 되었습니다!'); location.href='home';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('등록실패!'); location.href='add';");
				out.println("</script>");
				out.flush();
			}
			break;
		}
		
		getServletContext().getRequestDispatcher("/" + site)
		.forward(request, response);
	}

}
