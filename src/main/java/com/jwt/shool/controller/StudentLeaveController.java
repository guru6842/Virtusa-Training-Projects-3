package com.jwt.shool.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.shool.entity.Student;
import com.jwt.shool.entity.StudentLeave;
import com.jwt.shool.repository.StudentLeaveRepository;
import com.jwt.shool.repository.StudentRepository;

@RestController
@RequestMapping("studentleave")
public class StudentLeaveController {
	@Autowired
	StudentRepository studentrepo;
	@Autowired
	StudentLeaveRepository sleaverepo;
	
	@PostMapping("resquest")  //localhost:8080/studentleave/resquest
	public String studentLeaveRequest(@RequestBody StudentLeave studentleave)
	{
		Optional<Student> student = studentrepo.findById(studentleave.getStudentid());
		System.out.println(studentleave.getStudentid()+".."+studentleave.getName());
		if(!student.isPresent())
		{
			return "Invalid Studnet Id";
		}
		studentleave.setStatus("Requested");
		sleaverepo.save(studentleave);
		return "leave request sent and yet to be approved";
		
	}
	@GetMapping("/{id}/{status}")
	public String studentLeaveStatus(@PathVariable int id,@PathVariable("status") String status )
	{
		Optional<StudentLeave> studentleave = sleaverepo.findById(id);
		if(!studentleave.isPresent())
		{
			return "Invalid Studnet Id";
		}
		StudentLeave leave=studentleave.get();
		leave.setStatus(status);
		sleaverepo.save(leave);
		return "status updated";
	}
	@GetMapping("allleaveRequest")
	public List<StudentLeave> allLeaversRequest()
	{
		return sleaverepo.findAll();
	}
	@GetMapping("newleaves")
	public List<StudentLeave> newLeaversRequest()
	{
		return sleaverepo.newLeaverRequest();
	}
	@GetMapping("myleaves")
	public List<StudentLeave> myLeaves()
	{
		String studnetid=SecurityContextHolder.getContext().getAuthentication().getName();
		int id=Integer.parseInt(studnetid);
		return sleaverepo.myLeaves(id);
	
	}
}
