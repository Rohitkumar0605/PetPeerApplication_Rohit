package com.hcl.petpeersapp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.petpeersapp.dto.LoginRequest;
import com.hcl.petpeersapp.dto.PetDto;
import com.hcl.petpeersapp.dto.RegistrationRequest;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;
import com.hcl.petpeersapp.repository.PetRepository;
import com.hcl.petpeersapp.repository.RegistrationRepository;
import com.hcl.petpeersapp.services.UserServiceImpl;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	RegistrationRepository registrationRepository;

	@Mock
	PetRepository petRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	static User user = null;

	static List<Pet> pet1 = null;

	static LoginRequest loginRequest = null;

	static RegistrationRequest registrationRequest = null;

	ResponseEntity<String> expectedvalue = new ResponseEntity<String>("Success - registered", HttpStatus.OK);
	static List<Pet> expectval = null;
	static PetDto petDto = null;
	static Pet pet = null;

	@BeforeClass
	public static void setUp() {
		registrationRequest = new RegistrationRequest();

		registrationRequest.setUsername("test");
		registrationRequest.setUserPassword("test");
		registrationRequest.setConfirmPassword("test");

		user = new User();
		user.setUsername("test");
		user.setUserPassword("test");
		user.setConfirmPassword("test");

		loginRequest = new LoginRequest();

		loginRequest.setUsername("test");
		loginRequest.setUserPassword("test");

		expectval = new ArrayList();
		petDto = new PetDto("Cat", 2, "CT");

		pet = new Pet();

		pet.setName(petDto.getName());
		pet.setAge(petDto.getAge());

		expectval.add(pet);

	}

	@Test
	public void testAdd() {
		ResponseEntity<?> actval = userServiceImpl.add(registrationRequest);
		Assert.assertEquals(expectedvalue, actval);

	}

	@Test
	public void testsecurityService() {
		Mockito.when(registrationRepository.findByUsername(loginRequest.getUsername())).thenReturn(user);
		Mockito.when(petRepository.findAll()).thenReturn(expectval);

		List<Pet> actualval = userServiceImpl.securityService(loginRequest);
		Assert.assertEquals(expectval.size(), actualval.size());
	}

}
