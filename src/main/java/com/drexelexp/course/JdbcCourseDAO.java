package com.drexelexp.course;
/**
 * Implementation of CourseDAO in JDBC style.
 * @author
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.drexelexp.baseDAO.BaseDAO;
import com.drexelexp.baseDAO.JdbcDAO;
import com.drexelexp.course.Course;
import com.drexelexp.professor.Professor;
import com.drexelexp.subject.Subject;

public class JdbcCourseDAO extends JdbcDAO implements BaseDAO<Course> {
	
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Course instance) {
		
		String sql = "INSERT INTO courses " + "(NAME) VALUES (?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(2, "%" +instance.getName() + "%");
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
	
	public Course search(String Coursename) {
		
		String courseSql = "Select * From courses ";
		courseSql += "Where name LIKE ?";
		
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(courseSql);
			ps.setString(2, "%" + Coursename + "%");
			Course course = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				course = new Course(
					null,
					rs.getInt("number"),
					Coursename,
					rs.getInt("CourseId")

				);
			}
			rs.close();
			ps.close();
			return course;
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

	public List<Course> listCoursesByProfessor(Professor instance) {
		
		String courseSql = "Select *  From Courses ";
		courseSql += "Where name LIKE ? ";
		
		List<Course> courseList = new ArrayList<Course>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(courseSql);
			Course course = null;
			ResultSet rs = null;
			
			for(int index=0; index < instance.getCourses().size(); index++){
				ps.setString(2, "%" + instance.getCourses().get(index).getName() + "%");
				rs = ps.executeQuery();
				while (rs.next()) {
					course = new Course(
							null,
							rs.getInt("number"),
							instance.getCourses().get(index).getName(),
							instance.getCourses().get(index).getId()

						);

					courseList.add(course);
				}
			}
			
			rs.close();
			ps.close();
			return courseList;
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


	public void delete(Course course) {
		
		String sql = "DELETE FROM COURSES WHERE COURSE_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, course.getId());
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

	@Override
	public Course getById(int id) {
		String sql = "SELECT * FROM COURSE WHERE COURSE_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Course course = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				course = new Course(
						new Subject(rs.getInt("SUBJECT_ID")), rs.getInt("COURSE_ID"),//This should be a course num
						rs.getString("NAME"), rs.getInt("COURSE_ID")
					);
			}
			
			rs.close();
			ps.close();
			return course;
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

	public ArrayList<Course> getAll() {
		ArrayList<Course> courses = new ArrayList<Course>();
		String sql = "SELECT * FROM COURSES";
		Connection conn = null;
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Course course = null;
					course = new Course(
						new Subject(rs.getInt("SUBJECT_ID")), rs.getInt("COURSE_ID"),//This should be a course num
						rs.getString("NAME"), rs.getInt("COURSE_ID")
					);
					courses.add(course);
				}
			rs.close();
			ps.close();
			return courses;
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

	public void edit(Course course) {
		String sql = "UPDATE COURSES SET NAME = ? WHERE COURSE_ID = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course.getName());
			ps.setInt(2, course.getId());
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

	public LinkedList<Course> searchByName(ArrayList<String> queryList) {
		return null;
	}
	
}

