package com.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.util.JwtUtil;
@Component
public class Filter extends OncePerRequestFilter {
	@Autowired
	JwtUtil jutil;
	@Autowired
	UserDetailsService uservice;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String fulltoken=request.getHeader("Authorization");
		String uname=null;
		String token;
		if(fulltoken!=null && fulltoken.startsWith("Bearer ")) {
			token=fulltoken.substring(7);
			try {
				uname=jutil.extractUsername(token);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			UserDetails uds=uservice.loadUserByUsername(uname);
			if(uname!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken unamePwdAuthToken=
						new UsernamePasswordAuthenticationToken(uds,null,uds.getAuthorities());
				unamePwdAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(unamePwdAuthToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
