package com.drexelexp.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.subject.JdbcSubjectDAO;
import com.drexelexp.subject.Subject;

public class CourseTest {
	private Subject subject;
	private Course course;
	private ApplicationContext context;
	
	@Before
	public void initializeSubjectAndCourse() {
		Subject baseSubject = new Subject(-1, "TEST", "TEST");
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Subject>subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");
		((JdbcSubjectDAO)subjectDAO).insert(baseSubject);
		
		subject = ((JdbcSubjectDAO)subjectDAO).getByCode("TEST");
		
		Course baseCourse = new Course(-1, 1, "Test Course", "Blah", subject.getId());		
		BaseDAO<Course>courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		((JdbcCourseDAO)courseDAO).insert(baseCourse);
		
		course = ((JdbcCourseDAO)courseDAO).getByCode(subject, 1);
	}

	@Test
	public void getByCode() {
		BaseDAO<Course>courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		Course testCourse = ((JdbcCourseDAO)courseDAO).getByCode(subject, 1);
		assertEquals(course.getName(), testCourse.getName());
	}
	
	@Test
	public void getBySubject() {
		BaseDAO<Course>courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		List<Course> courses = ((JdbcCourseDAO)courseDAO).getBySubject(subject);
		
		boolean found = false;
		for(Course testCourse : courses) {
			if(testCourse.getName().equals(course.getName())) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void getByProfessor() throws Exception {
		Professor baseProfessor = new Professor(-1, "Professor Test");
	
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		((JdbcProfessorDAO)professorDAO).insert(baseProfessor);
		
		Professor professor = ((JdbcProfessorDAO)professorDAO).getByName("Professor Test");
		
		((JdbcProfessorDAO)professorDAO).addProfessorCourse(professor, course);
		
		BaseDAO<Course>courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		List<Course> courses = ((JdbcCourseDAO)courseDAO).getByProfessor(professor);
		
		boolean found = false;
		for(Course testCourse : courses) {
			if(testCourse.getName().equals(course.getName())) {
				found = true;
				break;
			}
		}
		assertTrue(found);
		
		((JdbcProfessorDAO)professorDAO).delete(professor);
	}
	
	@After
	public void cleanupSubjectAndCourse() {
		BaseDAO<Subject>subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");		
		BaseDAO<Course>courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		((JdbcCourseDAO)courseDAO).delete(course);
		((JdbcSubjectDAO)subjectDAO).delete(subject);
	}
}
