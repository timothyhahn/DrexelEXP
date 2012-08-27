package com.drexelexp.course;

import com.drexelexp.subject.Subject;

/**
 * Model for the Course object
 * @author
 *
 */
public class Course {
	public Subject subject;
	public int number;
	public String name;
	
	public Course() {
		
	}
	public Course(Subject subject, int number, String name) {
		this.subject = subject;
		this.number = number;
		this.name = name;
	}
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
