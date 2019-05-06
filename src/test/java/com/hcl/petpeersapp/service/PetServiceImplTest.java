package com.hcl.petpeersapp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.petpeersapp.dto.PetDto;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;
import com.hcl.petpeersapp.repository.PetRepository;
import com.hcl.petpeersapp.repository.RegistrationRepository;
import com.hcl.petpeersapp.services.PetServiceImpl;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceImplTest {

	@Mock
	PetRepository petRepository;

	@Mock
	RegistrationRepository registrationRepository;

	@InjectMocks
	PetServiceImpl petServiceImpl;

	static PetDto petDto = null;
	static Pet pet = null;
	static List<Pet> expectval = null;
	static User user = null;
	
	static Optional<User> user1 = Optional.empty();
	static Optional<Pet> pet1 = Optional.empty();

	ResponseEntity<String> expectedvalue = new ResponseEntity<String>("Successfully-added", HttpStatus.OK);

	ResponseEntity<String> expectedvalue2 = new ResponseEntity<String>("successfully purchased", HttpStatus.OK);

	@BeforeClass
	public static void setUp() {
		expectval = new ArrayList();
		petDto = new PetDto("Cat", 2, "CT");
		pet = new Pet();

		pet.setId(1L);
		pet.setName(petDto.getName());
		pet.setAge(petDto.getAge());
		pet.setPlace(petDto.getPlace());
		pet1 = Optional.of(pet);

		expectval.add(pet);

		user = new User();
		user.setId(1L);
		user.setUsername("test");
		user.setUserPassword("test");
		user.setConfirmPassword("test");
		user1 = Optional.of(user);
	}

	@Test
	public void testSavePet() {
		/* Mockito.when(petRepository.save(pet)).thenReturn(pet); */
		ResponseEntity<?> rslt = petServiceImpl.savePet(petDto);
		Assert.assertEquals(expectedvalue, rslt);
	}

	@Test
	public void testgetMyPets() {
		Mockito.when(petRepository.findByOwner(user)).thenReturn(expectval);
		List<Pet> actual = petServiceImpl.getMyPets(user);
		Assert.assertEquals(actual.size(), expectval.size());
		/* assertThat(actual, is(expectval)); */
	}

	@Test
	public void testGetAllPets() {

		Mockito.when(petRepository.findAll()).thenReturn(expectval);
		List<Pet> actualval = petServiceImpl.getAllPets();
		Assert.assertEquals(expectval.size(), actualval.size());
		/* assertThat(actual, is(expected)); */
	}

	@Test
	public void testBuyPet() {

		Mockito.when(petRepository.findById(1L)).thenReturn(pet1);
		Mockito.when(registrationRepository.findById(1L)).thenReturn(user1);

		Mockito.when(petRepository.save(pet)).thenReturn(pet);

		ResponseEntity<?> rslt = petServiceImpl.buyPet(1L, 1L);
		Assert.assertEquals(expectedvalue2, rslt);

	}

}
