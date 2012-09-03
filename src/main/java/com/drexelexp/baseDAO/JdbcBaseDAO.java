package com.drexelexp.baseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public abstract class JdbcBaseDAO<T> implements BaseDAO<T> {
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected abstract String getTableName();
	protected abstract String getIdColumnName();
	protected abstract int getId(T instance);
	protected abstract T parseResultSetRow(ResultSet rs) throws SQLException;
	protected abstract Map<String,Object> getColumnMap(T instance);
	
	private List<T> parseResultSet(ResultSet rs) throws SQLException{
		List<T> items = new ArrayList<T>();
		while (rs.next()) {
			T item = parseResultSetRow(rs);
			items.add(item);
		}
		return items;
	}
	
	private void setUnknownParameter(PreparedStatement ps, int parameterIndex, Object value) throws SQLException{		
		if(value instanceof Integer)
			ps.setInt(parameterIndex, (Integer)value);
		else if(value instanceof String)
			ps.setString(parameterIndex, (String)value);
		else if(value instanceof Float)
			ps.setFloat(parameterIndex, (Float)value);
		else if(value instanceof Timestamp)
			ps.setTimestamp(parameterIndex, (Timestamp)value);
	}

	public void insert(T instance) {
		Map<String,Object> columnMap = getColumnMap(instance);
		
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
			for (String key : columnMap.keySet()) {
					ps.setString(parameterIndex, key);
					
					setUnknownParameter(ps,parameterIndex+offset,columnMap.get(key));
					
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
		return getWhere(getIdColumnName()+" = "+id).get(0);
	}
	public void update(T instance) {
		String sql = "UPDATE "+getTableName()+" SET ? = ? ";
		
		Map<String,Object> columnMap = getColumnMap(instance);
		
		for(int i=1;i<columnMap.size();i++)
			sql+=", ? = ? ";
		
		sql+=" WHERE "+getIdColumnName()+" = ?";
		
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int parameterIndex = 1;
			
			for (String key :  columnMap.keySet()) {
					ps.setString(parameterIndex, key);
					parameterIndex++;
					
					setUnknownParameter(ps,parameterIndex,columnMap.get(key));
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
	
	protected List<T> getQuery(String query){
		String sql = query;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<T> items = parseResultSet(rs);

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
	
	protected List<T> getWhere(String condition){
		return getQuery("SELECT * FROM "+getTableName()+" WHERE "+condition);
	}
	
	public List<T> getAll() {
		return getWhere("1=1");
	}
}