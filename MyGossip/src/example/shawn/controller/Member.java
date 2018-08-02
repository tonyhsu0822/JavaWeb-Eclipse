package example.shawn.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String FORWARD_MEMBER_VIEW = "Member.view";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prevent user manually typing URL to access this Servlet
		// redirect those who didn't got "logined" token to index
		if(request.getSession().getAttribute("logined") == null) {
			response.sendRedirect(Constants.REDIRECT_INDEX);
			return;
		}
		
		String username = (String)request.getSession().getAttribute("logined");
		
		request.setAttribute("messages", getMessages(username));
		request.getRequestDispatcher(FORWARD_MEMBER_VIEW).forward(request, response);
	}

	// get user's message from files(.txt)
	// Map key: milliseconds since 1970/1/1, value: the message content
	private Map<Long, String> getMessages(String username) throws IOException{
		Path userhome = Paths.get(Constants.PATH_USERS, username);
		// to order the latest post at the first, using reverse order
		Map<Long, String> messages = new TreeMap<>(Comparator.reverseOrder());
		
		try(DirectoryStream<Path> textfiles = Files.newDirectoryStream(userhome, "*.txt")){
			for(Path txt : textfiles) {
				// translate filename to milliseconds
				Long ms = Long.parseLong(
						txt.getFileName().toString().replace(".txt", ""));
				// get file content by buffered reader
				StringBuilder content = new StringBuilder();
				try(BufferedReader reader = new BufferedReader(new FileReader(txt.toString()))){
					for(String s; (s = reader.readLine()) != null;) {
						content.append(s);
					}
				}
				
				// get file content by Files.readAllLines
//				for(String s : Files.readAllLines(txt)) {
//					content.append(s);
//				}
				
				messages.put(ms, content.toString());
			}
		}
		
		return messages;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
