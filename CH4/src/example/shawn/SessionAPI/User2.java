package example.shawn.SessionAPI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class User2
 */
@WebServlet("/User2")
public class User2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object token = session.getAttribute("logined");
		
		// if "logined" attribute existed, means the user already logined, forward to user page
		// else, redirect to login page
		if(token != null) {
			request.getRequestDispatcher("UserView2").forward(request, response);
		} else {
			response.sendRedirect("login2.html");
		}
	}
}
