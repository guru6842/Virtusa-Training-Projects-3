package com.jwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.entity.User;
import com.jwt.repository.UserRepository;
@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	UserRepository urepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=urepo.findById(username);
		if(user.isPresent()) {
			return new UserDetailsImp(user.get());
		}
		else {
			throw new UsernameNotFoundException("gi");
		}
		
	}

}
