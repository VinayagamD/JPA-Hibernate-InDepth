package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinay.jpa.hibernate.JpaHibernateApplication;
import com.vinay.jpa.hibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basics() {
		List resultList = em.createQuery("SELECT c FROM Course c").getResultList();
		logger.info("SELECT c FROM Course c -> {}",resultList);
	}
	
	@Test
	public void jpql_Typed() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT c FROM Course c -> {}",resultList);
	}
	
	@Test
	public void jpql_Typed_named_query() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_course",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT c FROM Course c -> {}",resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE name LIKE '%100 Steps'",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT c FROM Course c WHERE name LIKE '%100 Steps' -> {}",resultList);
	}
	
	
	
	
}
