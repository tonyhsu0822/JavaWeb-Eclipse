package example.shawn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DbBean implements Serializable {

	private DataSource dataSource;
	
//	private String jdbcUri;
//	private String username;
//	private String password;
	
	public DbBean() {
		try {
//			Class.forName("org.h2.Driver");
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/demo");
			
		} catch(NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isConnectedOK() {
		try(Connection conn = dataSource.getConnection()){
			return !conn.isClosed();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
//	public void setJdbcUri(String jdbcUri) {
//		this.jdbcUri = jdbcUri;
//	}
//	
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	
//	public void setPassword(String password) {
//		this.password = password;
//	}
}
