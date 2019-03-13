package com.vinay.jpa.hibernate.repositories;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinay.jpa.hibernate.JpaHibernateApplication;
import com.vinay.jpa.hibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseSpringDataRepository repository;
	@Autowired
	private EntityManager em;
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		logger.info("{}", courseOptional.isPresent());
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		logger.info("{}", courseOptional.isPresent());
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void playGroundWithSpringDataRepository(){
		/*
		 * Course course = new Course("MicroServices in 100 Steps");
		 * repository.save(course);
		 * course.setName("Microservices in 100 Setsps- Updated");
		 * repository.save(course);
		 */
//		Sort sort = new Sort(Sort.Direction.DESC, "name");
		List<Course> courses = repository.findAll();
		logger.info("Courses -> {}", courses);
//		logger.info("Courses -> {}", repository.findAll(sort));
		logger.info("Count -> {}", repository.count());
		logger.info("Course countByName -> {}",repository.countByName("JPA in 50 steps"));
		logger.info("Course Like '100 Steps' ->{}",repository.courseWith100StepsInName());
		logger.info("Course Like '100 Steps' Native Query -> {}",repository.courseWith100StepsInNameUsingNativeQuery());
		logger.info("Course Like '100 Steps' Named Query ->{}",repository.courseWith100StepsInNameUsingNamedQueries());
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest =  PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("FirstPage -> {}", firstPage.getContent());
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("SecondPage -> {}", secondPage.getContent());
	}
	
	@Test
	public void findUsingNames() {
		logger.info("FindByName -> {}",repository.findByName("JPA in 50 steps"));
	}
	
	

}
