package com.drexelexp.review;

import com.drexelexp.course.Course;
import com.drexelexp.professor.Professor;

/**
 * Model for the Professor object
 * 
 * @author
 * 
 */
public class Review {
	private int id;
	private String data;
	private int rating;
	private Professor professor;
	private Course course;
	
	public Review() {
		this.id = -1;
		this.data = "";
		this.rating = 0;
		this.professor = null;
		this.course = null;
	}
	
	public Review(int id, String data, int rating, Professor professor, Course course) {
		this.id = id;
		this.data = data;
		this.rating = rating;
		this.professor = professor;
		this.course = course;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
}
