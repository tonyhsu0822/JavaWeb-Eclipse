package example.shawn.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String MSG_INVALID_EMAIL = "invalid eamil";
    private static final String MSG_INVALID_USERNAME = "invalid username";
    private static final String MSG_INVALID_PASSWORD = "invalid password";
    private static final String FORWARD_SUCCESS = "register_success.view";
    private static final String FORWARD_ERROR = "register_error.view";
    private static final String PATH_USERS = "C:/javaweb/eclipse-workspace/MyGossip/users";
    
    private static final Pattern REGEX_USERNAME = Pattern.compile("^\\w{1,16}$");
    private static final Pattern REGEX_PASSWORD = Pattern.compile("^\\w{8,16}$");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordconfirm");
		
		List<String> errorList = new ArrayList<>();
		if(!validateEmail(email)) {
			errorList.add(MSG_INVALID_EMAIL);
		}
		if(!validateUsername(username)) {
			errorList.add(MSG_INVALID_USERNAME);
		}
		if(!validatePassword(password, passwordConfirm)) {
			errorList.add(MSG_INVALID_PASSWORD);
		}
		
		String path;
		if(errorList.isEmpty()) {
			path = FORWARD_SUCCESS;
			tryCreateUser(email, username, password);
		}
		else {
			path = FORWARD_ERROR;
			request.setAttribute("errorlist", errorList);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	// maybe the html form input type [email] already handled the validation of email address
	private boolean validateEmail(String email) {
		return (email != null) && (!email.isEmpty());
	}
	
	// TODO username already existed?
	private boolean validateUsername(String username) {
		return (username != null) && (username.isEmpty())
				&& (REGEX_USERNAME.matcher(username).matches());
	}
	
	private boolean validatePassword(String password, String password2) {
		return (password != null) && (password2 != null)
				&& (!password.isEmpty()) && (!password2.isEmpty())
				&& (REGEX_PASSWORD.matcher(password).matches())
				&& (password.equals(password2));
	}
	
	private void tryCreateUser(String email, String username, String password) throws IOException{
//		Path userhome = Paths.get(PATH_USERS, username);
		File userhome2 = new File(PATH_USERS, username);
		if(!userhome2.exists() /* || Files.notExists(userhome)*/ ) {
			
			userhome2.mkdir();
			
			// encryption
			int salt = (int)(Math.random() * 100);
			String encrypt = String.valueOf(salt + password.hashCode());

			File profile2 = new File(userhome2, "profile");
			try(FileWriter fw = new FileWriter(profile2);
					BufferedWriter bw = new BufferedWriter(fw);){
				bw.write(String.format("%s\t%s\t%d", email, encrypt, salt));
			}
			
			// java.nio
//			Files.createDirectories(userhome);
//			Path profile = userhome.resolve("profile");
//			try(BufferedWriter writer = Files.newBufferedWriter(profile);){
//				writer.write(String.format("%s\t%s\t%d", email, encrypt, salt));
//			}
		}
	}
}



