package example.shawn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestBookBean implements Serializable {
    private String jdbcUri = "jdbc:h2:tcp://localhost/c:/javaweb/eclipse-workspace/CH9-JDBC/demo";
    private String username = "shawn";
    private String password = "12345678";
    public GuestBookBean() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setMessage(Message message) {
        try(Connection conn = DriverManager.getConnection(
                jdbcUri, username, password);
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
        try(Connection conn = DriverManager.getConnection(
                                 jdbcUri, username, password);
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
