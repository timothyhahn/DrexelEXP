package com.drexelexp.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;
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
	@Override
	protected String getOrderByColumns(){
		return "NAME";
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
		Map<String,Object> map = new Hashtable<String,Object>();
		
		map.put("SUBJECT_ID",instance.getId());
		map.put("CODE",instance.getCode());
		map.put("NAME",instance.getName());
		
		return map;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("name");
	}
	
	public Subject getByCode(String code){
		Map<String,Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("UPPER(CODE)",code.toUpperCase());
		
		List<Subject> result = getWhere(conditions);
		
		if(result.size()==1)
			return result.get(0);
		
		return null;
	}
}
