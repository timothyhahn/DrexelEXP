package com.drexelexp.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.subject.JdbcSubjectDAO;
import com.drexelexp.subject.Subject;

public class SectionListing {
	private static final Logger logger = LoggerFactory.getLogger(SectionListing.class);
	
	private String subjectCode;
	private int courseNumber;
	private String professorName;
	
	private JdbcProfessorDAO _professorDAO;
	private JdbcProfessorDAO getProfessorDAO(){
		if(_professorDAO!=null)
			return _professorDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		return _professorDAO;
	}
	
	private JdbcCourseDAO _courseDAO;
	private JdbcCourseDAO getCourseDAO(){
		if(_courseDAO!=null)
			return _courseDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		return _courseDAO;
	}
	
	private JdbcSubjectDAO _subjectDAO;
	private JdbcSubjectDAO getSubjectDAO(){
		if(_subjectDAO!=null)
			return _subjectDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");
		
		return _subjectDAO;
	}
	
	public SectionListing(String subjectCode,int courseNumber,Element htmlElement){
		NodeList cells = htmlElement.getElementsByTagName("TD");		
		
		this.subjectCode=subjectCode;
		this.courseNumber=courseNumber;
		String text= ((Element)cells.item(8)).getTextContent();
		if(text.contains(","))
			this.professorName =  text.split(",")[0];
		else
			this.professorName = text;
		
		if(this.professorName.equals("STAFF"))
			return;
		
		Professor professor = getProfessorDAO().getByName(professorName);
		if(professor==null){
			logger.info("Ingesting Professor: "+professorName);
			getProfessorDAO().insert(new Professor(0,professorName));
			professor = getProfessorDAO().getByName(professorName);
		}
		
		Subject subject = getSubjectDAO().getByCode(subjectCode);
		Course course = getCourseDAO().getByCode(subject,courseNumber);
		
		if(professor!=null && course!=null){
			getProfessorDAO().addProfessorCourse(professor,course);
		}
	}
	
	public String getSubjectCode(){
		return subjectCode;
	}
	public int getCourseNumber(){
		return courseNumber;
	}
	public String getProfessorName(){
		return professorName;
	}
}
