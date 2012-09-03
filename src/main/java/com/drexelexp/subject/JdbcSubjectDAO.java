package com.drexelexp.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.drexelexp.baseDAO.SearchableDAO;

/**
 * Implementation of SubjectDAO in JDBC style.
 * @author
 *
 */
public class JdbcSubjectDAO extends SearchableDAO<Subject> {
	protected String getTableName(){
		return "subjects";
	}
	protected String getIdColumnName(){
		return "SUBJECT_ID";
	}
	protected int getId(Subject instance){
		return instance.getId();
	}
	protected Subject parseResultSetRow(ResultSet rs) throws SQLException{
		return new Subject(
				rs.getInt("SUBJECT_ID"),
				rs.getString("CODE"),
				rs.getString("NAME"));
	}
	protected Map<String,Object> getColumnMap(Subject instance){
		//TODO
		return null;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("name");
	}
}
