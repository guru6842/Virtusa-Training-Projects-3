package com.jwt.shool.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int studentid;
	private String name;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fromdate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate todate;
	private String reason;
	private String status;
	
}
