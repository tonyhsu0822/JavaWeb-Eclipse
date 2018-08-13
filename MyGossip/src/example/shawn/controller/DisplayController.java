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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {
	@Value("${path.index.view}")
	private String FORWARD_INDEX;
	@Value("${path.user.view}")
	private String FORWARD_USER;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index(Model model) throws ServletException, IOException {
//		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		
		List<Message> latest = userService.latestMessages(10);
//		request.setAttribute("latest", latest);
		model.addAttribute("latest", latest);
		
//		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
		return FORWARD_INDEX;
	}
	
	@RequestMapping("user/{username}")
	public String user(
			@PathVariable("username") String username,
			Model model) throws ServletException, IOException {
		// get the requested username from URL
//		String username = request.getRequestURI().replaceAll("/MyGossip/user/", "");
//		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		
//		request.setAttribute("username", username);
		model.addAttribute("username", username);
		if(userService.exist(username)) {
			List<Message> messages = userService.getMessages(username);
//			request.setAttribute("messages", messages);
			model.addAttribute("messages", messages);
		} else {
//			request.setAttribute("errorList",
//					Arrays.asList( String.format("%s 還沒發表訊息", username) ));
			model.addAttribute("errorList", 
					Arrays.asList( String.format("%s 還沒發表訊息", username) ));
		}

//		request.getRequestDispatcher(FORWARD_USER).forward(request, response);
		return FORWARD_USER;
	}
}
