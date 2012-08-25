package com.drexelexp.user;
/**
 * DAO interface for the User object
 * @author Timothy Hahn
 *
 */
import com.drexelexp.user.User;

public interface UserDAO {
	public void insert(User user);
	public User findByUserId(int userId);
}
