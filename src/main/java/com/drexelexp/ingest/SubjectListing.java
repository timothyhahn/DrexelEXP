package com.drexelexp.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;

import com.drexelexp.subject.JdbcSubjectDAO;
import com.drexelexp.subject.Subject;

public class SubjectListing {
	private static final Logger logger = LoggerFactory.getLogger(SubjectListing.class);
	private JdbcSubjectDAO _subjectDAO;
	private JdbcSubjectDAO getSubjectDAO(){
		if(_subjectDAO!=null)
			return _subjectDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");
		
		return _subjectDAO;
	}
	
	private String name;
	private String code;
	
	public SubjectListing(Element htmlElement){		
		String text = htmlElement.getTextContent();
		
		name=text.substring(0, text.indexOf('(')-1);
		code=text.substring(text.indexOf('(')+1,text.indexOf(')'));
		
		Subject subject = getSubjectDAO().getByCode(code);
		if(subject==null){
			 getSubjectDAO().insert(new Subject(0,code,name));
			 subject = getSubjectDAO().getByCode(code);
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getCode(){
		return code;
	}
}
