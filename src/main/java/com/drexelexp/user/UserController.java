package com.drexelexp.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drexelexp.baseDAO.BaseDAO;

/**
 * Controller for the User object
 * @author Timothy Hahn
 *
 */

@Controller
public class UserController {
	
	@RequestMapping(value="/create_user", method = RequestMethod.POST)
	public String create_user(@ModelAttribute("user")
    User user,ModelMap model) {
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<User> userDAO = (JdbcUserDAO) context.getBean("userDAO");
		userDAO.insert(user);
		return "login";
	}
}
