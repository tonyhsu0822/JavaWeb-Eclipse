package example.shawn.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import example.shawn.controller.Constants;

public class UserService {
	private final String PATH_USERS;
	
	public UserService(String path) {
		PATH_USERS = path;
	}
	
	public void tryCreateUser(String email, String username, String password) throws IOException{
//		Path userhome = Paths.get(PATH_USERS, username);
		File userhome2 = new File(PATH_USERS, username);
		if(!userhome2.exists() /* || Files.notExists(userhome)*/ ) {
			
			userhome2.mkdir();
			
			// encryption
			int salt = (int)(Math.random() * 100);
			String encrypt = String.valueOf(salt + password.hashCode());

			File profile2 = new File(userhome2, "profile");
			try(FileWriter fw = new FileWriter(profile2);
					BufferedWriter bw = new BufferedWriter(fw);){
				bw.write(String.format("%s\t%s\t%d", email, encrypt, salt));
			}
			
			// java.nio
//			Files.createDirectories(userhome);
//			Path profile = userhome.resolve("profile");
//			try(BufferedWriter writer = Files.newBufferedWriter(profile);){
//				writer.write(String.format("%s\t%s\t%d", email, encrypt, salt));
//			}
		}
	}
	
	public boolean login(String username, String password) throws IOException {
		if(username != null && !username.isEmpty()
				&& password != null && !password.isEmpty()) {
			
			Path userhome = Paths.get(PATH_USERS, username);
			return Files.exists(userhome) && isCorrectPassword(password, userhome); 
		}
		else {
			return false;
		}
	}
	
	private boolean isCorrectPassword(String password, Path userhome) throws IOException {
		Path profile = userhome.resolve("profile");
		try(BufferedReader reader = Files.newBufferedReader(profile)){
			String[] data = reader.readLine().split("\t");
			// 0:email 1:password(encrypted) 2:salt
			int encrypt = Integer.parseInt(data[1]);
			int salt = Integer.parseInt(data[2]);
			
			return (password.hashCode() + salt) == encrypt;
		}
	}
	
	public List<Message> getMessages(String username) throws IOException{
		Path userhome = Paths.get(Constants.PATH_USERS, username);
		// to order the latest post at the first, using reverse order
		List<Message> messages = new ArrayList<>();
		
		try(DirectoryStream<Path> textfiles = Files.newDirectoryStream(userhome, "*.txt")){
			for(Path txt : textfiles) {
				// translate filename to milliseconds
				Long ms = Long.parseLong(
						txt.getFileName().toString().replace(".txt", ""));
				// get file content by buffered reader
				StringBuilder content = new StringBuilder();
				try(BufferedReader reader = new BufferedReader(new FileReader(txt.toString()))){
					for(String s; (s = reader.readLine()) != null;) {
						content.append(s);
					}
				}
				
				// get file content by Files.readAllLines
//				for(String s : Files.readAllLines(txt)) {
//					content.append(s);
//				}
				
				messages.add(new Message(username, ms, content.toString()));
			}
		}
			
		messages.sort(Comparator.comparing(Message::getMillis).reversed());
		
		return messages;
	}
	
	public void addMessage(String username, String content) throws IOException {
		String filename = String.format("%d.txt", System.currentTimeMillis());
		Path txt = Paths.get(Constants.PATH_USERS, username, filename);
		
		try(BufferedWriter writer = Files.newBufferedWriter(txt);){
			writer.write(content);
		}
	}
	
	public void deleteMessage(String username, String ms) throws IOException {
		Path txt = Paths.get(Constants.PATH_USERS, username, String.format("%s.txt", ms));
		Files.delete(txt);
	}
}
