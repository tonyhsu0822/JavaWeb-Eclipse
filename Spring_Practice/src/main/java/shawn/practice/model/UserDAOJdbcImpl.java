package shawn.practice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAOJdbcImpl implements UserDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOJdbcImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> userBy(String username, String phone, String sex) {
		final String sql = "SELECT * FROM user_profile "
							+ "WHERE user_name = ISNULL(?, user_name)"
							+ "AND user_phone = ISNULL(?, user_phone)"
							+ "AND user_sex = ISNULL(?, user_sex)";
		
		// translate empty input to null, makes it work with SQL ISNULL function
		if(username.isEmpty()) {
			username = null;
		}
		if(phone.isEmpty()) {
			phone = null;
		}
		List<Map<String, Object>> results = 
				jdbcTemplate.queryForList(sql, new Object[] {username, phone, sex});
		
		List<User> matchedUsers = new ArrayList<>(); 
		for(Map<String, Object> row : results) {
			User user = new User();
			user.setId((String)row.get("user_id"));
			user.setUsername((String)row.get("user_name"));
			user.setSex((String)row.get("user_sex"));
			user.setPhone((String)row.get("user_phone"));
			user.setAddress((String)row.get("user_address"));
			matchedUsers.add(user);
		}
		
		return matchedUsers;
	}
	
}
