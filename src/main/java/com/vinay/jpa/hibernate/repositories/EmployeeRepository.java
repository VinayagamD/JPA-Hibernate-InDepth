package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import com.vinay.jpa.hibernate.entity.Employee;
import com.vinay.jpa.hibernate.entity.FullTimeEmployee;
import com.vinay.jpa.hibernate.entity.PartTimeEmployee;

public interface EmployeeRepository {
	
	void insert(Employee employee);
//	List<Employee> retrieveAllEmployee();
	List<FullTimeEmployee> retrieveAllFullTimeEmployee();
	List<PartTimeEmployee> retrieveAllPartTimeEmployee();
}
