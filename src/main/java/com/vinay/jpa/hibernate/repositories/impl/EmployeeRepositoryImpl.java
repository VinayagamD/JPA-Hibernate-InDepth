package com.vinay.jpa.hibernate.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinay.jpa.hibernate.entity.Employee;
import com.vinay.jpa.hibernate.entity.FullTimeEmployee;
import com.vinay.jpa.hibernate.entity.PartTimeEmployee;
import com.vinay.jpa.hibernate.repositories.EmployeeRepository;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;

	// insert an Employee
	@Override
	public void insert(Employee employee) {
		em.persist(employee);
	}

	

	// retrieve all employee
	// Commented sIce MappedSupperClass Wont Work
	/*
	 * @Override public List<Employee> retrieveAllEmployee() { return
	 * em.createQuery("SELECT e FROM Employee e",Employee.class).getResultList(); }
	 */

	// This code is for mapped supper class
	
	@Override
	public List<FullTimeEmployee> retrieveAllFullTimeEmployee() {
		return  em.createQuery("SELECT e FROM FullTimeEmployee e",FullTimeEmployee.class).getResultList();
	}

	@Override
	public List<PartTimeEmployee> retrieveAllPartTimeEmployee() {
		return  em.createQuery("SELECT e FROM PartTimeEmployee e",PartTimeEmployee.class).getResultList();
	}
	
}
