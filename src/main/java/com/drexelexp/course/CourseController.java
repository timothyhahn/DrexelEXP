package com.drexelexp.course;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.Query;
import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.professor.Professor;
import com.drexelexp.review.JdbcReviewDAO;
import com.drexelexp.review.Review;
import com.drexelexp.user.User;

/**
 * Controller for the Course object
 * @author
 *
 */

@Controller
public class CourseController {
	private void addUsername(Model model){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getName().equals("anonymousUser")) {
				model.addAttribute("username","");
			} else {
				model.addAttribute("username",authentication.getName());
			}
	}
	
	private SearchableDAO<Course> _courseDAO;
	private SearchableDAO<Course> getCourseDAO(){
		if(_courseDAO!=null)
			return _courseDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		return _courseDAO;
	}
	
	@RequestMapping(value="/course", method = RequestMethod.GET)
	public ModelAndView list(Model model) {		
		return new ModelAndView("redirect:/course/1");
	}
	
	@RequestMapping(value="/course/{pageNum}", method = RequestMethod.GET)
	public ModelAndView listPage(@PathVariable String pageNum, Model model) {		
		addUsername(model);
		
		List<Course> courses = getCourseDAO().getPage(Integer.parseInt(pageNum), 20);
		
		model.addAttribute("courses",courses);
		model.addAttribute("pageNum",Integer.parseInt(pageNum));
		
		return new ModelAndView("course/list", "command", new Course());
	}
	
	@RequestMapping(value="/course/show/{courseID}", method = RequestMethod.GET)
	public String show(@PathVariable String courseID, Model model) {
		addUsername(model);
		
		Course course = getCourseDAO().getById(Integer.parseInt(courseID));
		
		model.addAttribute("course",course);
		
		return "course/show";
	}
	
	@RequestMapping(value="course/show/{profID}", method = RequestMethod.POST)
	public ModelAndView review(@PathVariable String profID,@ModelAttribute Professor professor, @ModelAttribute Review review, Model model) {
		
		Course course = new Course();
		course.setId(1);
		review.setProfessor(professor);
		review.setCourse(course);
		User user = new User();
		user.setId(1);
		review.setUser(user);
		Professor p = new Professor();
		p.setId(1);
		review.setProfessor(p);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> dao =  (JdbcReviewDAO) context.getBean("reviewDAO");
		
		dao.insert(review);
		
		return new ModelAndView("redirect:.");
	}
	
	@RequestMapping(value="/course/search", method = RequestMethod.GET)
	public ModelAndView search(Model model) {
		addUsername(model);
		
		ModelAndView mav = new ModelAndView("course/search");
		mav.addObject("courseQuery", new Query());
		
		return mav;
	}
	
	@RequestMapping(value="/course/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("query") String query, Model model) {
		System.out.println(query);
		
		List<Course> courses = getCourseDAO().search(query);		
		
		model.addAttribute("courses",courses);
		return "course/list";
	}
}
