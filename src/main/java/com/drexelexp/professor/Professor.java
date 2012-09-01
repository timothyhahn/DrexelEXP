package com.drexelexp.professor;

import java.util.ArrayList;
import java.util.List;

import com.drexelexp.course.Course;

/**
 * Model for the Professor object
 * 
 * @author
 * 
 */
public class Professor {
	private int id;
	private String name;
	private List<Course> courses = new ArrayList<Course>();
	
	public Professor(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}
		
	public void setCourses(List<Course> courses) {
			this.courses = courses;
	}

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
