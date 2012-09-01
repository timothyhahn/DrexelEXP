package com.drexelexp.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.JdbcDAO;

/**
 * Implementation of ProfessorDAO in JDBC style.
 * @author
 *
 */
public class JdbcReviewDAO extends JdbcDAO implements BaseDAO<Review>{
	
	// TODO Finish implementing this (currently copied from professor).

	@Override
	public void insert(Review instance) {
		String sql = "INSERT INTO PROFESSORS " + "(NAME) VALUES (?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, instance.getName());
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
		return new ArrayList<Review>(); // TODO delete me
		/*
		ArrayList<Review> professors = new ArrayList<Review>();
		String sql = "SELECT * FROM PROFESSORS";
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				Review professor = null;
				professor = new Review(
					rs.getInt("PROF_ID"),
					rs.getString("NAME")
				);
				professors.add(professor);
			}
			rs.close();
			ps.close();
			return professors;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		*/
	}
	@Override
	public Review getById(int id) {
		return null; // TODO delete me
		/*
		String sql = "SELECT * FROM PROFESSORS WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Review professor = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				professor = new Review(
					rs.getInt("PROF_ID"),
					rs.getString("NAME")
				);
			}
			rs.close();
			ps.close();
			return professor;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		*/
	}

	public void edit(Review professor) {
		String sql = "UPDATE PROFESSORS SET NAME = ? WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, professor.getName());
			ps.setInt(2, professor.getId());
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

	public void delete(Review professor) {
		String sql = "DELETE FROM PROFESSORS WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
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
	public ArrayList<Review> searchByName(ArrayList<String> queryTerms) {
		ArrayList<Review> professors = new ArrayList<Review>();
		/*
		for(String s : queryTerms) {
			String sql = "SELECT * FROM PROFESSORS WHERE NAME LIKE %?% ";
			 
			Connection conn = null;
	 
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, s);
				Review professor = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					professor = new Review(
						rs.getInt("PROF_ID"),
						rs.getString("NAME")
					);
				}
				rs.close();
				ps.close();
				professors.add(professor);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				if (conn != null) {
					try {
					conn.close();
					} catch (SQLException e) {}
				}
			}
		}*/
		
		return professors;
	}
}
