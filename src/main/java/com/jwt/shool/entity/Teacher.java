 package com.jwt.shool.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	private String name;
	private String subject;
	@Id
	private String phoneNumber;
	private String mailId;
	private String gender;
	
}
