package com.drexelexp.course;

import java.util.List;

import com.drexelexp.professor.Professor;

/**
 * DAO interface for the Course object
 * @author
 *
 */

public interface CourseDAO {
	public void insert(Course instance);
	public Course search(String Coursename);
	public List<Course> listCourses(Professor instance);
}
