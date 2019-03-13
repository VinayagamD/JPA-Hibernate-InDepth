package com.vinay.jpa.hibernate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vinay.jpa.hibernate.entity.Course;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	List<Course> findByName(String name);
	Long countByName(String name);
	@Query("SELECT c FROM Course c WHERE name LIKE '%100 Steps'")
	List<Course> courseWith100StepsInName();
	@Query(value = "SELECT * FROM Course c WHERE name LIKE '%100 Steps'",nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();
	@Query(name="query_get_100_Step_courses")
	List<Course> courseWith100StepsInNameUsingNamedQueries();
}
