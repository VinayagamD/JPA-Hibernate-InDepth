/**
 * 
 */
package com.vinay.jpa.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Dell
 *
 */
@Entity
//@Table(name="CourseDetails")
@Table(name="Course")//By Default It is already set Course And Optional
@NamedQueries(value= {
		@NamedQuery(name="query_get_all_course", query="SELECT c FROM Course c"),
		@NamedQuery(name="query_get_100_Step_courses",
		query="SELECT c FROM Course c WHERE name LIKE '%100 Steps'")
})
public class Course implements Serializable {

	private static final long serialVersionUID = 3935672323783205459L;

	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name="fullname",  nullable=false)
//	@Column(name="name") // Default This is the case and Optional
	@Column(nullable=false)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	

	public Course() {
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
				+ createdDate + "]";
	}
	
}
