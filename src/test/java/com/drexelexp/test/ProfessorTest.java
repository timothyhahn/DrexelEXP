package com.drexelexp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
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
		
		ModelAndView mav = new AnnotationMethodHandlerAdapter().handle(request, response, controller);
		assertEquals("redirect:/professor", mav.getViewName());
	}

}
