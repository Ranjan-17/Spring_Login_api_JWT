package com.example.loginApi.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Builder
public class RefreshToken {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String  token;
	private Instant expiryDate;
	
	
	@OneToOne
	@JoinColumn(name="userid",referencedColumnName ="id")
	private userEntity user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	public userEntity getUserEntity() {
		return user;
	}

	public void setUserEntity(userEntity user) {
		this.user=user;
	}
	

	

	
	
	

}
