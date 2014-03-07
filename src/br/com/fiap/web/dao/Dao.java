package br.com.fiap.web.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {

	void insert(T entity);
	void update(T entity);
	void delete(Integer primaryKey) throws Exception;
	List<T> findAll();
	List<T> findEspecific(T entity);
	T findById(Integer primaryKey);
	
}
