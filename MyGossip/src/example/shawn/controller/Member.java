package example.shawn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.shawn.model.UserService;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
@ServletSecurity(
		@HttpConstraint(rolesAllowed = {"member"})
)
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String FORWARD_MEMBER_VIEW = "WEB-INF/jsp/member.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String username = (String)request.getSession().getAttribute("logined");
		UserService userService = (UserService) getServletContext().getAttribute("userService"); 
		
		request.setAttribute("messages", userService.getMessages(username));
		request.getRequestDispatcher(FORWARD_MEMBER_VIEW).forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
