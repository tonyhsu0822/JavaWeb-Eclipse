package example.shawn.SessionAPI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/Login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if("caterpillar".equals(name) && "123456".equals(password)){
			// not understand here, security issue?
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			HttpSession session = request.getSession();
			session.setAttribute("logined", name);	
			response.sendRedirect("User2");
		} else {
			response.sendRedirect("login2.html");
		}
	}

}
