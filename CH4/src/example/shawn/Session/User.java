package example.shawn.Session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		// find the cookie recorded the user
		boolean autoLogin = false;
		Cookie userCookie = null;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("user".equals(cookie.getName()) 
						&& "caterpillar".equals(cookie.getValue())) {
					autoLogin = true;
					userCookie = cookie;
					break;
				}
			}
		}
		
		// cookie exists, forward to user page
		// else, redirect to login page
		if(autoLogin && userCookie != null) {
			request.setAttribute(userCookie.getName(), userCookie.getValue());
			request.getRequestDispatcher("UserView").forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
