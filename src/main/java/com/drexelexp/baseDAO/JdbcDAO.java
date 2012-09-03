package com.drexelexp.baseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.drexelexp.professor.Professor;

public abstract class JdbcDAO<T> implements BaseDAO<T> {
	
	
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected abstract String getTableName();
	protected abstract String getIdColumnName();
	protected abstract int getId(T instance);
	protected abstract T parseResultSetRow(ResultSet rs) throws SQLException;
	protected abstract Dictionary<String,Object> getColumnMap(T instance);
	protected abstract List<String> getSearchableColumns();
	
	public void insert(T instance) {
		Dictionary<String,Object> columnMap = getColumnMap(instance);
		
		String list = "(?";
		for(int i=1;i<columnMap.size();i++)
			list+=",?";
		list+=")";
		
		String sql = "INSERT INTO "+getTableName()+" "+list+" VALUES "+list;
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int offset = columnMap.size();
			int parameterIndex=1;
			for (Enumeration<String> e = columnMap.keys(); e.hasMoreElements() ;) {
					String key=e.nextElement();

					ps.setString(parameterIndex, key);
					
					Object value = columnMap.get(key);
					Class type = value.getClass();
					
					if(type.equals(Integer.class))
						ps.setInt(parameterIndex+offset, (Integer)value);
					else if(type.equals(String.class))
						ps.setString(parameterIndex+offset, (String)value);
					
					parameterIndex++;
		     }
			
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
	public T getById(int id) {
		String sql = "SELECT * FROM "+getTableName()+" WHERE "+getIdColumnName()+" = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			T item = null;
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				item = parseResultSetRow(rs);
			}
			
			rs.close();
			ps.close();
			
			return item;
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
	public void update(T instance) {
		String sql = "UPDATE "+getTableName()+" SET ? = ? ";
		
		Dictionary<String,Object> columnMap = getColumnMap(instance);
		
		for(int i=1;i<columnMap.size();i++)
			sql+=", ? = ? ";
		
		sql+=" WHERE "+getIdColumnName()+" = ?";
		
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int parameterIndex = 1;
			
			for (Enumeration<String> e = columnMap.keys(); e.hasMoreElements() ;) {
					String key=e.nextElement();

					ps.setString(parameterIndex, key);
					parameterIndex++;
					
					Object value = columnMap.get(key);
					Class type = value.getClass();
					
					if(type.equals(Integer.class))
						ps.setInt(parameterIndex, (Integer)value);
					else if(type.equals(String.class))
						ps.setString(parameterIndex, (String)value);
					
					parameterIndex++;
		     }
			
			ps.setInt(parameterIndex, getId(instance));
			
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
	public void delete(T instance) {
		String sql = "DELETE FROM "+getTableName()+" WHERE "+getIdColumnName()+" = ?";
		 
		Connection conn = null;
 
		try {
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getId(instance));
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
	
	public List<T> search(String query) {
		//TODO update to search multiple columns
		LinkedList<T> items = new LinkedList<T>();
		
		String sql = "SELECT * FROM " + getTableName() + " WHERE "+getSearchableColumns().get(0)+" LIKE ?";

		for (String queryPart : Arrays.asList(query.split(" "))) {

			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, "%" + queryPart + "%");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					T item = parseResultSetRow(rs);

					T toMod = null;
					boolean itemNotFound = true;
					for (T i : items) {
						if (getId(i) == getId(item)) {
							toMod = i; // Because modifying while iterating does
										// stupid things...
							itemNotFound = false;
						}
					}

					if (itemNotFound)
						items.add(item);
					else {
						items.remove(toMod);
						items.addFirst(toMod);
					}
				}

				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		}

		return items;
	}

	public List<T> getAll() {
		ArrayList<T> items = new ArrayList<T>();
		String sql = "SELECT * FROM " + getTableName();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				T item = parseResultSetRow(rs);
				items.add(item);
			}

			rs.close();
			ps.close();

			return items;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}