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
	private List<Course> courses;;
	
	public Professor() {
		this.id = -1;
		this.name = "";
	}
	public Professor(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id){
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public String getName(){
		return name;
	}
	public List<Course> getCourses(){
		if(courses!=null)
			return courses;
		
		//TODO
		
		return null;
	}
}
