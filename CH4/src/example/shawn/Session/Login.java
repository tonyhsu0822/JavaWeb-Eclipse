package example.shawn.Session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if("caterpillar".equals(name) && "123456".equals(password)) {
			Cookie cookie = new Cookie("user", "caterpillar");
			if(request.getParameter("auto").equals("true")) {
				cookie.setMaxAge(60 * 60 * 24); // =1天 (單位:秒)
			}
			// if cookie wasn't set max age,
			// the default life duration last until browser closed
			response.addCookie(cookie);
			response.sendRedirect("User");
		} else { // login failed
			response.sendRedirect("login.html");
		}
	}

}
