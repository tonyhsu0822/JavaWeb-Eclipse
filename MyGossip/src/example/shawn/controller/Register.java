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

import example.shawn.model.UserService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String MSG_INVALID_EMAIL = "電子信箱格式錯誤";
    private static final String MSG_INVALID_USERNAME = "使用者名稱不符合規定";
    private static final String MSG_INVALID_PASSWORD = "密碼不符合規定或兩次密碼不同";
    private static final String FORWARD_SUCCESS = "WEB-INF/jsp/register_success.jsp";
    private static final String FORWARD_FORM = "WEB-INF/jsp/register.jsp";
    
    private static final Pattern REGEX_USERNAME = Pattern.compile("^\\w{1,16}$");
    private static final Pattern REGEX_PASSWORD = Pattern.compile("^\\w{8,16}$");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    // GET request from the link of index
    // just forward it to "register.jsp"
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher(FORWARD_FORM).forward(request, response);
    }
    
	// POST request from register.jsp
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
			UserService userService = (UserService) getServletContext().getAttribute("userService");
			userService.tryCreateUser(email, username, password);
		}
		else {
			path = FORWARD_FORM;
			request.setAttribute("errorList", errorList);
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
}



