package com.drexelexp.subject;
/**
 * Model for the Subject object
 * @author
 *
 */
public class Subject {
	private int id;
	private String code;
	private String name;
	
	public Subject(int id, String code, String name) {
		this.id=id;
		this.code=code;
		this.name=name;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
