package com.drexelexp.baseDAO;

import javax.sql.DataSource;

public abstract class JdbcDAO {
	protected DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}