package com.drexelexp.professor;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drexelexp.baseDAO.BaseDAO;

/**
 * Controller for the Professor object
 * @author
 *
 */
@Controller
public class ProfessorController {
	
	@RequestMapping(value="/professor/add", method = RequestMethod.GET)
	public String add(Model model) {		
		return "professor/add";
	}
	
	@RequestMapping(value="/professor/list", method = RequestMethod.GET)
	public String list(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		
		ArrayList<Professor> professors = new ArrayList<Professor>();
		professors.add(new Professor(1,"Sunny Wong"));
		professors.add(new Professor(2,"Jeffery Popyack"));
		professors.add(new Professor(3,"Kurt Schmidt"));
		
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
