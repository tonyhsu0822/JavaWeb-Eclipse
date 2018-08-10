package example.shawn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import example.shawn.model.AccountDAO;
import example.shawn.model.AccountDAOFileImpl;
import example.shawn.model.MessageDAO;
import example.shawn.model.MessageDAOFileImpl;
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
         AccountDAO accountDAO = new AccountDAOFileImpl(usersPath);
         MessageDAO messageDAO = new MessageDAOFileImpl(usersPath);
         context.setAttribute("userService", new UserService(accountDAO, messageDAO));
    }
	
}
