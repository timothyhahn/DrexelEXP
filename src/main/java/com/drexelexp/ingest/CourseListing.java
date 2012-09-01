package com.drexelexp.ingest;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CourseListing {
	private int number;
	private String name;
	private String desc;
	
	public CourseListing(Element htmlElement){
		String text;		
		NodeList nodes;
		
		nodes = ((Element)htmlElement.getElementsByTagName("p").item(0)).getElementsByTagName("span");
		
		text = ((Element)nodes.item(0)).getTextContent();
		number = 0;//Integer.parseInt(text.split("[0-9]*")[0]);
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
