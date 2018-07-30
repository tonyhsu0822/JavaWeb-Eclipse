package Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadPdf
 */
@WebServlet("/downloadpdf")
public class DownloadPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String THE_PASSWORD = "123456";
	private static final String filepath = "/WEB-INF/eCertificate.pdf";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get password
		String passwd = request.getParameter("passwd");
		
		if(passwd.equals(THE_PASSWORD)){
			writePdf(response);
		}
		else {
			generateHtml(response);
		}
	}
	
	private void writePdf(HttpServletResponse response) throws IOException{
		// if the browser can read pdf, then the file will be opened with pdf reader,
		// otherwise the user may be asked to download the file
		response.setContentType("application/pdf");
		
		try(InputStream in = getServletContext().getResourceAsStream(filepath);
				OutputStream out = response.getOutputStream();){
			
			byte[] buffer = new byte[1024];
			int length = -1;
			while((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			
		}
	}
	
	// called when password is wrong
	private void generateHtml(HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
	
		out.print("<html>"
				+ "<body>"
				+ "<h1>Wrong Password</h1>"
				+ "</body>"
				+ "</html>");
	}

}
