package example.shawn.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 * Servlet Filter implementation class HtmlSanitizer
 */
@WebFilter("/NewMessage")
public class HtmlSanitizer extends HttpFilter {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public HtmlSanitizer() {
        super();
        // TODO Auto-generated constructor stub
    }

    private PolicyFactory policy;
    
    @Override
    public void init() throws ServletException{
    	policy = new HtmlPolicyBuilder()
    				.allowElements("a", "b", "i", "del", "pre", "code")
    				.allowUrlProtocols("http", "https")
    				.allowAttributes("href").onElements("a")
    				.requireRelNofollowOnLinks()
    				.toFactory();
    }
    
    private class SanitizerWrapper extends HttpServletRequestWrapper {
		public SanitizerWrapper(HttpServletRequest request) {
			super(request);
		}
    	
		@Override
		public String getParameter(String name) {
			String str;
			if((str = getRequest().getParameter(name)) != null) {
				return policy.sanitize(str);
			} else {
				return null;
			}
		}
    }
    
    @Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new SanitizerWrapper(request), response);
	}


}
