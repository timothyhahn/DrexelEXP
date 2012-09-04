package com.drexelexp.test;
/**
 * 
 */

import org.junit.Before;
import org.junit.Test;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert.*;

import com.drexelexp.course.CourseController;
/**
 * @author Lakshit
 *
 */
public class CourseTest {
	
	@Before
	public void initialize(){
		CourseController controller = new CourseController();
	}	
	
	@Test
	public void test(){
		
	}
}
