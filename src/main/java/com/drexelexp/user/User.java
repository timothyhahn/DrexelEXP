package com.drexelexp.user;
/**
 * Model for the User object
 * @author Timothy Hahn
 *
 */
public class User {
	private int id;
	private String email;
	private String name = "";
	private String password = ""; //temp thing
	private String university;
	private boolean active;
	private boolean moderator;

	/**
	 * Default Constructor
	 * Purpose: Should never be used
	 */
	public User() {
		this.setId(-1);
		this.setEmail("");
		this.setUniversity("");
		this.setActive(false);
		this.setModerator(false);
	}
	/**
	 * Minimal Constructor
	 * Purpose: The bare minimum for an active user
	 * @param id Auto generated ID
	 * @param email User's e-mail
	 */
	public User(int id, String email) {
		this.setId(id);
		this.setEmail(email);
		this.setUniversity(findUniversity(email));
		this.setActive(true);
		this.setModerator(false);
	}
	
	/**
	 * Helper Functions
	 */
	
	/**
	 * Finds the university name from the e-mail given.
	 * @param email
	 * @return
	 */
	private String findUniversity(String email) {
		return "";
	}
	
	/**
	 * Accesors and Mutators
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public boolean isModerator() {
		return moderator;
	}
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
