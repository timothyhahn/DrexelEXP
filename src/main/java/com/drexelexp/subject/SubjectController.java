package com.drexelexp.subject;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drexelexp.subject.Subject;

/**
 * Controller for the Subject object
 * @author
 *
 */
@Controller
public class SubjectController {
	@RequestMapping(value="/subject/list", method = RequestMethod.GET)
	public String showUsers(Model model) {

		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		
		model.addAttribute("subjects",subjects);
		
		return "subject/list";
	}

}
