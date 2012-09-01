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
import java.util.List;

import javax.sql.DataSource;
import com.drexelexp.course.Course;
import com.drexelexp.professor.Professor;

public class JdbcCourseDAO implements CourseDAO {
	
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Course instance) {
		// TODO Auto-generated method stub
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
	
	@Override
	public Course search(String Coursename) {
		// TODO Auto-generated method stub
		
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

	@Override
	public List<Course> listCourses(Professor instance) {
		// TODO Auto-generated method stub
		
		
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


	@Override
	public void delete(Course course) {
		
		String sql = "DELETE FROM Courses WHERE NAME LIKE ?";
		 
		Connection conn = null;
 

		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(2, "%" + course.getName() + "%");
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

