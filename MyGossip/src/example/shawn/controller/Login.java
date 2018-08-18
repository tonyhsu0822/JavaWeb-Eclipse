package example.shawn.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.Message;
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
		Optional<String> optionalPassword = userService.encryptedPassword(username, password);
		
		try {
			request.login(username, optionalPassword.get());
			request.getSession().setAttribute("logined", username);
			response.sendRedirect(REDIRECT_SUCCESS);
		} catch(NoSuchElementException | ServletException e) {
			request.setAttribute("errorList", Arrays.asList("登入失敗"));
			List<Message> latest = userService.latestMessages(10);
			request.setAttribute("latest", latest);
			request.getRequestDispatcher(REDIRECT_ERROR).forward(request, response);
		}	
	}
}









