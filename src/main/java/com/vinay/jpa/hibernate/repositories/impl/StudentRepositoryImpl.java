package com.vinay.jpa.hibernate.repositories.impl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinay.jpa.hibernate.entity.Course;
import com.vinay.jpa.hibernate.entity.Passport;
import com.vinay.jpa.hibernate.entity.Student;
import com.vinay.jpa.hibernate.repositories.StudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	EntityManager em;
	
	@Override
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	@Override
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
		return student;
	}

	@Override
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	@Override
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}

	@Override
	public void someOperationToUnderstandPersistanceContext() {
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

	@Override
	public void insertStudentAndCourse(Student student,Course course) {
		/*
		 * Student student = new Student("Jack"); Course course = new
		 * Course("Micorservices in 100 Steps");
		 */
		em.persist(student);
		em.persist(course);
		student.addCourse(course);
		course.addStudent(student);
	}

	@Override
	public void insertHardCodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Micorservices in 100 Steps");
		em.persist(student);
		em.persist(course);
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
		
	}
	
	
	
	
	

}
