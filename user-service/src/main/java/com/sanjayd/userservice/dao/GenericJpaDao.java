package com.sanjayd.userservice.dao;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


//@Repository for exception translation 
@Repository
@Scope("prototype")
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDao<T> implements IGenericDao<T> {


    //API


}
	

