package com.vinay.jpa.hibernate.repositories;

import com.vinay.jpa.hibernate.entity.Student;

public interface StudentRepository {
	
	Student findById(Long id);
	Student save(Student student);
	void deleteById(Long id);
	void saveStudentWithPassport();
	void someOperationToUnderstandPersistanceContext();

}
