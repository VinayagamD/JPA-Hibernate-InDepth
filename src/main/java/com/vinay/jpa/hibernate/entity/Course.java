/**
 * 
 */
package com.vinay.jpa.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dell
 *
 */
@Entity
//@Table(name="CourseDetails")
@Table(name="Course")//By Default It is already set Course And Optional
public class Course implements Serializable {

	private static final long serialVersionUID = 3935672323783205459L;

	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name="fullname",  nullable=false)
	@Column(name="name") // Default This is the case and Optional
	private String name;
	
	

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

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	
}
