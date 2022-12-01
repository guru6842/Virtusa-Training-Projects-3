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

import com.jwt.shool.entity.StudentLeave;
import com.jwt.shool.entity.Teacher;
import com.jwt.shool.entity.TeacherLeave;
import com.jwt.shool.repository.TeacherLeaveRepository;
import com.jwt.shool.repository.TeacherRepository;

@RestController
@RequestMapping("teacherleave")
public class TeacherLeaveController {
	@Autowired
	TeacherRepository teachrepo;
	@Autowired
	TeacherLeaveRepository teachleaverepo;
	@PostMapping("resquest")  //localhost:8080/teacherleave/resquest
	public String teacherLeaveRequest(@RequestBody TeacherLeave teachleave)
	{
		Optional<Teacher> tech = teachrepo.findById(teachleave.getPhoneno());
		if(!tech.isPresent())
		{
			return "Invalid teacher phone number";
		}
		teachleave.setStatus("Requested");
		teachleaverepo.save(teachleave);
		return "leave request sent and yet to be approved";
		
	}
	@GetMapping("myleaves")
	public List<TeacherLeave> myLeaves()
	{
		String techerphone=SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println(techerid);
		//int id=Integer.parseInt(techerid);
		return teachleaverepo.myLeaves(techerphone);
	
	}
	@GetMapping("allleaveRequest")
	public List<TeacherLeave> allLeaversRequest()
	{
		return teachleaverepo.findAll();
	}
	@GetMapping("newleaves")
	public List<TeacherLeave> newLeaversRequest()
	{
		return teachleaverepo.newLeaverRequest();
	}
	@GetMapping("/{id}/{status}")
	public String teacherLeaveStatus(@PathVariable int id,@PathVariable("status") String status )
	{
		Optional<TeacherLeave> teachleave = teachleaverepo.findById(id);
		if(!teachleave.isPresent())
		{
			return "Invalid Studnet Id";
		}
		TeacherLeave leave=teachleave.get();
		leave.setStatus(status);
		teachleaverepo.save(leave);
		return "status updated";
	}
}
