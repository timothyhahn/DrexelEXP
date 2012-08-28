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
	@RequestMapping(value="/professor/list", method = RequestMethod.GET)
	public String list(Model model) {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		professors.add(new Professor(1,"Sunny Wong"));
		professors.add(new Professor(2,"Jeffery Popyack"));
		professors.add(new Professor(3,"Kurt Schmidt"));
		
		model.addAttribute("professors",professors);
		
		return "professor/list";
	}
	
	@RequestMapping(value="/professor/testinsert", method = RequestMethod.GET)
	public String testinsert(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<Professor> dao = (JdbcProfessorDAO) context.getBean("professorDAO");
		dao.insert(new Professor(0,"Sunny"));
		return "professor/list";
	}
}
