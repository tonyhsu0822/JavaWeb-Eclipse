package example.shawn.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("example.shawn.model")
public class RootConfig {
	@Bean
	public DataSource getDataSource() {
    	try {
    		Context initContext = new InitialContext();
    		Context envContext = (Context) initContext.lookup("java:/comp/env");
    		return (DataSource) envContext.lookup("jdbc/gossip");
    	} catch (NamingException e) {
    		throw new RuntimeException(e);
    	}
    }
}
