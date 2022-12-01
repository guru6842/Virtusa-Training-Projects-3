package com.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.UserRepository;

@SpringBootApplication
public class JwtAuthentication2Application implements CommandLineRunner{
	
	@Autowired
	UserRepository userrepo;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthentication2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1=new User();
		u1.setUsername("user1");
		u1.setPassword(bcrypt.encode("user1"));
		Role role=new Role();
		role.setRolename("ROLE_ADMIN");
		u1.setUrole(role);
		userrepo.save(u1);
		
		
	}

}
