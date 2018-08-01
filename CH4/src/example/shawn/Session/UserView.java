package example.shawn.Session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserView
 */
@WebServlet("/UserView")
public class UserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HTML_TEMPLATE = 
			"<!DOCTYPE html>"
			+ "<html>"
			+ "	<head>"
			+ "		<meta charset='utf-8'>"
			+ "		<title>%s</title>"
			+ "	</head>"
			+ "	<body>"
			+ "		<h1>%s</h1>"
			+ "		%s"
			+ "	</body>"
			+ "</html>";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.printf(HTML_TEMPLATE, "登入成功", request.getAttribute("user"), "登入成功");
	}
}



