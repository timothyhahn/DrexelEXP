package com.drexelexp.course;
/**
 * Implementation of CourseDAO in JDBC style.
 * @author
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
	@Override
	protected String getOrderByColumns(){
		return "COURSE_ID,NUMBER";
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
	protected Map<String,Object> getColumnMap(Course instance){
		Map<String,Object> map = new Hashtable<String,Object>();

		map.put("COURSE_ID",instance.getId());
		map.put("NUMBER",instance.getNumber());
		map.put("NAME",instance.getName());
		map.put("DESCRIPTION",instance.getDescription());
		map.put("SUBJECT_ID",instance.getSubject().getId());
		
		return map;
	}
	@Override
	protected String getSearchTable(){
		return getTableName()+" "+
				"JOIN subjects as s "+
				"ON courses.SUBJECT_ID=s.SUBJECT_ID";
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("courses.NAME","s.NAME");
	}	
	
	public Course getByCode(Subject subject, int number){
		Map<String,Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("SUBJECT_ID",subject.getId());
		conditions.put("NUMBER",number);
		
		List<Course> result = getWhere(conditions);
		
		if(result.size()==1)
			return result.get(0);
		
		return null;
	}
	
	public List<Course> getBySubject(Subject subject){
		Map<String, Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("SUBJECT_ID",subject.getId());
		
		return getWhere(conditions);
	}
	
	public List<Course> getByProfessor(Professor professor){
		return getQuery(
				"SELECT c.* FROM courses as c "+
						"JOIN (professor_course as pc, professors as p) "+
						"ON (p.prof_id=pc.prof_id and pc.course_id=c.course_id) "+
						"WHERE pc.prof_id="+professor.getId());
	}
}