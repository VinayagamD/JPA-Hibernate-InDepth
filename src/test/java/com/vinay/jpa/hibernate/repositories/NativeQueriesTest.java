package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
@SpringBootTest(classes= JpaHibernateApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;
	
	@Test
	public void native_queries_basic() {
//		Query query = em.createNativeQuery("SELECT * FROM COURSE",Course.class);
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE is_deleted=0",Course.class);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {} ", resultList);
	}
	
	@Test
	public void native_quries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID = ?",Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE ID = ? -> {} ", resultList);
	}
	
	@Test
	public void native_quries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID = :id",Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE ID = :id -> {} ", resultList);
	}
	
	@Test
	@Transactional
	public void native_quries_to_update() {
		Query query = em.createNativeQuery("UPDATE  COURSE set last_updated_date=sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {} ", noOfRowsUpdated);
	}
}
