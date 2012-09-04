package com.drexelexp.professor;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.review.JdbcReviewDAO;
import com.drexelexp.review.Review;

/**
 * Model for the Professor object
 * 
 * @author
 * 
 */
public class Professor {
	private int id;
	private String name;
	private List<Course> courses;
	private List<Review> reviews;
	
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
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcCourseDAO dao = (JdbcCourseDAO) context.getBean("courseDAO");
		
		courses = dao.getByProfessor(this);
		
		return courses;
	}
	public List<Review>	getReviews(){
		if(reviews!=null)
			return reviews;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcReviewDAO dao = (JdbcReviewDAO) context.getBean("reviewDAO");
		
		reviews = dao.getReviews(this);
		
		return reviews;
	}
	public float getRating(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcReviewDAO dao = (JdbcReviewDAO) context.getBean("reviewDAO");
		
		return dao.getRating(this);
	}
	
	public String getRatingString(){
		float rating = getRating();
		
		return new DecimalFormat("#.#").format(rating);
	}
}
