package com.drexelexp.review;

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
 * Controller for the Review object
 * @author
 *
 */
@Controller
public class ReviewController {
	// TODO Actually implement this file (currently copied from professor).
	
	@RequestMapping(value="/review/add", method = RequestMethod.GET)
	public ModelAndView addProfessor() {
		return new ModelAndView("review/add", "command", new Review());
	}
	
	@RequestMapping(value="/review/create", method = RequestMethod.POST)
	public String createProfessor(@ModelAttribute("review") Review review, ModelMap model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		professorDAO.insert(review);
		return "review/list";
	}
	
	@RequestMapping(value="/review/edit", method = RequestMethod.GET) 
	public String listEditProfessor(Model model) {
		ArrayList<Review> professors = null;
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		
		professors = ((JdbcReviewDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "review/edit/list";
	}
	
	@RequestMapping(value="/review/edit/{profID}", method = RequestMethod.GET) 
	public ModelAndView editProfessor(@PathVariable String profID, Model model) {

		System.out.println("ID: " + profID);
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		Review review = new Review();
		review.setId(Integer.parseInt(profID));
		review = ((JdbcReviewDAO) professorDAO).getById(review.getId());
		model.addAttribute("review", review);
		return new ModelAndView("review/edit", "command", review);
	}
	
	@RequestMapping(value="/review/edit/{profID}", method = RequestMethod.POST)
	public ModelAndView updateProfessor(@ModelAttribute("review") Review review, @PathVariable String profID) {
		review.setId(Integer.parseInt(profID));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		((JdbcReviewDAO)professorDAO).edit(review);
		return new ModelAndView("redirect:../");
	}
	
	@RequestMapping(value="/review/delete", method = RequestMethod.GET) 
	public String listDeleteProfessor(Model model) {
		ArrayList<Review> professors = null;
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		
		professors = ((JdbcReviewDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "delete/list";
	}
	
	@RequestMapping(value="/review/delete/{profID}", method = RequestMethod.GET) 
	public ModelAndView deleteProfessor(@PathVariable String profID, Model model) {
		System.out.println("ID: " + profID);
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		Review review = new Review();
		review.setId(Integer.parseInt(profID));
		review = ((JdbcReviewDAO) professorDAO).getById(review.getId());
		model.addAttribute("review", review);
		return new ModelAndView("review/delete/confirm", "command", review);
	}
	
	@RequestMapping(value="/review/delete/{profID}", method = RequestMethod.POST)
	public ModelAndView removeProfessor(@ModelAttribute("review") Review review, @PathVariable String profID) {
		review.setId(Integer.parseInt(profID));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		((JdbcReviewDAO)professorDAO).delete(review);
		return new ModelAndView("redirect:../");
	}
	
	@RequestMapping(value="/review", method = RequestMethod.GET)
	public String list(Model model) {
		ArrayList<Review> professors = null;
		
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> professorDAO = (JdbcReviewDAO) context.getBean("professorDAO");
		
		professors = ((JdbcReviewDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "review/list";
	}
	
	
	
	@RequestMapping(value="/review/show/{profID}", method = RequestMethod.GET)
	public String show(@PathVariable String profID, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Review> dao = (JdbcReviewDAO) context.getBean("professorDAO");
		
		model.addAttribute("review",dao.getById(Integer.parseInt(profID)));
		
		return "review/show";
	}
	
	@RequestMapping(value="/review/search", method = RequestMethod.GET)
	public ModelAndView searchForProfessor() {
		return new ModelAndView("review/search", "command", new String());
		
	}
	@RequestMapping(value="/review/search", method = RequestMethod.POST)
	public String showSearchResults(@ModelAttribute("name") String query) {
		
		ArrayList<String> queryList = new ArrayList<String>(Arrays.asList(query.split(" ")));
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcReviewDAO dao = (JdbcReviewDAO) context.getBean("professorDAO");
		dao.searchByName(queryList);
		
		return "review/list";
	}
}
