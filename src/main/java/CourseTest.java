/**
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;


import com.drexelexp.course.Course;
import com.drexelexp.course.CourseController;
/**
 * @author Lakshit
 *
 */
public class CourseTest {
	
	 private MockHttpServletRequest request;
	 private MockHttpServletResponse response;
	 CourseController controller;
	 
	@Before
	public void setUp() {
	       request = new MockHttpServletRequest();
	       response = new MockHttpServletResponse();
	       controller = new CourseController();
	    }	
	
	@Test
	public void CreateCoursetest() throws Exception{
		request.setRequestURI("/course/create");
		request.setMethod("POST");
		Course course = new Course(1000,1001, "Test", null, 1002);
		request.setParameter("name", "course");
		ModelAndView mav = new AnnotationMethodHandlerAdapter().handle(request, response, controller);
		assertEquals("redirect:/course", mav.getViewName());
	}
	
	

}
