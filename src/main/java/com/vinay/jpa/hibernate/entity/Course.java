/**
 * 
 */
package com.vinay.jpa.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.asm.Advice.This;

/**
 * @author Dell
 *
 */
@Entity
//@Table(name="CourseDetails")
@Table(name="Course")//By Default It is already set Course And Optional
@NamedQueries(value= {
		@NamedQuery(name="query_get_all_course", query="SELECT c FROM Course c"),
		@NamedQuery(name="query_get_all_course_join_fetch", query="SELECT c FROM Course c JOIN FETCH c.students s"),
		@NamedQuery(name="query_get_100_Step_courses", query="SELECT c FROM Course c WHERE name LIKE '%100 Steps'")
})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id = ?")// Set specific condition for delete 
@Where(clause = "is_deleted = false")
public class Course implements Serializable {

	private static final long serialVersionUID = 3935672323783205459L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name="fullname",  nullable=false)
//	@Column(name="name") // Default This is the case and Optional
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting is Deleted to True");
		this.isDeleted = true;
	}

	public Course() {
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	public Course(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
		
	}
	
	
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", lastUpdatedDate=" + lastUpdatedDate + ", createdDate="
				+ createdDate + "] Reviews["+reviews+"]";
	}
	
}
