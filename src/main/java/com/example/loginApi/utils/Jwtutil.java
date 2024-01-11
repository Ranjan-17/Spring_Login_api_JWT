package com.example.loginApi.utils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Jwtutil {
	
//	private static String secret ="this is secret";
	public String generateJwt(UserDetails userPrincipal) {
//			 
			return generateTokenFromUsername(userPrincipal.getUsername());
	}
	public String generateTokenFromUsername(String username) {
		Claims claims =Jwts.claims().setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60*24));
//			Set<String> roleEntity =userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//					.collect(Collectors.toSet());
//		
		return Jwts.builder().setClaims(claims).compact();
		
	}
		
		
		
		
	}
//	public String generateRefreshJwt(Authentication authentication) {
//		  UserDetails userDetails =(UserDetails)authentication.getPrincipal(); 
//
//			
//			Claims claims =Jwts.claims().setIssuer(userDetails.getUsername().toString())
//					.setIssuedAt(new Date(System.currentTimeMillis()))
//					.setExpiration(new Date(System.currentTimeMillis() + 1000 *60*2));
//				Set<String> roleEntity =userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//						.collect(Collectors.toSet());
//			
//			return Jwts.builder().setClaims(claims).claim("role:",roleEntity).compact();
//			
//	}



