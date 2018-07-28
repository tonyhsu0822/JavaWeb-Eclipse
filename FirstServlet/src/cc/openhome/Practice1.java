package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Practice1
 */
@WebServlet("/practice1")
public class Practice1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Practice1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		// get client's ip address
		String addr = request.getRemoteAddr();
		
		// get access time from session of request
		HttpSession httpSession = request.getSession();
		long time = httpSession.getLastAccessedTime();
		Date d = new Date(time);
		
		// get query string
		String query = request.getQueryString();
		if(query == null)
			query = "no query";
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Result</title>");
		out.print("<body>");
		out.print("<h1>");
		out.print("Your IP address: " + addr);
		out.print("</h1>");
		out.print("<h1>");
		out.print("Request time: " + d);
		out.print("</h1>");
		out.print("<h1>");
		out.print("Query string: " + query);
		out.print("</h1>");
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
