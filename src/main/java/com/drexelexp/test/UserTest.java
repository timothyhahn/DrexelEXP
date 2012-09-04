package com.drexelexp.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.BaseDAO;

import com.drexelexp.user.JdbcUserDAO;
import com.drexelexp.user.User;

public class UserTest {
	private User user;
	private ApplicationContext context;
	
	@Before
	public void initializeSubjectAndCourse() {
		user = new User(1, "tester@test.com");
		
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		BaseDAO<User>userDAO = (JdbcUserDAO) context.getBean("userDAO");
		((JdbcUserDAO)userDAO).insert(user);
	}

	@Test
	public void findIdByEmail() {
		BaseDAO<User>userDAO = (JdbcUserDAO) context.getBean("userDAO");
		int result = ((JdbcUserDAO)userDAO).findIdByEmail("tester@test.com");
		assertEquals(user.getId(), result);
	}
	
	@After
	public void cleanupSubjectAndCourse() {
		BaseDAO<User>userDAO = (JdbcUserDAO) context.getBean("userDAO");
		((JdbcUserDAO)userDAO).delete(user);
	}
}
