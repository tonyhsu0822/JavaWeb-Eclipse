package example.shawn.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterSuccess
 */
@WebServlet("/RegisterSuccess.view")
public class RegisterSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String HTML_TITLE = "Register Success";
	private static final String HTML_HEADING = "成功送出表單";
	private static final String HTML_LINK_INDEX = "<a href='index.html'>回首頁</a>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.printf(Constants.HTML_TEMPLATE_H1, HTML_TITLE, HTML_HEADING, HTML_LINK_INDEX);
	}

}
