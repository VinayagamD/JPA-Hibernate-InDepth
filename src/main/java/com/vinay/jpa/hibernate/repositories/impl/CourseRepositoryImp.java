/**
 * 
 */
package com.vinay.jpa.hibernate.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.entity.Review;
import com.vinay.jpa.hibernate.entity.ReviewRating;
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
		Course course = em.find(Course.class, id);
		logger.info("Course -> {} ",course);
		return course;
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
		/*
		 * Course course1 = new Course("Web Service in 100 Steps"); em.persist(course1);
		 * Course course2 = new Course("Angular JS in 100 Steps"); em.persist(course2);
		 * em.flush(); // Flush -> commits the code to the database without any delay
		 * 
		 * course1.setName("Web Service in 100 Steps-Updated");
		 * course2.setName("Angular JS in 100 Steps-Updated"); em.refresh(course1); //
		 * Pulls a data from table hence no update happens em.flush(); // Here flush
		 * only update course 2
		 */
		/*Test Nullable */
		/*
		 * Course course1 = new Course("Web Service in 100 Steps");
		 * course1.setName(null); em.persist(course1); em.flush();
		 */
		// Condition 2
		Course course1 = new Course("Web Service in 100 Steps");
		em.persist(course1);
//		course1.setName(null); -> Line will cause exception due to nullable false
		Course course2 = findById(10001L);
//		em.flush();
		course2.setName("JPA in 50 Steps - Updated");
		
	}

	@Override
	public void addHardCodedReviewsForCourse() {
		// get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {} ",course.getReviews());
		
		// add 2 Reviews to it
		Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff");
		Review review2 = new Review(ReviewRating.FIVE, "Hatsoff");
		
		// Setting the relationship
		course.addReview(review1);
		course.addReview(review2);
		review1.setCourse(course);
		review2.setCourse(course);
		// save it to the database
		em.persist(review1);
		em.persist(review2);
		
	}
	
	@Override
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		// get the course 10003
		Course course = findById(courseId);
		logger.info("course.getReviews() -> {}",course.getReviews());
		/*
		 * for (Review review : reviews) { course.addReview(review);
		 * review.setCourse(course); em.persist(review); }
		 */
		reviews.forEach((review)->{
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		});
		
	}
	
	
}
