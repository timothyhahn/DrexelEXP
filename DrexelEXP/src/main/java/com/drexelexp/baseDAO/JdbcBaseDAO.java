package com.drexelexp.baseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
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
	protected String getOrderByColumns(){
		return getIdColumnName();
	}
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
		columnMap.remove(getIdColumnName());
		
		String list = "(";
		String cols = "(";
		boolean first = true;
		for(String column : columnMap.keySet()){
			if(first){
				list+="?";
				cols+=column;
				first=false;
			}
			else{
				list+=",?";
				cols+=","+column;
			}
		}
		list+=")";
		cols+=")";
		
		String sql = "INSERT INTO "+getTableName()+" "+cols+" VALUES "+list;
		Connection conn = null;
 
		System.out.println(sql);
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int parameterIndex=1;
			for (String key : columnMap.keySet()) {					
				setUnknownParameter(ps,parameterIndex,columnMap.get(key));
				
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
		Map<String, Object> conditions = new Hashtable<String,Object>();
		
		conditions.put(getIdColumnName(),id);
		
		List<T> result = getWhere(conditions);
		
		if(result.size()==1)
			return result.get(0);
		
		return null;
	}
	public void update(T instance) {
		String sql = "UPDATE "+getTableName()+" SET ";
		
		Map<String,Object> columnMap = getColumnMap(instance);
		boolean first = true;
		for(String column : columnMap.keySet()){
			if(first){
				sql+=column+"=?";
				first=false;
			}
			else{
				sql+=","+column+"=?";
			}
		}
		
		sql+=" WHERE "+getIdColumnName()+" = ?";
		
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int parameterIndex = 1;
			
			for (String key :  columnMap.keySet()) {				
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
	
	private List<T> getWhere(Map<String,Object> conditions, int page, int pageSize){
		String condition = "";
		boolean first = true;
		for(String column : conditions.keySet()){
			if(first){
				condition+=column+"=?";
				first=false;
			}
			else{
				condition+=" AND "+column+"=?";
			}
		}		
		
		String sql =
				"SELECT * FROM "+getTableName()+" "+
				"WHERE "+condition+" "+
				"ORDER BY "+getOrderByColumns()+" "+
				"LIMIT "+pageSize+" OFFSET "+((page-1)*pageSize);
		
		System.out.println(sql);

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			int parameterIndex=1;
			for(String key : conditions.keySet()){
				setUnknownParameter(ps,parameterIndex,conditions.get(key));
				parameterIndex++;
			}
			
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
	
	protected List<T> getWhere(Map<String,Object> conditions){
		return getWhere(conditions,1, Integer.MAX_VALUE);
	}
	
	public List<T> getAll() {
		Map<String, Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("1",1);
		
		return getWhere(conditions);
	}
	
	public List<T> getPage(int page, int pageSize) {
		Map<String, Object> conditions = new Hashtable<String,Object>();
		
		conditions.put("1",1);
		
		return getWhere(conditions, page, pageSize);
	}
}