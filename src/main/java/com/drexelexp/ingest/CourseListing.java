package com.drexelexp.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.subject.Subject;

public class CourseListing {
	private static final Logger logger = LoggerFactory.getLogger(CourseListing.class);
	
	private JdbcCourseDAO _courseDAO;
	private JdbcCourseDAO getCourseDAO(){
		if(_courseDAO!=null)
			return _courseDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
		
		return _courseDAO;
	}
	
	
	private String name;
	private String desc;
	private int number;
	
	public CourseListing(Element htmlElement, Subject subject){
		String text;
		NodeList nodes;
		
		nodes = ((Element)htmlElement.getElementsByTagName("p").item(0)).getElementsByTagName("span");
		
		text = ((Element)nodes.item(0)).getTextContent();
		number = Integer.parseInt(text.split("\\u00A0")[1].split("[\\s]+")[0].trim());
		text = ((Element)nodes.item(1)).getTextContent();
		name = text;
		
		Element pblock = ((Element)htmlElement.getElementsByTagName("p").item(1));
		desc = pblock.getTextContent();
		
		Course course = getCourseDAO().getByCode(subject,number);
		if(course==null){
			logger.info("Ingest Course: "+name);
			getCourseDAO().insert(new Course(0,number,name,desc,subject.getId()));
			course = getCourseDAO().getByCode(subject,number);
		}
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDesc(){
		return desc;
	}
}
