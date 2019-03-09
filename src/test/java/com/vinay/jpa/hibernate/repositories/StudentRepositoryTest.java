package com.vinay.jpa.hibernate.repositories;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinay.jpa.hibernate.JpaHibernateApplication;
import com.vinay.jpa.hibernate.entity.Passport;
import com.vinay.jpa.hibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EntityManager em;
	
	// Session & Session Factory
	// EntityManager & Persistence Context
	// Transaction
	@Test
	@Transactional// Persistence Context
	public void someTest() {
//		Database Operation 1 - Retrieve Student
		Student student = em.find(Student.class, 20001L);
		// Persistence Context(Student)
//		Database Operation 2 - Retrieve Passport
		Passport passport = student.getPassport();
		// Persistence Context(student, passport)
//		Database Operation 3 - Update Passport
		passport.setNumber("E123457");
		// Persistence Context(student, passport++)
//		Database Operation 4 - update student
		student.setName("Ranga - Updated");
		// Persistence Context(student++,passport++)
	}
	
	@Test
	@Transactional
	public void someTestPeristenceContext() {
		studentRepository.someOperationToUnderstandPersistanceContext();
	}
	
	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveStudentWithPassport() {
		fail("Not yet implemented");
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {} ",student);
		logger.info("passport->{}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {} ",passport);
		logger.info("student -> {}",passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourse() {
		Student student = em.find(Student.class, 20001L);
		logger.info("studwnt -> {} ", student);
		logger.info("courses->{}",student.getCourses() );
	}

}
