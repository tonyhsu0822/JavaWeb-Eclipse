package example.shawn.model;

import java.util.List;

public interface MessageDAO {
	List<Message> messagesBy(String username);
	void createMessage(Message message);
	void deleteMessageBy(String username, String millis);
	List<Message> latestMessages(int n); 
}
