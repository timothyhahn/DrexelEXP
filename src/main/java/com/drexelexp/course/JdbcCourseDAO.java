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
import com.drexelexp.professor.Professor;
import com.drexelexp.subject.Subject;

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
		return Arrays.asList("name");
	}
	
	public List<Course> getBySubject(Subject subject){
		return getWhere("SUBJECT_ID = "+subject.getId());
	}
	
	public List<Course> getByProfessor(Professor professor){
		return getQuery(
				"SELECT c.* FROM courses as c "+
						"JOIN (Professor_Course as pc, professors as p) "+
						"ON (p.prof_id=pc.prof_id and pc.course_id=c.course_id) "+
						"WHERE pc.prof_id="+professor.getId());
	}
}