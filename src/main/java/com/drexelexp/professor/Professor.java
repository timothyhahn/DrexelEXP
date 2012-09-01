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
	
	public Professor() {
		this.id = -1;
		this.name = "";
	}
	public Professor(int id, String name) {
		this.id = id;
		this.name = name;
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
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public Course getCourseByCode(String courseCode) {
		for(Course c : courses) {
			if(c.getCourseCode().equals(courseCode)){ 
				return c;
			}
		}
		return null;
	}

}
