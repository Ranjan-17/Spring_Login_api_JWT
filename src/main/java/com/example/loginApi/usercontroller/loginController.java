package com.example.loginApi.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginApi.dto.JwtResponse;
import com.example.loginApi.dto.LoginRequestDto;
import com.example.loginApi.dto.RefreshTokenRequest;
import com.example.loginApi.dto.SignupRequestDto;
import com.example.loginApi.repository.userRepository;
import com.example.loginApi.repository.roleRepository;

import com.example.loginApi.service.CustomUserDetailsService;
import com.example.loginApi.service.RefreshTokenService;
import com.example.loginApi.utils.Jwtutil;
import com.example.loginApi.entity.userEntity;
import com.example.loginApi.exception.EmailAlreadyExistException;
import com.example.loginApi.exception.NameAlreadyExistException;
import com.example.loginApi.entity.RefreshToken;
import com.example.loginApi.entity.roleEntity;
import java.util.*;

import javax.naming.NameAlreadyBoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RestController

public class loginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	userRepository UserRepository;
	
	@Autowired
	roleRepository RoleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	Jwtutil jwtutil;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	
	
	
	
	@PostMapping("/register")
	public  ResponseEntity<?> signUp(@RequestBody SignupRequestDto signupRequestDto){
		
		if(UserRepository.existsByUsername(signupRequestDto.getUsername())) {
			throw new NameAlreadyExistException("Username is already taken");
		}	
		if(UserRepository.existsByEmail(signupRequestDto.getEmail())){
			throw new EmailAlreadyExistException ("Email is already taken");
		}
		if(signupRequestDto.getUsername().length()<6 && signupRequestDto.getPassword().length()<6) {
			return new ResponseEntity<>("Username and Password atleast have 6 characters",HttpStatus.BAD_REQUEST);
		}
		
	
		
		userEntity user = new userEntity();
		user.setUsername(signupRequestDto.getUsername());
		user.setEmail(signupRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
		
		roleEntity roles=RoleRepository.findByName("MANAGER").get();
		 user.setRoles(Collections.singleton(roles));
		 
		 roleEntity roles1=RoleRepository.findByName("ADMIN").get();
		 user.setRoles(Collections.singleton(roles1)); 
		 
			
		
		UserRepository.save(user);
		return new  ResponseEntity<>("User registered Successfully",HttpStatus.OK);
		
	}
	
	
	@PostMapping("/signin")
    public JwtResponse authenticateUser(@RequestBody LoginRequestDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
        		  new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
                
        SecurityContextHolder.getContext().setAuthentication(authentication);	
        
       
        	RefreshToken refreshToken =refreshTokenService.createRefreshToken(loginDto.getUsername());
    
//        	String Jwt= jwtutil.generateJwt(authentication);
      
//        		JwtResponse.builder().accessToken(jwtutil.generateJwt(authentication));
        		
        		JwtResponse jwtResponse =new JwtResponse();
        		UserDetails userDetails =(UserDetails)authentication.getPrincipal();
        		jwtResponse.setAccessToken(jwtutil.generateJwt(userDetails));
        		jwtResponse.setToken(refreshToken.getToken());
        	
        		return  jwtResponse;
   
	
	
	}
	@PostMapping("/refreshToken")
	public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		
		 		 return refreshTokenService.findByToken(refreshTokenRequest.getToken())		 		
		 				.map(refreshTokenService::verifyExpiration)
		 				.map(RefreshToken::getUserEntity)
		 				.map(user->{
		 							String token =jwtutil.generateTokenFromUsername(user.getUsername());
		 							JwtResponse jwtResponse =new JwtResponse();
		 							jwtResponse.setAccessToken(token);
		 							jwtResponse.setToken(refreshTokenRequest.getToken());
		 							
		 							return jwtResponse;
		 				})
		 				.orElseThrow(()->new RuntimeException("RefreshToken is not in Database"));
		 				
		 				
		 					
		 				
	
		 				}

}
