package com.drexelexp.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.JdbcDAO;
import com.drexelexp.course.Course;
import com.drexelexp.course.JdbcCourseDAO;
import com.drexelexp.professor.JdbcProfessorDAO;
import com.drexelexp.professor.Professor;

/**
 * Implementation of ProfessorDAO in JDBC style.
 * @author
 *
 */
public class JdbcReviewDAO extends JdbcDAO implements BaseDAO<Review>{
	
	@Override
	public void insert(Review instance) {
		String sql = "INSERT INTO reviews " + "(DATA, RATING, PROF_ID, COURSE_ID) VALUES (?, ?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, instance.getData());
			ps.setInt(2, instance.getRating());
			ps.setInt(3, instance.getProfessor().getId());
			ps.setInt(4, instance.getCourse().getId());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public ArrayList<Review> getAll() {
		ArrayList<Review> reviews = new ArrayList<Review>();
		String sql = "SELECT * FROM reviews";
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ApplicationContext context = 
			    		new ClassPathXmlApplicationContext("Spring-Module.xml");
				BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
				Professor professor = professorDAO.getById(rs.getInt("PROF_ID"));
				
				// TODO add course as well after the DAO is finished
				// <Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
				
				Review review = new Review(
						rs.getInt("REVIEW_ID"),
						rs.getString("DATA"),
						rs.getInt("RATING"),
						professor,
						null
					);
				reviews.add(review);
			}
			rs.close();
			ps.close();
			return reviews;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public Review getById(int id) {
		String sql = "SELECT * FROM reviews WHERE REVIEW_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Review review = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ApplicationContext context = 
			    		new ClassPathXmlApplicationContext("Spring-Module.xml");
				BaseDAO<Professor> professorDAO = (JdbcProfessorDAO) context.getBean("professorDAO");
				Professor professor = professorDAO.getById(rs.getInt("PROF_ID"));
				
				// TODO add course as well after the DAO is finished
				// <Course> courseDAO = (JdbcCourseDAO) context.getBean("courseDAO");
				
				review = new Review(
						rs.getInt("REVIEW_ID"),
						rs.getString("DATA"),
						rs.getInt("RATING"),
						professor,
						null
					);
			}
			rs.close();
			ps.close();
			return review;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public void edit(Review review) {
		String sql = "UPDATE REVIEWs SET DATA = ?, PROF_ID = ?, COURSE_ID = ? WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, review.getData());
			ps.setInt(2, review.getProfessor().getId());
			ps.setInt(3, review.getCourse().getId());
			ps.setInt(4, review.getId());
			System.out.println(ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		
		}
	}

	public void delete(Review review) {
		String sql = "DELETE FROM reviews WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getId());
			//professor = null;
			System.out.println(ps.toString());
			ps.executeUpdate();
			ps.close();
		//	return professor;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		
		}
	}

}
