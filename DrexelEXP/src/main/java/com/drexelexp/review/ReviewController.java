package com.drexelexp.review;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.drexelexp.user.JdbcUserDAO;
import com.drexelexp.user.User;

/**
 * Controller for the Review object
 * @author
 *
 */
@Controller
public class ReviewController {
	@RequestMapping(value="/review/delete/{reviewId}", method = RequestMethod.GET)
	public View delete(@PathVariable String reviewId,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("userDAO");
		User user = userDAO.findByEmail(authentication.getName());
		
		if(user==null || !user.getModerator())
			return new RedirectView(request.getHeader("referer"));
		
		JdbcReviewDAO reviewDAO = (JdbcReviewDAO) context.getBean("reviewDAO");
		
		Review review = reviewDAO.getById(Integer.parseInt(reviewId));
		
		reviewDAO.delete(review);
		
		return new RedirectView(request.getHeader("referer"));
	}	
}
