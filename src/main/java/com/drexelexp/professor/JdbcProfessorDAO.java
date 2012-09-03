
package com.drexelexp.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.drexelexp.baseDAO.SearchableDAO;
import com.drexelexp.course.Course;

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
	protected Map<String,Object> getColumnMap(Professor instance){
		Map<String,Object> map = new Hashtable<String,Object>();
		
		map.put("PROF_ID", instance.getId());
		map.put("NAME", instance.getName());
		
		return map;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("NAME");
	}
	
	public Professor getByName(String name){
		Map<String,Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("LOWER(NAME)",name.toLowerCase());
		
		List<Professor> result = getWhere(conditions);
		
		if(result.size()==1)
			return result.get(0);
		
		return null;
	}
	
	public void addProfessorCourse(Professor professor,Course course){
		//TODO
		
	}
	
	public List<Professor> getByCourse(Course course){
		return getQuery(
				"SELECT p.* FROM professors as p "+
						"JOIN (Professor_Course as pc, courses as c) "+
						"ON (p.prof_id=pc.prof_id and pc.course_id=c.course_id) "+
						"WHERE pc.course_id="+course.getId());
	}
}
