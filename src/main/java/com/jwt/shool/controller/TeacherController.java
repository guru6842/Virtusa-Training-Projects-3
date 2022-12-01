package com.jwt.shool.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.UserRepository;
import com.jwt.shool.entity.Teacher;
import com.jwt.shool.repository.TeacherRepository;

@RestController
@RequestMapping("teacher")
public class TeacherController {
	@Autowired
	TeacherRepository techRepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	User user=new User();
	@PostMapping("add")   //localhost:8080/teacher/add
	public void addTeacher(@RequestBody Teacher techObj )
	{
		String uname=techObj.getPhoneNumber();
		String tname=techObj.getName();
		String pwd=tname.substring(0, 4)+"@123";
		
		user.setUsername(uname);
		user.setPassword(bcrypt.encode(pwd));
		Role role=new Role();
		role.setRolename("ROLE_TEACHER");
		user.setUrole(role);
		userrepo.save(user);
		techRepo.save(techObj);
	}
	@GetMapping("list")   //localhost:8080/teacher/list
	public List<Teacher> listOfTeachers()
	{
		return techRepo.findAll();
		 
	}
	
	@GetMapping("/{phoneNo}")
	public Optional<Teacher> searchTeacher(@PathVariable String phoneNo)
	{
		return techRepo.findById(phoneNo);
	}
	@DeleteMapping("/{phoneNo}")
	public void deleteStudent(@PathVariable String phoneNo)
	{
		//techdbrepo.deleteById(phoneNo);
		//userrepo.deleteById(phoneNo);
		techRepo.deleteById(phoneNo);
	}
	@PutMapping("/{phoneNo}")
	public Teacher update(@PathVariable String phoneNo,@RequestBody Teacher techObj) {
		Teacher tech=techRepo.findById(phoneNo).orElse(null);
		return techRepo.save(techObj);
	}
	
}
