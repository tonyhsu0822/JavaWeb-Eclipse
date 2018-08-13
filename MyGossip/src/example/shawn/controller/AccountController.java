package example.shawn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	@Value("${path.register_success.view}")
	private String FORWARD_SUCCESS;
	@Value("${path.register.view}")
	private String FORWARD_FORM;
	
	@Autowired
	private UserService userService;
	
	private final String MSG_INVALID_EMAIL = "電子信箱格式錯誤";
	private final String MSG_INVALID_USERNAME = "使用者名稱不符合規定";
	private final String MSG_INVALID_PASSWORD = "密碼不符合規定或兩次密碼不同";
	    
	private final Pattern REGEX_USERNAME = Pattern.compile("^\\w{1,16}$");
	private final Pattern REGEX_PASSWORD = Pattern.compile("^\\w{8,16}$");
	
	@RequestMapping(value = "Register", method = RequestMethod.GET)
	public String registerForm() throws ServletException, IOException {
//    	request.getRequestDispatcher(FORWARD_FORM).forward(request, response);
		return FORWARD_FORM;
    }
	
	@RequestMapping(value = "Register", method = RequestMethod.POST)
	protected String register(
			@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("passwordconfirm") String passwordConfirm,
			Model model) throws ServletException, IOException {
//		String email = request.getParameter("email");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String passwordConfirm = request.getParameter("passwordconfirm");
		
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
//			UserService userService = (UserService) request.getServletContext().getAttribute("userService");
			userService.tryCreateUser(email, username, password);
		}
		else {
			path = FORWARD_FORM;
//			request.setAttribute("errorList", errorList);
			model.addAttribute("errorList", errorList);
		}
//		request.getRequestDispatcher(path).forward(request, response);
		return path;
	}

	// maybe the html form input type [email] already handled the validation of email address
	private boolean validateEmail(String email) {
		return (email != null) && (!email.isEmpty());
	}
	
	// TODO username already existed?
	private boolean validateUsername(String username) {
		return (username != null) && (REGEX_USERNAME.matcher(username).find());
	}
	
	private boolean validatePassword(String password, String password2) {
		return (password != null) && (password2 != null)
				&& (REGEX_PASSWORD.matcher(password).find())
				&& (password.equals(password2));
	}
}












