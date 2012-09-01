package com.drexelexp.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class SubjectListing {
	private static final Logger logger = LoggerFactory.getLogger(SubjectListing.class);
	
	private String name;
	private String code;
	private String url;
	
	public SubjectListing(Element htmlElement){
		url = htmlElement.getAttribute("href");
		
		String text =htmlElement.getTextContent();
				
		name=text.substring(0, text.indexOf('(')-1);
		code=text.substring(text.indexOf('(')+1,text.indexOf(')'));
	}
	
	public String getName(){
		return name;
	}
	
	public String getCode(){
		return code;
	}
}
