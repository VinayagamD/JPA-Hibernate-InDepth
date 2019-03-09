package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.entity.Review;
/**
 * Repository API for accessing course
 * @author Dell
 *
 */
public interface CourseRepository {

	/**
	 * To find course from table with valid id
	 * @param id primary key of the course
	 * @return Course From Table
	 */
	Course findById(Long id);
	
	/**
	 * To save course
	 * @param course
	 * @return com.vinay.jpa.hibernate.entity.Course: Saved Successfully
	 */
	Course save(Course course);
	
	/**
	 * To remove existing course in db
	 * @param id: existing course id
	 */
	void deleteById(Long id);
	
	void playWithEntityManager();
	
	void addHardCodedReviewsForCourse();

	void addReviewsForCourse(Long courseId, List<Review> reviews);
}
