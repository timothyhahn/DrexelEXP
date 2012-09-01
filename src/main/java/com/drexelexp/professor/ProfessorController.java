package com.drexelexp.professor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.drexelexp.HomeController;
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
	public ModelAndView addProfessor() {
		return new ModelAndView("professor/add", "command", new Professor());
	}
	
	@RequestMapping(value="/professor/create", method = RequestMethod.POST)
	public String createProfessor(@ModelAttribute("professor") Professor professor, ModelMap model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		professorDAO.insert(professor);
		return "professor/list";
	}
	
	@RequestMapping(value="/professor/edit", method = RequestMethod.GET) 
	public String listEditProfessor(Model model) {
		ArrayList<Professor> professors = null;
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		professors = ((JdbcProfessorDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "professor/edit/list";
	}
	
	@RequestMapping(value="/professor/edit/{profID}", method = RequestMethod.GET) 
	public ModelAndView editProfessor(@PathVariable String profID, Model model) {

		System.out.println("ID: " + profID);
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		Professor professor = new Professor();
		professor.setId(Integer.parseInt(profID));
		professor = ((JdbcProfessorDAO) professorDAO).getById(professor.getId());
		model.addAttribute("professor", professor);
		return new ModelAndView("professor/edit", "command", professor);
	}
	
	@RequestMapping(value="/professor/edit/{profID}", method = RequestMethod.POST)
	public ModelAndView updateProfessor(@ModelAttribute("professor") Professor professor, @PathVariable String profID) {
		professor.setId(Integer.parseInt(profID));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		((JdbcProfessorDAO)professorDAO).edit(professor);
		return new ModelAndView("redirect:../");
	}
	
	@RequestMapping(value="/professor/delete", method = RequestMethod.GET) 
	public String listDeleteProfessor(Model model) {
		ArrayList<Professor> professors = null;
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		professors = ((JdbcProfessorDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "professor/delete/list";
	}
	
	@RequestMapping(value="/professor/delete/{profID}", method = RequestMethod.GET) 
	public ModelAndView deleteProfessor(@PathVariable String profID, Model model) {
		System.out.println("ID: " + profID);
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		Professor professor = new Professor();
		professor.setId(Integer.parseInt(profID));
		professor = ((JdbcProfessorDAO) professorDAO).getById(professor.getId());
		model.addAttribute("professor", professor);
		return new ModelAndView("professor/delete/confirm", "command", professor);
	}
	
	@RequestMapping(value="/professor/delete/{profID}", method = RequestMethod.POST)
	public ModelAndView removeProfessor(@ModelAttribute("professor") Professor professor, @PathVariable String profID) {
		professor.setId(Integer.parseInt(profID));
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		((JdbcProfessorDAO)professorDAO).delete(professor);
		return new ModelAndView("redirect:../");
	}
	
	@RequestMapping(value="/professor", method = RequestMethod.GET)
	public String list(Model model) {
		ArrayList<Professor> professors = null;
		
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		professors = ((JdbcProfessorDAO) professorDAO).getAll();
		
		model.addAttribute("professors",professors);
		
		return "professor/list";
	}
	
	
	
	@RequestMapping(value="/professor/show/{profID}", method = RequestMethod.GET)
	public String show(@PathVariable String profID, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		model.addAttribute("professor",dao.getById(Integer.parseInt(profID)));
		
		return "professor/show";
	}
}
