package example.shawn.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {

	private DataSource dataSource;
//	private JdbcRowSet rowset;
	
	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void createAccount(Account account) {
		final String sql = "INSERT INTO t_account (name, email, password, salt) VALUES (?, ?, ?, ?)";
		final String sql2 = "INSERT INTO t_account_role (name, role) VALUES (?, 'member')";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				PreparedStatement stmt2 = conn.prepareStatement(sql2)){
			
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getEmail());
			stmt.setString(3, account.getPassword());
			stmt.setString(4, account.getSalt());
			
			stmt2.setString(1, account.getName());
			
			stmt.executeUpdate();
			stmt2.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<Account> accountBy(String name) {
		final String sql = "SELECT * FROM t_account WHERE name = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Account account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				return Optional.of(account);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
