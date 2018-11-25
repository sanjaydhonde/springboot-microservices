package com.sanjayd.productservice.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;
    
    @PersistenceContext
    private EntityManager entityManager;

	public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) {
        return entityManager.find(clazz, id);
    }

	@SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
	
    public void create(final T entity) {
        entityManager.persist(entity);
    }
	
    public T update(final T entity) {
        return entityManager.merge(entity);
    }
	
    public void delete(final T entity) {
        entityManager.remove(entity);
    }
	
    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }
    
    public List<Object> findUsingQuery(String qlString, Object... params ){
    	Query query=entityManager.createQuery(qlString);
    	if(params != null){
    		for(int i=0;i < params.length;i++){
    			query.setParameter(i + 1, params[i]);
    		}
    	}
    	return (List<Object>) query.getResultList();
    }

}
