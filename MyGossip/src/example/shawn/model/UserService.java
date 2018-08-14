package example.shawn.model;

import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	@Autowired
	private final AccountDAO accountDAO;
	@Autowired
	private final MessageDAO messageDAO;
	
	public UserService(AccountDAO accountDAO, MessageDAO messageDAO) {
		this.accountDAO = accountDAO;
		this.messageDAO = messageDAO;
	}
	
	public void tryCreateUser(String email, String username, String password) throws IOException{
		if(!accountDAO.accountBy(username).isPresent()) {			
			// encryption
			int salt = (int)(Math.random() * 100);
			String encrypt = String.valueOf(salt + password.hashCode());
			
			accountDAO.createAccount(
					new Account(username, email, encrypt, String.valueOf(salt)));
		}
	}
	
	public boolean login(String username, String password) throws IOException {
		if(username != null && !username.isEmpty()
				&& password != null && !password.isEmpty()) {
			Optional<Account> optionalAccount = accountDAO.accountBy(username);
			if(optionalAccount.isPresent()) {
				return isCorrectPassword(password, optionalAccount.get());
			}
		}
		return false;
	}
	
	private boolean isCorrectPassword(String password, Account account) throws IOException {
		int encrypt = Integer.parseInt(account.getPassword());
		int salt = Integer.parseInt(account.getSalt());
		
		return (password.hashCode() + salt) == encrypt;
	}
	
	public boolean exist(String username) {
		return accountDAO.accountBy(username).isPresent();
	}
	
	public List<Message> getMessages(String username) throws IOException{
		List<Message> messages = messageDAO.messagesBy(username);
		messages.sort(Comparator.comparing(Message::getMillis).reversed());
		return messages;
	}
	
	public void addMessage(String username, String content) throws IOException {
		messageDAO.createMessage(
				new Message(username, Instant.now().toEpochMilli(), content));
	}
	
	public void deleteMessage(String username, String millis) throws IOException {
		messageDAO.deleteMessageBy(username, millis);
	}

	public List<Message> latestMessages(int n) {
		return messageDAO.latestMessages(n);
	}
}
