package com.drexelexp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import javax.sql.DataSource;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.JdbcDAO;

/**
 * Implementation of UserDAO in JDBC style.
 * @author Timothy Hahn
 *
 */
public class JdbcUserDAO extends JdbcDAO<User> implements BaseDAO<User>{
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
		//TODO
		return null;
	}
	protected Dictionary<String,Object> getColumnMap(User instance){
		//TODO
		return null;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList();
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
		String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
		 
		Connection conn = null;
		int id = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("USER_ID");
			}
			rs.close();
			ps.close();
			return id;
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
}
