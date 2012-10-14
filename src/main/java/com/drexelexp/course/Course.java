

package com.drexelexp.course;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.review.JdbcReviewDAO;
import com.drexelexp.review.Review;
import com.drexelexp.subject.JdbcSubjectDAO;
import com.drexelexp.subject.Subject;

/**
 * Model for the Course object
 * @author
 *
 */
public class Course {
	private int id;
	private int number;
	private String name;
	private String description;
	private int subjectId;
	private Subject subject;
	private List<Professor> professors;
	private List<Review> reviews;
	
	public Course(){
		
	}
	
	public Course(int id, int number, String name, String description, int subjectId) {
		this.id=id;
		this.number = number;
		this.name = name;
		this.description=description;
		this.subjectId=subjectId;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public String getDescription(){
		return description;
	}
	public Subject getSubject() {
		if(subject!=null)
			return subject;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Subject> dao = (JdbcSubjectDAO) context.getBean("subjectDAO");
		
		subject = dao.getById(subjectId);
		
		System.out.println(subject!=null?subject.getName():"NULL");
		
		return subject;
	}
		
	public List<Professor> getProfessors() {
		if(professors!=null)
			return professors;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcProfessorDAO dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		professors = dao.getByCourse(this);
		
		return professors;
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
		
		return new DecimalFormat("0.0").format(rating);
	}
}

