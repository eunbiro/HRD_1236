package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VaccresvDAO;

@WebServlet("/")
public class VacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VacController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getServletPath();
		String site = null;
		
		System.out.println(command);
		VaccresvDAO vacc = new VaccresvDAO();
		switch (command) {
		case "/home" :
			site = "index.jsp";
			break;
		case "/add" :
			site = "add.jsp";
			break;
		case "/search" :
			site = "search.jsp";
			break;
		case "/insert" :
			int result = vacc.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result == 1) {
				out.println("<script>");
				out.println("alert('접종예약정보가 등록 되었습니다!'); location='search';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('등록실패!'); location='add';");
				out.println("</script>");
				out.flush();
			}
			break;
		case "/select" :
			site = vacc.select(request, response);
			
			break;
		case "/result" :
			site = vacc.result(request, response);
			break;
		}
		
		getServletContext().getRequestDispatcher("/" + site)
		.forward(request, response);
	}

}
