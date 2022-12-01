package com.jwt.shool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrades {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstClass;
	private String secondClass;
	private String thirdClass;
	private String fourthClass;
	private String fifthClass;
	private String sixthClass;
	private String seventhClass;
	private String eighthClass;
	private String ninthClass;
	private String tenthClass;
}
