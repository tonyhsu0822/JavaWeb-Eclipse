package example.shawn.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {
	@Value("${path.member.view}")
	private String FORWARD_MEMBER_VIEW;
	@Value("${path.redirect.member}")
	private String REDIRECT_MEMBER;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("Member")
	public String member(
			@SessionAttribute("logined") String username,
			Model model) throws ServletException, IOException {	
//		String username = (String)request.getSession().getAttribute("logined");
//		UserService userService = (UserService) request.getServletContext().getAttribute("userService"); 
		
		// model's attributes defaultly set to request's attributes
		model.addAttribute("messages", userService.getMessages(username));
		return FORWARD_MEMBER_VIEW;
	}
	
	@RequestMapping(value = "NewMessage", method = RequestMethod.POST)
	protected String newMessage(
			@RequestParam("content") String content,
			@SessionAttribute("logined") String username,
			Model model) throws ServletException, IOException {	
//		request.setCharacterEncoding("utf-8");
//		String content = request.getParameter("content");
		if(content == null || content.isEmpty()) { // empty content, do nothing and return
			return REDIRECT_MEMBER;
		} else if(content.length() > 140) { // content length over limit, forward back
			model.addAttribute("messages", userService.getMessages(username));
			return FORWARD_MEMBER_VIEW;
		} else { // add new message
//			UserService userService = (UserService) request.getServletContext().getAttribute("userService"); 
//			String username = (String)request.getSession().getAttribute("logined");
			userService.addMessage(username, content);
			return REDIRECT_MEMBER;
		}
	}
	
	@RequestMapping(value = "DelMessage", method = RequestMethod.POST)
	protected String delMessage(
			@SessionAttribute("logined") String username,
			@RequestParam("millis") String ms) throws ServletException, IOException {
//		String username = (String)request.getSession().getAttribute("logined");
//		String ms = request.getParameter("millis");
		if(ms != null) {
//			UserService userService = (UserService) request.getServletContext().getAttribute("userService"); 
			userService.deleteMessage(username, ms);
		}
		
		return REDIRECT_MEMBER;
	}
}






