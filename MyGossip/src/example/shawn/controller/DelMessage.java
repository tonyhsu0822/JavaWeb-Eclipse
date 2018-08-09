package example.shawn.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.UserService;

/**
 * Servlet implementation class DelMessage
 */
@WebServlet("/DelMessage")
public class DelMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelMessage() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("logined");
		String ms = request.getParameter("millis");
		if(ms != null) {
			UserService userService = (UserService) getServletContext().getAttribute("userService"); 
			userService.deleteMessage(username, ms);
		}
		
		response.sendRedirect(Constants.REDIRECT_MEMBER);
	}
	
	

}
