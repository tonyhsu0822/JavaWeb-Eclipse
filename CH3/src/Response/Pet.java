package Response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Pet
 */
@WebServlet("/pet")
public class Pet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set encoding type of reqeust and response
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
		
		// get name and prefered pet types
		String name = request.getParameter("user");
		String[] types = request.getParameterValues("type");
		
		// print html
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<body>"
				+ "名字:" + name
				+ "<br>喜歡的寵物類型"
				+ "<ul>");
		
		// print pet type list
		for(String type: types) {
			out.print("<li>" + type + "</li>");
		}
		
		// print remaining html
		out.print("</ul>"
				+ "</body>"
				+ "</html>");
	}

}
