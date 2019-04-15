package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
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
@SpringBootTest(classes = JpaHibernateApplication.class)
public class PerfomanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Autowired
	private EntityManager em;

	@Test
	@Transactional
	public void creatingNPlusOneProblem() {

		List<Course> courses = em.createNamedQuery("query_get_all_course", Course.class).getResultList();
		courses.forEach(course -> logger.info("Course -> {} Students -> {} ", course, course.getStudents()));
	}

	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> graph = em.createEntityGraph(Course.class);
		Subgraph<Object> subgraph = graph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("query_get_all_course", Course.class)
				.setHint("javax.persistence.loadgraph", graph).getResultList();
		
		  courses.forEach(course -> logger.info("Course -> {} Students -> {} ", course,
		  course.getStudents()) );
		 
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_course_join_fetch", Course.class)
				.getResultList();
		
		  courses.forEach(course -> logger.info("Course -> {} Students -> {} ", course,
		  course.getStudents()) );
	}

}
