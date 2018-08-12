package example.shawn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GuestBookBean implements Serializable {
	
    private DataSource dataSource;
    
    public GuestBookBean() {
        try {
//            Class.forName("org.h2.Driver");
        	Context initContext = new InitialContext();
        	Context envContext = (Context) initContext.lookup("java:/comp/env");
        	dataSource = (DataSource) envContext.lookup("jdbc/demo");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setMessage(Message message) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preStat = conn.prepareStatement(
            		"INSERT INTO t_message(name, email, msg) VALUES (?, ?, ?)");) {

        	preStat.setString(1, message.getName());
        	preStat.setString(2, message.getEmail());
        	preStat.setString(3, message.getMsg());
        	
            preStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public List<Message> getMessages() {
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM t_message");
            List<Message> messages = new ArrayList<>();
            while (result.next()) {
                Message message = new Message();
                message.setId(result.getLong(1));
                message.setName(result.getString(2));
                message.setEmail(result.getString(3));
                message.setMsg(result.getString(4));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
}
