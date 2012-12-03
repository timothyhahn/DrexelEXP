package com.drexelexp.baseDAO;

import java.util.List;

public interface BaseDAO<T> {
	public void insert(T instance);
	public T getById(int id);
	public void update(T insance);
	public void delete(T instance);	
	
	public List<T> getAll();
	public List<T> getPage(int page, int pageSize);
}
