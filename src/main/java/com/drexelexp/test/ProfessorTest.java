package com.drexelexp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.professor.ProfessorController;

public class ProfessorTest {

	@Test
	public void createProfessorTest() throws Exception {
		Professor professor = new Professor(-1, "Professor Test");
		
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		((JdbcProfessorDAO)professorDAO).insert(professor);
		
		Professor result = ((JdbcProfessorDAO)professorDAO).getByName("Professor Test");
		((JdbcProfessorDAO)professorDAO).delete(result);
	}

}
