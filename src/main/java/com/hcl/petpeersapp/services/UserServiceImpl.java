package com.hcl.petpeersapp.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.petpeersapp.dto.LoginRequest;
import com.hcl.petpeersapp.dto.RegistrationRequest;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;
import com.hcl.petpeersapp.repository.PetRepository;
import com.hcl.petpeersapp.repository.RegistrationRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private PetRepository petRepository;

	@Override
	@Transactional
	public ResponseEntity<String> add(RegistrationRequest registrationRequest) {
		User user = new User();

		user.setUsername(registrationRequest.getUsername());
		user.setUserPassword(registrationRequest.getUserPassword());
		user.setConfirmPassword(registrationRequest.getConfirmPassword());

		registrationRepository.save(user);

		return new ResponseEntity<String>("Success - registered", HttpStatus.OK);
	}

	@Override
	public List<Pet> securityService(LoginRequest loginRequest) {
		User user = registrationRepository.findByUsername(loginRequest.getUsername());

		if (user.getUserPassword().equals(loginRequest.getUserPassword())) {

			return petRepository.findAll();
		}
		return null;

	}

}
