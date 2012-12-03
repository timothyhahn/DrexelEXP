package com.drexelexp.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.baseDAO.BaseDAO;

/**
 * Controller for the User object
 * @author Timothy Hahn
 *
 */

@Controller
public class UserController {
	
	@RequestMapping(value="/create_user", method = RequestMethod.POST)
	public ModelAndView create_user(@ModelAttribute("user")
    User user,ModelMap model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<User> userDAO = (JdbcUserDAO) context.getBean("userDAO");
		
		String validMessage = user.isValid();
		if(validMessage.equals("")){

			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			user.setModerator(true);
			userDAO.insert(user);
			return new ModelAndView("redirect:/login");
		} else {
			model.addAttribute("errorMessage", validMessage);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getName().equals("anonymousUser")) {
				model.addAttribute("username","");
			} else {
				model.addAttribute("username",authentication.getName());
			}
			return new ModelAndView("register", "command", user);
		}
	}
}

