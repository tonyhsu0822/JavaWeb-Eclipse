package example.shawn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.Message;
import example.shawn.model.UserService;

/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		
		List<Message> latest = userService.latestMessages(10);
		request.setAttribute("latest", latest);
		
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
