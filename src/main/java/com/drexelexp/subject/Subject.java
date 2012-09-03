package com.drexelexp.subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.course.Course;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;

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
		BaseDAO<Subject> dao = (JdbcSubjectDAO) context.getBean("subjectDAO");
		
		courses =new ArrayList<Course>();
		
				
		return courses;
	}
}
