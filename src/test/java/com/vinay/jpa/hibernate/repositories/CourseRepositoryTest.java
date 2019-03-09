package com.vinay.jpa.hibernate.repositories;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinay.jpa.hibernate.JpaHibernateApplication;
import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private EntityManager em;
	
	@Test
	public void contextLoads() {
		logger.info("Testing is Running");
	}
	
	@Test
	public void findById_basicTest() {
		logger.info("running findById_basicTest");
		Course course = repository.findById(10001l);
		assertNotNull(course);
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	@Test
	public void findById_InvalidCourseName() {
		logger.info("findById_InvalidCourseName");
		Course course = repository.findById(10001l);
		assertNotNull(course);
		assertNotEquals("JPA in 100 steps", course.getName());
	}
	
	@Test
	public void findById_InvalidId() {
		logger.info("running findById_InvalidId");
		Course course = repository.findById(1l);
		assertNull(course);
	}
	
	
	@Test
	@DirtiesContext// Some Testcase requires data which is deleted by 
				  //  this test case, hence dirties context avoids such scenario 
	public void deleteById_basicTest() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	
	@Test
	@DirtiesContext
	public void save_basic() {
		//get course
		Course course = repository.findById(10002L);
		assertEquals("Spring in 50 Steps", course.getName());
		//update details
		course.setName("Spring in 50 Steps-Updated");
		repository.save(course);
		//check the values
		Course course1 = repository.findById(10002L);
		assertEquals("Spring in 50 Steps-Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}
	
	@Test
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10003l);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001l);
		logger.info("{}", review.getCourse());
	}
	

}
