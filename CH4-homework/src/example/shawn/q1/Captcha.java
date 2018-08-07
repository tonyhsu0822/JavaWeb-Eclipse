package example.shawn.q1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Captcha
 */
@WebServlet("/captcha")
public class Captcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String HTML_TEMPLATE_H1 = 
			"<!DOCTYPE html>"
			+ "<html>"
			+ "	<head>"
			+ "		<meta charset='utf-8'>"
			+ "		<title>%s</title>"
			+ "	</head>"
			+ "	<body>"
			+ "		<h1>%s</h1>"
			+ "		%s"
			+ "	</body>"
			+ "</html>";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Captcha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // generate captcha through GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		
		int randomNum = (int)(Math.random() * 10000);
		String captcha = String.format("%04d", randomNum);
		
		request.getSession().setAttribute("captcha", captcha);
		ImageIO.write(generateCaptcha(captcha), "JPG", response.getOutputStream());
	}

	// 一些 Java 2D 的東西，作用為依傳入的數字產生圖片
    private BufferedImage generateCaptcha(String password) throws IOException {
        BufferedImage bufferedImage =
                 new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("標楷體", Font.BOLD, 16));
        g.drawString(password, 10, 15);
        return bufferedImage;
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // validate captcha through POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String captcha = (String)request.getSession().getAttribute("captcha");
		String number = request.getParameter("number");
		
		PrintWriter out = response.getWriter();
		String s;
		if(captcha.equals(number)) {
			s = "驗證碼正確";
		} else {
			s = "驗證碼錯誤";
		}
		out.printf(HTML_TEMPLATE_H1, s, s, "");
	}

}
