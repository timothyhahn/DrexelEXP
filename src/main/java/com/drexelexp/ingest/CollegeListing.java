package com.drexelexp.ingest;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CollegeListing {
	private static final Logger logger = LoggerFactory.getLogger(CollegeListing.class);
	
	private String name;
	private String code;
	private ArrayList<SubjectListing> subjects;
	
	public CollegeListing(Element htmlElement){		
		Element title = (Element)htmlElement.getElementsByTagName("h2").item(0);
		
		String text = title.getTextContent();
		
		name=text.substring(0, text.indexOf('(')-1);
		code=text.substring(text.indexOf('(')+1,text.indexOf(')'));
		
		subjects = new ArrayList<SubjectListing>();
		NodeList subjectLinks = htmlElement.getElementsByTagName("a");
		for(int i=0;i<subjectLinks.getLength();i++){			
			Element link = (Element)(subjectLinks.item(i));
			subjects.add(new SubjectListing(link));
		}
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<SubjectListing> getSubjects(){
		return subjects;
	}
}
