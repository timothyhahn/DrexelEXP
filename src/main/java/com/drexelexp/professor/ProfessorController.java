package com.drexelexp.professor;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
