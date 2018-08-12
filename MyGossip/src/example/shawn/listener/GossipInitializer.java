package example.shawn.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import example.shawn.model.AccountDAO;
import example.shawn.model.AccountDAOFileImpl;
import example.shawn.model.AccountDAOJdbcImpl;
import example.shawn.model.MessageDAO;
import example.shawn.model.MessageDAOFileImpl;
import example.shawn.model.MessageDAOJdbcImpl;
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
    	
    	DataSource dataSource = getDataSource();
    	AccountDAO accountDAO = new AccountDAOJdbcImpl(dataSource);
    	MessageDAO messageDAO = new MessageDAOJdbcImpl(dataSource);
    	
    	ServletContext context = sce.getServletContext();
//         String usersPath = context.getInitParameter("users-path");
//         AccountDAO accountDAO = new AccountDAOFileImpl(usersPath);
//         MessageDAO messageDAO = new MessageDAOFileImpl(usersPath);
    	context.setAttribute("userService", new UserService(accountDAO, messageDAO));
    }
	
    private DataSource getDataSource() {
    	try {
    		Context initContext = new InitialContext();
    		Context envContext = (Context) initContext.lookup("java:/comp/env");
    		return (DataSource) envContext.lookup("jdbc/gossip");
    	} catch (NamingException e) {
    		throw new RuntimeException(e);
    	}
    }
    
}
