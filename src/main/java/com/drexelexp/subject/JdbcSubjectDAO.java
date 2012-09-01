package com.drexelexp.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * Implementation of SubjectDAO in JDBC style.
 * @author
 *
 */
public class JdbcSubjectDAO implements SubjectDAO {
	private DataSource dataSource;
	
	@Override
	public void insert(Subject instance) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO SUBJECTS " + "(NAME) VALUES (?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" +instance.getShortName() + "%");
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

	@Override
	public Subject search(String subjectname) {
		// TODO Auto-generated method stub
		String courseSql = "Select * From subjects ";
		courseSql += "Where name LIKE ?";
		
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(courseSql);
			ps.setString(2, "%" + subjectname + "%");
			Subject subject = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				subject = new Subject();
				subject.setShortName(subjectname);
			}
			rs.close();
			ps.close();
			return subject;
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
	public List<Subject> listSubjects() {
		// TODO Auto-generated method stub
		List<Subject> subjects = new ArrayList<Subject>();
		String sql = "SELECT * FROM SUBJECTS";
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Subject subject = null;
					subject = new Subject();
					subject.setLongName(rs.getString("shortName"));
					subjects.add(subject);
				}
			rs.close();
			ps.close();
			return subjects;
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
	public void delete(Subject instance) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM SUBJECTS WHERE NAME LIKE ?";
		 
		Connection conn = null;
 

		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(0, "%" + instance.getShortName() + "%");
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


}
