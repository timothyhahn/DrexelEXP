package com.drexelexp.baseDAO;

public interface BaseDAO<T> {
	public void insert(T instance);
	public T getById(int id);
}
