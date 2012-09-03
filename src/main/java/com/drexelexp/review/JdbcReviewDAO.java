package com.drexelexp.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.JdbcBaseDAO;
import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;

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
	protected Dictionary<String,Object> getColumnMap(Review instance){
		//TODO
		return null;
	}
}
