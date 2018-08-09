package example.shawn.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.UserService;

/**
 * Servlet implementation class NewMessage
 */
@WebServlet("/NewMessage")
public class NewMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessage() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		String content = request.getParameter("content");
		if(content == null || content.isEmpty()) { // empty content, do nothing and return
			response.sendRedirect(Constants.REDIRECT_MEMBER);
			return;
		} else if(content.length() > 140) { // content length over limit, forward back
			request.getRequestDispatcher(Constants.REDIRECT_MEMBER).forward(request, response);
			return;
		} else { // add new message
			UserService userService = (UserService) getServletContext().getAttribute("userService"); 
			String username = (String)request.getSession().getAttribute("logined");
			userService.addMessage(username, content);
			response.sendRedirect(Constants.REDIRECT_MEMBER);
		}
	}
	
	

}




