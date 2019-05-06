package com.hcl.petpeersapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.petpeersapp.dto.PetDto;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;
import com.hcl.petpeersapp.services.PetServiceImpl;

@RestController("/pets")
public class PetController {

	@Autowired
	private PetServiceImpl petServiceImpl;

	@GetMapping("/getallpets")
	public List<Pet> petHome() {
		return petServiceImpl.getAllPets();

	}

	@PostMapping("/addPet")
	public ResponseEntity<String> addPet(@RequestBody PetDto petDto) {

		return petServiceImpl.savePet(petDto);

	}

	@GetMapping("/mypets/{userId}")
	public List<Pet> myPets(@PathVariable("userId") long userId) {
		User user = new User();
		user.setId(userId);
		return petServiceImpl.getMyPets(user);

	}

	@GetMapping("/buyPet/{petId}/{userId}")
	public ResponseEntity<?> buyPet(@PathVariable("petId") long petId, @PathVariable("userId") long userId) {

		return petServiceImpl.buyPet(petId, userId);

	}

}
