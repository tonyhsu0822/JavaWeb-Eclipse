package example.shawn.controller;

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
 * Servlet implementation class DelMessage
 */
@WebServlet("/DelMessage")
public class DelMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelMessage() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prevent user manually typing URL to access this Servlet
		// redirect those who didn't got "logined" token to index
		// TODO make this become a Filter
		if(request.getSession().getAttribute("logined") == null) {
			response.sendRedirect(Constants.REDIRECT_INDEX);
			return;
		}
		
		String username = (String)request.getSession().getAttribute("logined");
		String ms = request.getParameter("millis");
		if(ms != null) {
			deleteMessage(username, ms);
		}
		
		response.sendRedirect(Constants.REDIRECT_MEMBER);
	}
	
	private void deleteMessage(String username, String ms) throws IOException {
		Path txt = Paths.get(Constants.PATH_USERS, username, String.format("%s.txt", ms));
		Files.delete(txt);
	}

}
