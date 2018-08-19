package shawn.practice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import shawn.practice.model.QueryService;
import shawn.practice.model.User;

@Controller
public class QueryController {
	
	@Autowired
	QueryService queryService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String queryForm() {
		return "query";
	}
	
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public String queryResult(
			@RequestParam("username") String username,
			@RequestParam("phone") String phone,
			@RequestParam("sex") String sex,
			Model model) {

		List<User> matchedUsers = queryService.queryUsers(username, phone, sex);
		model.addAttribute("showResult", true);
		model.addAttribute("matchedUsers", matchedUsers);
		
		// fill back to the form 
		model.addAttribute("username", username);
		model.addAttribute("phone", phone);
		model.addAttribute("sex", sex);
		
		return "query";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("errorOccurred", true);
		mav.addObject("exception", e);
		if(e instanceof SQLException || e instanceof DataAccessException) {
			mav.addObject("errorReason", "資料庫連線異常");
		}
		mav.setViewName("query");
		
		return mav;
	}
}
