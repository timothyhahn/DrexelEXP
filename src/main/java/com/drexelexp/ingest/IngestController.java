package com.drexelexp.ingest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.subject.JdbcSubjectDAO;
import com.drexelexp.subject.Subject;

@Controller
public class IngestController {
	private static final Logger logger = LoggerFactory.getLogger(IngestController.class);
	
	private JdbcSubjectDAO _subjectDAO;
	private JdbcSubjectDAO getSubjectDAO(){
		if(_subjectDAO!=null)
			return _subjectDAO;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		_subjectDAO = (JdbcSubjectDAO) context.getBean("subjectDAO");
		
		return _subjectDAO;
	}
	
	@RequestMapping(value = "/ingest", method = RequestMethod.GET)
	public String list(Model model) {
		String rootUrl = "http://catalog.drexel.edu/coursedescriptions/quarter/undergrad/index.html";
		ArrayList<CollegeListing> colleges = new ArrayList<CollegeListing>();

		try {
			Document document = getUrlDocument(rootUrl);

			Element content = document.getElementById("content");

			NodeList divs = content.getElementsByTagName("div");
			int divCount = divs.getLength();
			for (int i = 0; i < divCount; i++) {
				Element div = (Element) divs.item(i);

				if (div.getAttribute("class").equals("colfloat")) {
					colleges.add(new CollegeListing(div));
				}
			}
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
		} finally {
			model.addAttribute("colleges", colleges);
		}

		return "ingest/list";
	}

	@RequestMapping(value = "/ingest/courses/{collegeCode}/{subjectCode}", method = RequestMethod.GET)
	public String courses(@PathVariable String collegeCode,@PathVariable String subjectCode,Model model) {
		String url ="http://catalog.drexel.edu/coursedescriptions/quarter/undergrad/"+subjectCode.toLowerCase();
		if(subjectCode.equals("UNIV"))
			url+=collegeCode.toLowerCase();
		url+="/";
		
		Subject subject = getSubjectDAO().getByCode(subjectCode); 
		
		ArrayList<CourseListing> courses = new ArrayList<CourseListing>();	
		
		try {
			Document document = getUrlDocument(url);

			Element content = document.getElementById("courseinventorycontainer");

			NodeList divs = content.getElementsByTagName("div");
			int divCount = divs.getLength();
			for (int i = 0; i < divCount; i++) {
				Element div = (Element) divs.item(i);

				if (div.getAttribute("class").equals("courseblock")) {
					courses.add(new CourseListing(div,subject));
				}
			}
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
		} finally {
			model.addAttribute("courses", courses);
		}
		
		return "ingest/courses";
	}
	
	@RequestMapping(value = "/ingest/professors/{collegeCode}/{subjectCode}", method = RequestMethod.GET)
	public String professors(@PathVariable String collegeCode,@PathVariable String subjectCode,Model model) {
		String url ="http://catalog.drexel.edu/coursedescriptions/quarter/undergrad/"+subjectCode.toLowerCase();
		if(subjectCode.equals("UNIV"))
			url+=collegeCode.toLowerCase();
		url+="/";
		
		Subject subject = getSubjectDAO().getByCode(subjectCode); 
		
		ArrayList<CourseListing> courses = new ArrayList<CourseListing>();	
		
		try {
			Document document = getUrlDocument(url);

			Element content = document.getElementById("courseinventorycontainer");

			NodeList divs = content.getElementsByTagName("div");
			int divCount = divs.getLength();
			for (int i = 0; i < divCount; i++) {
				Element div = (Element) divs.item(i);

				if (div.getAttribute("class").equals("courseblock")) {
					courses.add(new CourseListing(div,subject));
				}
			}
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
		} finally {
			model.addAttribute("courses", courses);
		}
		
		return "ingest/professors";
	}
	
	private String getFilteredContents(String url) throws IOException {
		URL u = new URL(url);
		BufferedReader input = new BufferedReader(new InputStreamReader(u.openStream()));
		String contents = "";
		String line;
		
		int divs=0;
		while ((line = input.readLine()) != null) {
			if(line.contains("<div")){
				divs++;
			}
			if(line.contains("</div")){
				if(divs==0)
					line=line.replace("</div>", "");
				else
					divs--;
			}
			
			line= line
					.replaceAll(" < ", " &lt; ")
					.replaceAll(" & ", " &amp; ")
					.replaceAll("<img[^>]*>", "")
					.replaceAll("<li[^>]*>", "")
					.replaceAll("</li>", "")
					.replaceAll("<ul[^>]*>", "")
					.replaceAll("</ul>", "")
					.replaceAll("<br/>","")
					;
			
			contents+=line;
		}
		
		return contents;
	}

	private Document getUrlDocument(String url) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			String contents = getFilteredContents(url);
			Reader reader = new StringReader(contents);
			InputSource source = new InputSource(reader);
			Document document = builder.parse(source);
			
			return document;
		} catch (ParserConfigurationException ex) {
			logger.error(ex.getMessage());
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SAXException ex) {
			logger.error(ex.getMessage());
		}

		throw new IllegalArgumentException("Getting document at " + url	+ " failed.");
	}
}