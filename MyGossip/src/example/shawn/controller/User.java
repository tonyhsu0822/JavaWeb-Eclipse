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
 * Servlet implementation class User
 */
@WebServlet("/user/*")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String FORWARD_USER = "/WEB-INF/jsp/user.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the requested username from URL
		String username = request.getPathInfo().substring(1);
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		List<Message> messages = userService.getMessages(username);
		
		request.setAttribute("username", username);
		request.setAttribute("messages", messages);
		
		request.getRequestDispatcher(FORWARD_USER).forward(request, response);
	}

}
