package com.drexelexp.course;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drexelexp.subject.Subject;

/**
 * Controller for the Course object
 * @author
 *
 */

@Controller
public class CourseController {
	@RequestMapping(value="/course/list", method = RequestMethod.GET)
	public String showUsers(Model model) {
		Subject s = new Subject();
		s.setShortName("CS");
		Course cs270 = new Course(s, 270, "Data Structures and Algorithms");
		Course cs281 = new Course(s, 281, "Systems Architecture I");
		Course cs283 = new Course(s, 283, "Systems Programming");
		Course cs350 = new Course(s, 350, "Software Design");
		
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(cs270);
		courses.add(cs281);
		courses.add(cs283);
		courses.add(cs350);
		
		model.addAttribute("courses",courses);
		
		return "course/list";
	}
}
