package com.drexelexp.course;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.baseDAO.BaseDAO;

/**
 * Controller for the Course object
 * @author
 *
 */


@Controller
public class CourseController {

	@RequestMapping(value="/course/add", method = RequestMethod.GET)
	public ModelAndView addCourse() {
		return new ModelAndView("course/add", "command", new Course());
	}
	
	@RequestMapping(value="/course/create",method = RequestMethod.POST)
	public ModelAndView createCourse(@ModelAttribute("course") Course course, ModelMap model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		courseDAO.insert(course);
		return new ModelAndView("redirect:../");
	}

	
	@RequestMapping(value="/course/edit", method = RequestMethod.GET)
	public String listEditCourse(Model model) {
		ArrayList<Course> courses = null;
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		courses = ((JdbcCourseDAO) courseDAO).getAll();
		
		model.addAttribute("courses", courses);
		return "course/edit/list";
	}
	
	@RequestMapping(value="/course/edit/{courseID}}", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable String courseID, Model model) {
		System.out.println("ID: " + courseID);
		ApplicationContext context =
				new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		Course course = new Course();
		course= ((JdbcCourseDAO) courseDAO).getById(Integer.parseInt(courseID));
		model.addAttribute("course",course);
		return new ModelAndView("course/edit", "command", course);
	}
	
	@RequestMapping(value="/course/edit/{courseID}",method = RequestMethod.POST)
	public ModelAndView updateCourse(@ModelAttribute("course") Course course, @PathVariable String courseID){
		course.setId(Integer.parseInt(courseID));
		ApplicationContext context=
				new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		((JdbcCourseDAO) courseDAO).edit(course);
		return new ModelAndView("redirect:../");
		
	}
	
	@RequestMapping(value="course/delete", method = RequestMethod.GET)
	public String listDeleteCourse(Model model) {
		ArrayList<Course> courses = new ArrayList<Course>();
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		courses = ((JdbcCourseDAO) courseDAO).getAll();
		model.addAttribute("courses", courses);
		
		return "delete/list";
	}
	
	@RequestMapping(value="/course/delete/{courseID}", method = RequestMethod.GET) 
	public ModelAndView deleteCourse(@PathVariable String courseID, Model model) {
		System.out.println("ID: " + courseID);
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		Course course = new Course();
		course.setId(Integer.parseInt(courseID));
		course = ((JdbcCourseDAO) courseDAO).getById(course.getId());
		model.addAttribute("course", course);
		return new ModelAndView("course/delete/confirm", "command", course);
	}
	
	@RequestMapping(value="/course/delete/{courseID}", method = RequestMethod.POST)
	public ModelAndView removeCourse(@ModelAttribute("course") Course course, @PathVariable String courseID) {
		course.setId(Integer.parseInt(courseID));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		((JdbcCourseDAO)courseDAO).delete(course);
		return new ModelAndView("redirect:../");
	}
	
	@RequestMapping(value="/course", method = RequestMethod.GET)
	public String showUsers(Model model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses = ((JdbcCourseDAO) courseDAO).getAll();
		model.addAttribute("courses", courses);
		
		
		return "course/list";
	}
	
	@RequestMapping(value="/course/show/{courseID}", method = RequestMethod.GET)
	public String show(@PathVariable String courseID, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Course> dao = (JdbcCourseDAO) context.getBean("courserDAO");
		
		model.addAttribute("course",dao.getById(Integer.parseInt(courseID)));
		
		return "course/show";
	}
	
	@RequestMapping(value="/course/search", method = RequestMethod.GET)
	public ModelAndView searchForCourse() {
		return new ModelAndView("course/search", "command", new Course());
		
	}
	@RequestMapping(value="/course/search", method = RequestMethod.POST)
	public String showSearchResults(@ModelAttribute("name") Course c, Model model) {
		String query = c.getName();
		
		ArrayList<String> queryList = new ArrayList<String>(Arrays.asList(query.split(" ")));
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcCourseDAO dao = (JdbcCourseDAO) context.getBean("courseDAO");
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses = new ArrayList<Course>(dao.searchByName(queryList));
		
		
		model.addAttribute("courses",courses);
		return "course/list";
	}
	
	
}
