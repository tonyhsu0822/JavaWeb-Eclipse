package example.shawn.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.Message;
import example.shawn.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessController {

	@Value("${path.redirect.member}")
	private String LOGIN_SUCCESS;
	@Value("${path.index.view}")
	private String LOGIN_ERROR;
	@Value("${path.redirect.index}")
	private String REDIRECT_INDEX;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public String login(
			@RequestParam(required=true) String username,
			@RequestParam(required=true) String password, 
			HttpServletRequest request) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
//		UserService userService = (UserService) request.getServletContext().getAttribute("userService"); 
		if(userService.login(username, password)) {
			// security issue: prevent session fixation attack
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			request.getSession().setAttribute("logined", username);
			return LOGIN_SUCCESS;
		} else {
			request.setAttribute("errorList", Arrays.asList("登入失敗"));
			List<Message> latest = userService.latestMessages(10);
			request.setAttribute("latest", latest);
			return LOGIN_ERROR;
		}
	}
	
	@RequestMapping("Logout")
	public String logout(HttpServletRequest request) throws ServletException, IOException {
		request.getSession().invalidate();
		return REDIRECT_INDEX;
	}
}
