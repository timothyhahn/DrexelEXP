package com.drexelexp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import com.drexelexp.baseDAO.JdbcBaseDAO;

/**
 * Implementation of UserDAO in JDBC style.
 * @author Timothy Hahn
 *
 */
public class JdbcUserDAO extends JdbcBaseDAO<User>{
	protected String getTableName(){
		return "users";
	}
	protected String getIdColumnName(){
		return "USER_ID";
	}
	protected int getId(User instance){
		return instance.getId();
	}
	protected User parseResultSetRow(ResultSet rs) throws SQLException{
		throw new IllegalStateException("User methods are overwritten.");
	}
	protected Map<String,Object> getColumnMap(User instance){
		throw new IllegalStateException("User methods are overwritten.");
	}	
	
	@Override
	public void insert(User user) {
		String userSQL = "INSERT INTO users " +
				"(email, password, active) VALUES (?, ?,?)";
		
		String roleSQL = "INSERT INTO user_roles "+
							"(user_id, authority) values (?,?)";
		Connection conn = null;
 
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(userSQL);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setBoolean(3, user.isActive());
			ps.executeUpdate();
			ps.close();
 
			int id = findIdByEmail(user.getEmail());
			
			ps = conn.prepareStatement(roleSQL);
			ps.setInt(1, id);
			if(user.isModerator()){
				ps.setString(2, "ROLE_ADMIN");
			} else {
				ps.setString(2, "ROLE_USER");
			}
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public int findIdByEmail(String email) {
		Map<String, Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("EMAIL",email);
		
		return getWhere(conditions).get(0).getId();
	}
}
