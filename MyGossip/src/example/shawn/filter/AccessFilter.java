package example.shawn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter(
		filterName = "/AccessFilter",
		urlPatterns = {
				"/Member", "/Member.view",
				"/NewMessage", "/DelMessage",
				"/Logout"
		},
		initParams = {
				@WebInitParam(name = "REDIRECT_INDEX", value = "index.html")
		})
public class AccessFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AccessFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	private String REDIRECT_INDEX;
	
	@Override
	public void init() throws ServletException{
		REDIRECT_INDEX = getInitParameter("REDIRECT_INDEX");
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getSession().getAttribute("logined") == null) {
			response.sendRedirect(REDIRECT_INDEX);
		} else {
			chain.doFilter(request, response);
		}
	}
}
