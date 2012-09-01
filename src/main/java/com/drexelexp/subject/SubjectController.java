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
		Subject s1 = new Subject();
		s1.setShortName("CS");
		Subject s2 = new Subject();
		s2.setShortName("SE");
		Subject s3 = new Subject();
		s3.setShortName("INFO");
		Subject s4 = new Subject();
		s4.setShortName("ENGL");
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		subjects.add(s1);
		subjects.add(s2);
		subjects.add(s3);
		subjects.add(s4);
		
		model.addAttribute("subjects",subjects);
		
		return "subject/list";
	}

}
