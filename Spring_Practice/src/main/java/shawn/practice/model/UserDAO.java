package shawn.practice.model;

import java.util.List;

public interface UserDAO {
	List<User> userBy(String username, String phone, String sex);
}
