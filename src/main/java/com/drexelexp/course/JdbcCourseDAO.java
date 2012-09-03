package com.drexelexp.course;
/**
 * Implementation of CourseDAO in JDBC style.
 * @author
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.course.Course;

public class JdbcCourseDAO extends SearchableDAO<Course> {
	protected String getTableName(){
		return "courses";
	}
	protected String getIdColumnName(){
		return "COURSE_ID";
	}
	protected int getId(Course instance){
		return instance.getId();
	}
	protected Course parseResultSetRow(ResultSet rs) throws SQLException{
		//TODO
		return new Course(
				rs.getInt("COURSE_ID"),
				rs.getInt("NUMBER"),
				rs.getString("NAME"),
				rs.getString("DESCRIPTION"),
				rs.getInt("SUBJECT_ID")
			);
	}
	protected Dictionary<String,Object> getColumnMap(Course instance){
		//TODO
		return null;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("name","subject");
	}
	
	public List<Course> getBySubjectId(int id){
		return getWhere("SUBJECT_ID = "+id);
	}
}