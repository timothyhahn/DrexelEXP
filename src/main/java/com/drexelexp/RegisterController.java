package com.drexelexp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.drexelexp.user.User;

@Controller
public class RegisterController {
	
	@RequestMapping(value="/register")
	public ModelAndView showUsers(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName().equals("anonymousUser")) {
			model.addAttribute("username","");
		} else {
			model.addAttribute("username",authentication.getName());
		}
		return new ModelAndView("register", "command", new User());
	}
}
