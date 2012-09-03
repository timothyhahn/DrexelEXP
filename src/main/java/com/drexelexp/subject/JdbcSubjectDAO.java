package com.drexelexp.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import javax.sql.DataSource;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.JdbcDAO;
import com.drexelexp.professor.Professor;

/**
 * Implementation of SubjectDAO in JDBC style.
 * @author
 *
 */
public class JdbcSubjectDAO extends JdbcDAO<Subject> implements BaseDAO<Subject> {
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
	protected Dictionary<String,Object> getColumnMap(Subject instance){
		//TODO
		return null;
	}
	protected List<String> getSearchableColumns(){
		return Arrays.asList("name");
	}
}
