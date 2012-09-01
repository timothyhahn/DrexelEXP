

package com.drexelexp.course;

import java.util.ArrayList;
import java.util.List;

import com.drexelexp.professor.Professor;
import com.drexelexp.subject.Subject;

/**
 * Model for the Course object
 * @author
 *
 */
public class Course {
	private String courseCode;
	private Subject subject;
	private int number;
	private String name;
	private List<Professor> professors = new ArrayList<Professor>();
	private int CourseId;
	
	
	public Course() {
		
	}
	public Course(Subject subject, int number, String name, int id) {
		this.subject = subject;
		this.number = number;
		this.name = name;
		this.setCourseCode(subject.getShortName() + number);
		this.CourseId = id;
	}
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
		this.setCourseCode(subject.getShortName() + number);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		this.setCourseCode(subject.getShortName() + number);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public void setId(int id){
		this.CourseId = id;
	}
	
	public int getId(){
		return CourseId;
	}
	
	public List<Professor> getProfessors() {
		return professors;
	}
	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	public void addProfessor(Professor professor) {
		professors.add(professor);
	}
	public Professor getProfessorById(int id) {
		for(Professor p : professors) {
			if(p.getId() == id ) {
				return p;
			}
		}
		return null;
	}
}

