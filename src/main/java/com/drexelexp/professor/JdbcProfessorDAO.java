
package com.drexelexp.professor;

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
public class JdbcProfessorDAO extends JdbcDAO implements BaseDAO<Professor>{

	@Override
	public void insert(Professor instance) {
		String sql = "INSERT INTO PROFESSORS " + "(NAME) VALUES (?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, instance.getName());
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

	public ArrayList<Professor> getAll() {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		String sql = "SELECT * FROM PROFESSORS";
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Professor professor = null;
					professor = new Professor(
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
		
	}
	@Override
	public Professor getById(int id) {
		String sql = "SELECT * FROM PROFESSORS WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Professor professor = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				professor = new Professor(
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
	}

	public void edit(Professor professor) {
		String sql = "UPDATE PROFESSORS SET NAME = ? WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, professor.getName());
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

	public void delete(Professor professor) {
		String sql = "DELETE FROM PROFESSORS WHERE PROF_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, professor.getId());
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
	public ArrayList<Professor> searchByName(ArrayList<String> queryTerms) {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		for(String s : queryTerms) {
			String sql = "SELECT * FROM PROFESSORS WHERE NAME LIKE ?";
			 
			Connection conn = null;
	 
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + s + "%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {

					Professor professor = null;
					
					professor = new Professor(
						rs.getInt("PROF_ID"),
						rs.getString("NAME")
					);
					
					professors.add(professor);
				}
				rs.close();
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
		
		return professors;
	}
}

