package com.drexelexp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.review.JdbcReviewDAO;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {

		logger.info("Welcome home! the locale is "+ locale.toString());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName().equals("anonymousUser")) {
			model.addAttribute("username","");
		} else {
			model.addAttribute("username",authentication.getName());
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcReviewDAO reviewDAO =  (JdbcReviewDAO) context.getBean("reviewDAO");
		
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("reviews",reviewDAO.getRecent(10));
		mav.addObject("profQuery", new Query());
		mav.addObject("courseQuery", new Query());
		return mav;
		
	}
	

	
}
