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
import com.jwt.shool.entity.Student;
import com.jwt.shool.repository.StudentRepository;
@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentRepository stuRepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	@PostMapping("add")   //localhost:8080/student/add
	public void addStudent(@RequestBody Student stuObj )
	{
		String uname=""+stuObj.getId();
		String name=stuObj.getName();
		String pwd=name.substring(0, 4)+"@123";
		User user=new User();
		user.setUsername(uname);
		user.setPassword(bcrypt.encode(pwd));
		Role role=new Role();
		role.setRolename("ROLE_STUDENT");
		user.setUrole(role);
		userrepo.save(user);
		
		stuRepo.save(stuObj);
	}
	@GetMapping("list")   //localhost:8080/student/list
	public List<Student> listOfStudents()
	{
		return stuRepo.findAll();
		 
	}
	@GetMapping("/{id}")
	public Optional<Student> searchStudent(@PathVariable int id)
	{
		//Student ob=new Student(id, null, null, null, null, null)
		return stuRepo.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable int id)
	{
		//userrepo.deleteById(""+id);
		stuRepo.deleteById(id);
	}
	@PutMapping("/{id}")
	public Student update(@PathVariable int id,@RequestBody Student stude) {
		Student sss=stuRepo.findById(id).orElse(null);
		return stuRepo.save(stude);
	}
}
