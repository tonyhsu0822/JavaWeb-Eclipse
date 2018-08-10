package example.shawn.model;

import java.util.Optional;

public interface AccountDAO {
	void createAccount(Account account);
	Optional<Account> accountBy(String name);
}
