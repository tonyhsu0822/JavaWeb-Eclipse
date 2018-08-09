package example.shawn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import example.shawn.model.UserService;

/**
 * Application Lifecycle Listener implementation class GossipInitializer
 *
 */
@WebListener
public class GossipInitializer implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext context = sce.getServletContext();
         String usersPath = context.getInitParameter("users-path");
         context.setAttribute("userService", new UserService(usersPath));
    }
	
}
