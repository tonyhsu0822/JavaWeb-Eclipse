package example.shawn.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AccountDAOFileImpl implements AccountDAO {

	private final String USERS_PATH;
	
	public AccountDAOFileImpl(String usersPath) {
		USERS_PATH = usersPath;
	}
	
	@Override
	public void createAccount(Account account) {
		Path userhome = Paths.get(USERS_PATH, account.getName());
		try {
			Files.createDirectories(userhome);
			Path profile = userhome.resolve("profile");
			try(BufferedWriter writer = Files.newBufferedWriter(profile);){
				writer.write(String.format("%s\t%s\t%s", account.getEmail(), account.getPassword(), account.getSalt()));
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public Optional<Account> accountBy(String name) {
		Path userhome = Paths.get(USERS_PATH, name);
        if(Files.notExists(userhome)) {
            return Optional.empty();
        }
        return readProfile(userhome, name);
	}
	
	public Optional<Account> readProfile(Path userhome, String name) {
		Path profile = userhome.resolve("profile");
		try(BufferedReader reader = Files.newBufferedReader(profile)){
			String[] data = reader.readLine().split("\t");
			// 0:email 1:password(encrypted) 2:salt
			String email = data[0];
			String encrypt = data[1];
			String salt = data[2];
			return Optional.of(new Account(name, email, encrypt, salt));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
