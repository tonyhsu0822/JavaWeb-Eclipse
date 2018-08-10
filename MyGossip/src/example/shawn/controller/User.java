package example.shawn.controller;

import java.io.IOException;
import java.util.Arrays;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the requested username from URL
		String username = request.getPathInfo().substring(1);
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		
		request.setAttribute("username", username);
		if(userService.exist(username)) {
			List<Message> messages = userService.getMessages(username);
			request.setAttribute("messages", messages);
		} else {
			request.setAttribute("errorList",
					Arrays.asList( String.format("%s 還沒發表訊息", username) ));
		}

		request.getRequestDispatcher(FORWARD_USER).forward(request, response);
	}

}
