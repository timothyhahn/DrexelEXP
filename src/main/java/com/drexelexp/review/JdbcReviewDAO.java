package com.drexelexp.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import com.drexelexp.baseDAO.JdbcBaseDAO;

/**
 * Implementation of ProfessorDAO in JDBC style.
 * 
 * @author
 * 
 */
public class JdbcReviewDAO extends JdbcBaseDAO<Review> {
	protected String getTableName() {
		return "reviews";
	}

	protected String getIdColumnName() {
		return "REVIEW_ID";
	}

	protected int getId(Review instance) {
		return instance.getId();
	}

	protected Review parseResultSetRow(ResultSet rs) throws SQLException {
		return new Review(
				rs.getInt("REVIEW_ID"),
				rs.getString("TEXT"),
				rs.getFloat("RATING"),
				rs.getTimestamp("TIMESTAMP"),
				rs.getInt("USER_ID"),
				rs.getInt("PROFESSOR_ID"),
				rs.getInt("COURSE_ID"));
	}

	protected Map<String, Object> getColumnMap(Review instance) {
		Map<String,Object> map = new Hashtable<String,Object>();
		
		map.put("REVIEW_ID",instance.getId());
		map.put("TEXT",instance.getText());
		map.put("RATING",instance.getRating());
		map.put("TIMESTAMP",instance.getTimestamp());
		map.put("USER_ID",instance.getUser().getId());
		map.put("PROFESSOR_ID",instance.getProfessor().getId());
		map.put("COURSE_ID",instance.getCourse().getId());
		
		return map;
	}
}
