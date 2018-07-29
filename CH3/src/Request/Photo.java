package Request;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Photo
 */
@MultipartConfig(location="c:/javaweb/myDownload")
@WebServlet("/photo")
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Photo() {
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
		// deal with Chinese file name
		request.setCharacterEncoding("utf-8");
		
		Part photo = request.getPart("photo");
		
		// TODO get filename
		String filename = getSubmittedFileName(photo);
		
		// write file with [write] method of class [Part]
		// hard-coding the filename now
		photo.write(filename);
		
		// print html with content "Dowmload complete"
		response.getWriter().print("<html>" + 
				"<head>" + 
				"<meta charset=\"UTF-8\">" + 
				"<title>upload photo</title>" + 
				"</head>" + 
				"<body>" + 
				"<h1>Download completed</h1>" +
				"<a href=photo.html>back</a>" +
				"</body>" + 
				"</html>");
	}

	private String getSubmittedFileName(Part part) {
		
		String header = part.getHeader("Content-Disposition");
		
		// regex: filename="(.*)" -> match [filename=xxxxx],
		// but get only [xxxxx] part through method [group]
		Pattern filenameRegex = Pattern.compile("filename=\"(.*)\"");
		Matcher matcher = filenameRegex.matcher(header);
		matcher.find();
		// get first matching pattern
		String filename = matcher.group(1);
		
		// get filename from absolutive path if it is
		if(filename.contains("\\")){
			return filename.substring(filename.lastIndexOf("\\"));
		}
		return filename;
	}
}






