package com.drexelexp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.user.User;

@Controller
public class RegisterController {
	
	@RequestMapping(value="/register")
	public ModelAndView showUsers() {
		return new ModelAndView("register", "command", new User());
	}
}
