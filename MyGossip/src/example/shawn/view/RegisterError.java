package example.shawn.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterError
 */
@WebServlet("/RegisterError.view")
public class RegisterError extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String HTML_TITLE = "Illegal Form";
	private static final String HTML_HEADING = "表單填寫錯誤";
	private static final String HTML_LINK_REGISTER = "<a href='register.html'>重新註冊</a>";
	private static final String HTML_LINK_INDEX = "<a href='index.html'>回首頁</a>";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterError() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> errorList = (List<String>)request.getAttribute("errorlist");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		for(String s : errorList) {
			sb.append("<li>")
				.append(s)
				.append("</li>");
		}
		sb.append("</ul>")
			.append(HTML_LINK_REGISTER)
			.append("<br>")
			.append(HTML_LINK_INDEX);
		
		out.printf(Constants.HTML_TEMPLATE_H1, HTML_TITLE, HTML_HEADING, sb.toString());
	}

}




