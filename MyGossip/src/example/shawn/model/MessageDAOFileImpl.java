package example.shawn.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOFileImpl implements MessageDAO {

	private final String USERS_PATH;
	
	public MessageDAOFileImpl(String usersPath) {
		USERS_PATH = usersPath;
	}
	
	@Override
	public List<Message> messagesBy(String username) {
		Path userhome = Paths.get(USERS_PATH, username);
		
		List<Message> messages = new ArrayList<>();
		try(DirectoryStream<Path> textfiles = Files.newDirectoryStream(userhome, "*.txt")){
			for(Path txt : textfiles) {
				// translate filename to milliseconds
				Long ms = Long.parseLong(
						txt.getFileName().toString().replace(".txt", ""));
				StringBuilder content = new StringBuilder();
				for(String s : Files.readAllLines(txt)) {
					content.append(s);
				}
				messages.add(new Message(username, ms, content.toString()));
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		
		return messages;
	}

	@Override
	public void createMessage(Message message) {
		String filename = String.format("%d.txt", System.currentTimeMillis());
		Path txt = Paths.get(USERS_PATH, message.getUsername(), filename);
		
		try(BufferedWriter writer = Files.newBufferedWriter(txt);){
			writer.write(message.getContent());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public void deleteMessageBy(String username, String millis) {
		try {
			Path txt = Paths.get(USERS_PATH, username, String.format("%s.txt", millis));
			Files.delete(txt);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public List<Message> latestMessages(int n) {
		// not implemented yet
		// using jdbc implement instead
		return null;
	}

}
