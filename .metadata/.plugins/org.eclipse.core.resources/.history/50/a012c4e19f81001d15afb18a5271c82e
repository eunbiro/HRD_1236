package controller;

import java.io.IOException;
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
		}
		
		getServletContext().getRequestDispatcher("/" + site)
		.forward(request, response);
	}

}
