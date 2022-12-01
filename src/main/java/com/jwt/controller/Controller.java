package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.jwt.JwtRequest;
import com.jwt.service.UserDetailsServiceImp;
import com.jwt.util.JwtUtil;

@RestController
public class Controller {
	@Autowired
	UserDetailsServiceImp udservice;
	@Autowired
	AuthenticationManager authmanager;
	@Autowired
	JwtUtil jutil;
	@PostMapping("/token")   //localhost:8080/token
	public String createToken(@RequestBody JwtRequest request)
	{
		try {
			authmanager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}
		catch (BadRequest e) {
			e.printStackTrace();
		}
		UserDetails ud=udservice.loadUserByUsername(request.getUsername());
		return jutil.generateToken(ud);
		
	}
	@GetMapping("/home")
	public String home()
	{
		return " welcome home";
	}

}
