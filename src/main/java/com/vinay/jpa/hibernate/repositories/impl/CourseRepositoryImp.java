/**
 * 
 */
package com.vinay.jpa.hibernate.repositories.impl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.repositories.CourseRepository;

/**
 * @author Dell
 *
 */
@Repository
@Transactional
public class CourseRepositoryImp implements CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Entity manager to access data from db
	 */
	@Autowired
	EntityManager em;

	/**
	 * (non-javadoc)
	 * @see com.vinay.jpa.hibernate.repositories#findById(java.lang.Long id)
	 */
	@Override
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	/**
	 * (non-javadoc)
	 * @see com.vinay.jpa.hibernate.repositories#save(com.vinay.jpa.hibernate.entity.Course course)
	 */
	@Override
	public Course save(Course course) {
		// insert or update
		if(course.getId() == null) {
			// insert
			em.persist(course);
		}else {
			// update
			em.merge(course);
		}
		return course;
	}

	/**
	 * (non-javadoc)
	 * @see com.vinay.jpa.hibernate.repositories#deleteById(java.lang.Long id)
	 */
	@Override
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	@Override
	public void playWithEntityManager() {
		logger.info("playWithEntityManager - start");
		/*
		 * Course course = new Course("Web Service in 100 Steps"); em.persist(course);
		 * course.setName("Web Service in 100 Steps-Updated");
		 */
		/* Flush and Detach*/
		/*
		 * Course course1 = new Course("Web Service in 100 Steps"); em.persist(course1);
		 * em.flush(); course1.setName("Web Service in 100 Steps-Updated"); em.flush();
		 * Course course2 = new Course("Angular JS in 100 Steps"); em.persist(course2);
		 * em.flush(); em.detach(course2);
		 * course2.setName("Angular JS in 100 Steps-Updated"); em.flush();
		 */
		
		/*
		 * Course course1 = new Course("Web Service in 100 Steps"); em.persist(course1);
		 * Course course2 = new Course("Angular JS in 100 Steps"); em.persist(course2);
		 * em.flush(); em.detach(course2);
		 * course1.setName("Web Service in 100 Steps-Updated"); em.flush();
		 * course2.setName("Angular JS in 100 Steps-Updated"); em.flush();
		 */
		/* Clear */
		/*
		 * Course course1 = new Course("Web Service in 100 Steps"); em.persist(course1);
		 * Course course2 = new Course("Angular JS in 100 Steps"); em.persist(course2);
		 * em.flush(); em.clear(); course1.setName("Web Service in 100 Steps-Updated");
		 * em.flush(); course2.setName("Angular JS in 100 Steps-Updated"); em.flush();
		 */
		/*Refresh*/
		Course course1 = new Course("Web Service in 100 Steps"); 
		em.persist(course1);
		Course course2 = new Course("Angular JS in 100 Steps"); 
		em.persist(course2);
		em.flush(); // Flush -> commits the code to the database without any delay
		
		course1.setName("Web Service in 100 Steps-Updated");
		course2.setName("Angular JS in 100 Steps-Updated");
		em.refresh(course1);
		em.flush();
	}
}
