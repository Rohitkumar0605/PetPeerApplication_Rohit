package com.hcl.petpeersapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.petpeersapp.dto.LoginRequest;
import com.hcl.petpeersapp.dto.RegistrationRequest;
import com.hcl.petpeersapp.model.Pet;

@Service
public interface UserService {

	public ResponseEntity<String> add(RegistrationRequest registrationRequest);

	public List<Pet> securityService(LoginRequest loginRequest);

}
