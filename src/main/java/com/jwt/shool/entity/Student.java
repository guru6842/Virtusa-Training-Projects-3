package com.jwt.shool.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	private int id;
	private String name;
	private String gender;
	private String grade;
	
	private String parentName;
	private String contactNumber;
	
	@OneToOne(cascade=CascadeType.ALL)
	private StudentGrades stuGrd;
	
}
