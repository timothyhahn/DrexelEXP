package com.drexelexp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.professor.ProfessorController;

public class ProfessorTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ProfessorController controller;

    @Before
    public void setUp() {
       request = new MockHttpServletRequest();
       response = new MockHttpServletResponse();
       controller = new ProfessorController();
    }

	@Test
	public void createProfessorTest() throws Exception {
		request.setRequestURI("/professor/create");
		request.setMethod("POST");
		Professor professor = new Professor(9999, "Professor Test");
		request.setAttribute("professor", professor);
		
		ModelAndView mav = new AnnotationMethodHandlerAdapter().handle(request, response, controller);
		assertEquals("redirect:/professor", mav.getViewName());
		
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		Professor result = ((JdbcProfessorDAO)professorDAO).getById(9999);
		
		assertEquals("Professor Test", result.getName());
		
		((JdbcProfessorDAO)professorDAO).delete(professor);
	}

}
