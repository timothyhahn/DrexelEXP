package com.drexelexp.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CourseListing {
	private static final Logger logger = LoggerFactory.getLogger(CourseListing.class);
	
	private int number;
	private String name;
	private String desc;
	
	public CourseListing(Element htmlElement){
		String text;
		NodeList nodes;
		
		nodes = ((Element)htmlElement.getElementsByTagName("p").item(0)).getElementsByTagName("span");
		
		text = ((Element)nodes.item(0)).getTextContent();
		number = Integer.parseInt(text.split("\\u00A0")[1].trim());
		text = ((Element)nodes.item(1)).getTextContent();
		name = text;
		
		Element pblock = ((Element)htmlElement.getElementsByTagName("p").item(1));
		desc = pblock.getTextContent();
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
