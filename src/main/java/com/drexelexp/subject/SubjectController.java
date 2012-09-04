package com.drexelexp.subject;

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
import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.subject.Subject;
import com.drexelexp.subject.JdbcSubjectDAO;

/**
 * Controller for the Subject object
 * 
 * @author
 * 
 */
@Controller
public class SubjectController {
	private void addUsername(Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication.getName().equals("anonymousUser")) {
			model.addAttribute("username", "");
		} else {
			model.addAttribute("username", authentication.getName());
		}
	}

	private SearchableDAO<Subject> _subjectDAO;

	private SearchableDAO<Subject> getSubjectDAO() {
		if (_subjectDAO != null)
			return _subjectDAO;

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");

		return _subjectDAO;
	}

	@RequestMapping(value="/subject", method = RequestMethod.GET)
	public ModelAndView list(Model model) {		
		return new ModelAndView("redirect:/subject/1");
	}
	
	@RequestMapping(value="/subject/{pageNum}", method = RequestMethod.GET)
	public ModelAndView listPage(@PathVariable String pageNum, Model model) {		
		addUsername(model);
		
		List<Subject> subjects = getSubjectDAO().getPage(Integer.parseInt(pageNum), 20);
		
		model.addAttribute("subjects",subjects);
		model.addAttribute("pageNum",Integer.parseInt(pageNum));
		
		return new ModelAndView("subject/list", "command", new Subject());
	}

	@RequestMapping(value = "/subject/show/{subjectID}", method = RequestMethod.GET)
	public String show(@PathVariable String subjectID, Model model) {
		addUsername(model);

		Subject subject = getSubjectDAO().getById(Integer.parseInt(subjectID));

		model.addAttribute("subject", subject);

		return "subject/show";
	}

	@RequestMapping(value = "/subject/search", method = RequestMethod.GET)
	public ModelAndView search(Model model) {
		addUsername(model);
		
		ModelAndView mav = new ModelAndView("subject/search");
		mav.addObject("subjectQuery", new Query());
		
		return mav;
	}

	@RequestMapping(value = "/subject/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("query") String query, Model model) {
		System.out.println(query);

		List<Subject> subjects = getSubjectDAO().search(query);

		model.addAttribute("subjects", subjects);
		return "subject/list";
	}
}
