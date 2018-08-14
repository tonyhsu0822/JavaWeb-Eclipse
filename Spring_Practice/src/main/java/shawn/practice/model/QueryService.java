package shawn.practice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryService {
	
	@Autowired
	UserDAO userDAO;
	
	public QueryService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<User> queryUsers(String username, String phone, String sex){
		return userDAO.userBy(username, phone, sex);
	}
}
