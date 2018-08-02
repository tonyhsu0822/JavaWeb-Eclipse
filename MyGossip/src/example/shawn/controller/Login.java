package example.shawn.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String REDIRECT_SUCCESS = "Member";
	private static final String REDIRECT_ERROR = "index.html";
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
		String redirectTo;
		
		if(login(username, password)) {
			// security issue: prevent session fixation attack
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			request.getSession().setAttribute("logined", username);
			redirectTo = REDIRECT_SUCCESS;
		} else {
			redirectTo = REDIRECT_ERROR;
		}
		
		response.sendRedirect(redirectTo);
	}

	private boolean login(String username, String password) throws IOException {
		if(username != null && !username.isEmpty()
				&& password != null && !password.isEmpty()) {
			
			Path userhome = Paths.get(Constants.PATH_USERS, username);
			return Files.exists(userhome) && isCorrectPassword(password, userhome); 
		}
		else {
			return false;
		}
	}
	
	private boolean isCorrectPassword(String password, Path userhome) throws IOException {
		Path profile = userhome.resolve("profile");
		try(BufferedReader reader = Files.newBufferedReader(profile)){
			String[] data = reader.readLine().split("\t");
			// 0:email 1:password(encrypted) 2:salt
			int encrypt = Integer.parseInt(data[1]);
			int salt = Integer.parseInt(data[2]);
			
			return (password.hashCode() + salt) == encrypt;
		}
	}
}









