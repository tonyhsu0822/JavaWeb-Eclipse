package example.shawn.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String REDIRECT_SUCCESS = "Member";
	private static final String REDIRECT_ERROR = "/WEB-INF/jsp/index.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService userService = (UserService) getServletContext().getAttribute("userService"); 
		if(userService.login(username, password)) {
			// security issue: prevent session fixation attack
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			request.getSession().setAttribute("logined", username);
			response.sendRedirect(REDIRECT_SUCCESS);
		} else {
			request.setAttribute("errorList", Arrays.asList("登入失敗"));
			request.getRequestDispatcher(REDIRECT_ERROR).forward(request, response);
		}
		
		
	}

	
}









