package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import com.vinay.jpa.hibernate.entity.Student;

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
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students is empty",Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Results -> {} ",courses);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2",Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Results -> {} ",courses);
	}
	
	@Test
	public void jpql_courses_with_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY SIZE(c.students) DESC",Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Results -> {} ",courses);
	}
	
	@Test
	public void jpql_students_with_passport_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number like '%1234%'",Student.class);
		List<Student> courses = query.getResultList();
		logger.info("Results -> {} ",courses);
	}
	
	@Test
	public void join() {
		Query query = em.createQuery("SELECT c,s FROM Course c JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {} ",resultList.size());
		resultList.forEach((result)->{
			logger.info("Course{}, Student{}",result[0],result[1]);
		});
	}
	
	@Test
	public void left_join() {
		Query query = em.createQuery("SELECT c,s FROM Course c LEFT JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {} ",resultList.size());
		resultList.forEach((result)->{
			logger.info("Course{}, Student{}",result[0],result[1]);
		});
	}
	
	@Test
	public void cross_join() {
		Query query = em.createQuery("SELECT c, s FROM Course c , Student s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {} ",resultList.size());
		resultList.forEach((result)->{
			logger.info("Course{}, Student{}",result[0],result[1]);
		});
	}
	
}
