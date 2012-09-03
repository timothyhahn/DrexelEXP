package com.drexelexp.subject;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;

/**
 * Model for the Subject object
 * @author
 *
 */
public class Subject {
	private int id;
	private String code;
	private String name;
	
	private List<Course> courses;
	
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
	
	public List<Course> getCourses(){
		if(courses!=null)
			return courses;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcCourseDAO dao = (JdbcCourseDAO) context.getBean("courseDAO");
		
		courses = dao.getBySubject(this);
		
				
		return courses;
	}
}
