package example.shawn.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageDAOJdbcImpl implements MessageDAO {

	@Autowired
	private DataSource dataSource;
	
	public MessageDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Message> messagesBy(String username) {
		final String sql = "SELECT * FROM t_message WHERE name = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			List<Message> messages = new ArrayList<>(); 
			while (rs.next()) {
				Message msg = new Message(rs.getString(1), rs.getLong(2), rs.getString(3));
				messages.add(msg);
			}
			
			return messages;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createMessage(Message message) {
		final String sql = "INSERT INTO t_message (name, time, content) VALUES (?, ?, ?)";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
		
			stmt.setString(1, message.getUsername());
			stmt.setLong(2, message.getMillis());
			stmt.setString(3, message.getContent());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteMessageBy(String username, String millis) {
		final String sql = "DELETE FROM t_message WHERE name = ? AND time = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, username);
			stmt.setLong(2, Long.parseLong(millis));
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Message> latestMessages(int n) {
		final String sql = "SELECT * FROM t_message ORDER BY time DESC LIMIT ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, n);
			ResultSet rs = stmt.executeQuery();
			
			List<Message> messages = new ArrayList<>();
			while(rs.next()) {
				Message message = new Message(rs.getString(1), rs.getLong(2), rs.getString(3));
				messages.add(message);
			}
			return messages; 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
