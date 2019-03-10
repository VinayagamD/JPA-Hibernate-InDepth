package com.vinay.jpa.hibernate.repositories;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
@SpringBootTest(classes = JpaHibernateApplication.class)
public class CriteriaQueryTest {
	
	private Logger logger = LoggerFactory.getLogger(CriteriaQueryTest.class);
	
	@Autowired
	EntityManager em;
	
	@Test
	public void all_courses() {
		//"Select c FROM Course c"
		
		// 1. Use criteria builder to create  a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq  = cb.createQuery(Course.class);
		// 2. Define the roots for the table which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define the Predicates etc using Criteria Builder
		
		// 4. Add predicates etc to the Criteria Query
		//5. Build the TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ",resultList);
	}
	
	@Test
	public void all_courses_having_100Steps() {
		//"Select c FROM Course c WHERE name like '%100 Steps'"
		
		// 1. Use criteria builder to create  a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq  = cb.createQuery(Course.class);
		// 2. Define the roots for the table which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define the Predicates etc using Criteria Builder
		Predicate like100Steps =  cb.like(courseRoot.get("name"), "%100 Steps");
		// 4. Add predicates etc to the Criteria Query
		cq.where(like100Steps);
		//5. Build the TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ",resultList);
	}
	
	@Test
	public void all_courses_without_students() {
		//"Select c FROM Course c WHERE c.students is empty"
		
		// 1. Use criteria builder to create  a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq  = cb.createQuery(Course.class);
		// 2. Define the roots for the table which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define the Predicates etc using Criteria Builder
		Predicate isEmpty =  cb.isEmpty(courseRoot.get("students"));
		// 4. Add predicates etc to the Criteria Query
		cq.where(isEmpty);
		//5. Build the TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ",resultList);
	}
	
	@Test
	public void join() {
		//"Select c FROM Course c JOIN c.students s"
		
		// 1. Use criteria builder to create  a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq  = cb.createQuery(Course.class);
		// 2. Define the roots for the table which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define the Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		// 4. Add predicates etc to the Criteria Query

		//5. Build the TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ",resultList);
	}
	
	@Test
	public void left_join() {
		//"Select c FROM Course c JOIN c.students s"
		
		// 1. Use criteria builder to create  a Criteria Query returning the
		// expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq  = cb.createQuery(Course.class);
		// 2. Define the roots for the table which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define the Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		// 4. Add predicates etc to the Criteria Query

		//5. Build the TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ",resultList);
	}
}
