package com.drexelexp.subject;
/**
 * Model for the Subject object
 * @author
 *
 */
public class Subject {
	public String shortName;
	public String longName;
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String toString() {
		return shortName;
	}
}
