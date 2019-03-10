package com.vinay.jpa.hibernate;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.entity.FullTimeEmployee;
import com.vinay.jpa.hibernate.entity.PartTimeEmployee;
import com.vinay.jpa.hibernate.entity.Review;
import com.vinay.jpa.hibernate.entity.Student;
import com.vinay.jpa.hibernate.repositories.CourseRepository;
import com.vinay.jpa.hibernate.repositories.EmployeeRepository;
import com.vinay.jpa.hibernate.repositories.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Course course = repository.findById(10001L);
		 * logger.info("Course  10001 -> {} ", course); repository.deleteById(10001L);
		 * repository.save(new Course("Microservices in 100 steps"));
		 */
//		courseRepository.playWithEntityManager();
//		studentRepository.saveStudentWithPassport();
//		courseRepository.addHardCodedReviewsForCourse();
		/*
		 * List<Review> reviews = new ArrayList<>(); Review review1= new Review("5",
		 * "Great Hands-on Stuff."); Review review2= new Review("5", "Hatsoff");
		 * reviews.add(review1); reviews.add(review2);
		 * courseRepository.addReviewsForCourse(10003L,reviews);
		 */
//		studentRepository.insertStudentAndCourse();
//		studentRepository.insertHardCodedStudentAndCourse();
//		studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));
		/*
		 * employeeRepository.insert(new PartTimeEmployee("Jill", new
		 * BigDecimal("50")));
		 * 
		 * employeeRepository.insert(new FullTimeEmployee("Jack", new
		 * BigDecimal("10000")));
		 */
		
//		logger.info("All Employees -> {} ", employeeRepository.retrieveAllEmployee());
		/*
		 * logger.info("All FullTimeEmployees -> {} ",
		 * employeeRepository.retrieveAllFullTimeEmployee());
		 * logger.info("All PartTimeEmployees -> {} ",
		 * employeeRepository.retrieveAllPartTimeEmployee());
		 */
	}
	

}
