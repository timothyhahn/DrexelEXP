package com.drexelexp.professor;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.user.JdbcUserDAO;
import com.drexelexp.user.User;

/**
 * Controller for the Professor object
 * @author
 *
 */
@Controller
public class ProfessorController {
	
	@RequestMapping(value="/professor/add", method = RequestMethod.GET)
	public ModelAndView showUsers() {
		return new ModelAndView("professor/add", "command", new User());
	}
	@RequestMapping(value="/professor/create", method = RequestMethod.POST)
	public String createProfessor(@ModelAttribute("professor") Professor professor, ModelMap model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		professorDAO.insert(professor);
		return "professor/list";
	}
	
	
	@RequestMapping(value="/professor/list", method = RequestMethod.GET)
	public String list(Model model) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		//BaseDAO<Professor> dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		ArrayList<Professor> professors = new ArrayList<Professor>();
		
		//professors.add(new Professor(1,"Sunny Wong"));
		//professors.add(new Professor(2,"Jeffery Popyack"));
		//professors.add(new Professor(3,"Kurt Schmidt"));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		professors = ((JdbcProfessorDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "professor/list";
	}
	
	
	
	@RequestMapping(value="/professor/show", method = RequestMethod.GET)
	public String show(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		model.addAttribute("professor",dao.getById(1));
		
		return "professor/show";
	}
}
