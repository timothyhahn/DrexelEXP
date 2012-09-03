package com.drexelexp.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.drexelexp.baseDAO.JdbcBaseDAO;

/**
 * Implementation of ProfessorDAO in JDBC style.
 * @author
 *
 */
public class JdbcReviewDAO extends JdbcBaseDAO<Review> {
	protected String getTableName(){
		return "reviews";
	}
	protected String getIdColumnName(){
		return "REVIEW_ID";
	}
	protected int getId(Review instance){
		return instance.getId();
	}
	protected Review parseResultSetRow(ResultSet rs) throws SQLException{
		//TODO
		return null;
	}
	protected Map<String,Object> getColumnMap(Review instance){
		//TODO
		return null;
	}
}
