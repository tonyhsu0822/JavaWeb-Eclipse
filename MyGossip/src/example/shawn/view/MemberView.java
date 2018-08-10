package example.shawn.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberView
 */
@WebServlet(name = "Member.view", urlPatterns = { "/Member.view" })
public class MemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String REDIRECT_INDEX = "index.html";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prevent user manually typing URL to access this Servlet
		// redirect those who didn't got "logined" token to index
		if(request.getSession().getAttribute("logined") == null) {
			response.sendRedirect(REDIRECT_INDEX);
			return;
		}
		
		String username = (String)request.getSession().getAttribute("logined");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Gossip 微網誌</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");

        // left panel
        out.println("<div class='leftPanel'>");
        out.println("<img src='images/血小板.jpg' alt='血小板' height=200 width=200/><br><br>");
        out.printf("<a href='Logout'>登出 %s</a>", username);
        out.println("</div>");
        
        // new massage post
        out.println("<form method='post' action='NewMessage'>");
        out.println("分享新鮮事...<br>");
        // 如果訊息長度超過限制，會再forward回來，可以從"content" Param得到原本的訊息
        String preContent = request.getParameter("content");
        if(preContent == null) {
            preContent = "";
        }
        else { // 上次送出訊息長度超過限制
            out.println("訊息要 140 字以內<br>");
        }
        
        out.printf("<textarea cols='60' rows='4' name='content'>%s</textarea><br>", preContent);
        out.println("<button type='submit'>送出</button>");
        out.println("</form>");
        
        // previous posted message
        out.println("<table border='0' cellpadding='2' cellspacing='2'>");
        out.println("<thead>");
        out.println("<tr><th><hr></th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        Map<Long, String> messages = (Map<Long, String>) request.getAttribute("messages");
        messages.forEach((millis, content) -> {
            LocalDateTime dateTime = 
                    Instant.ofEpochMilli(millis)
                           .atZone(ZoneId.of("Asia/Taipei"))
                           .toLocalDateTime();
            
            out.println("<tr><td style='vertical-align: top;'>");
            out.printf("%s<br>", username);
            out.printf("%s<br>", content);
            out.println(dateTime);
            out.println("<form method='post' action='DelMessage'>");
            out.printf("<input type='hidden' name='millis' value='%s'>", millis);
            out.println("<button type='submit'>刪除</button>");
            out.println("</form>");
            out.println("<hr></td></tr>");
        });

        
        out.println("</tbody>");
        out.println("</table>");
//        out.println("<hr>");
        out.println("</body>");
        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
