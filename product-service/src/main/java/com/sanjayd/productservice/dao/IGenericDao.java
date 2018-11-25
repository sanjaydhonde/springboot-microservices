package com.sanjayd.productservice.dao;

import java.io.Serializable;
import java.util.List;


public interface IGenericDao<T extends Serializable> {
	
	public void setClazz(final Class<T> clazzToSet) ;

    public T findOne(long id);

    public List<T> findAll();

    public void create(T entity);

    public T update(T entity);

    public void delete(T entity);

    public void deleteById(long entityId);
    
    public List<Object> findUsingQuery(String qlString, Object... params );

}
