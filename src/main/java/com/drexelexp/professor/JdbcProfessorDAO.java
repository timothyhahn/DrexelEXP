
package com.drexelexp.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.course.Course;
import com.drexelexp.subject.Subject;

/**
 * Implementation of ProfessorDAO in JDBC style.
 * @author
 *
 */
public class JdbcProfessorDAO extends SearchableDAO<Professor>{
	protected String getTableName(){
		return "professors";
	}
	protected String getIdColumnName(){
		return "PROF_ID";
	}
	protected int getId(Professor instance){
		return instance.getId();
	}
	protected Professor parseResultSetRow(ResultSet rs) throws SQLException{
		return new Professor(
				rs.getInt("PROF_ID"),
				rs.getString("NAME"));
	}
	protected Dictionary<String,Object> getColumnMap(Professor instance){
		//TODO
		return null;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("NAME");
	}
	
	public List<Professor> getByCourse(Course course){
		return getQuery(
				"SELECT p.* FROM professors as p "+
						"JOIN (Professor_Course as pc, courses as c) "+
						"ON (p.prof_id=pc.prof_id and pc.course_id=c.course_id) "+
						"WHERE pc.course_id="+course.getId());
	}
}
