package com.hcl.petpeersapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.petpeersapp.dto.PetDto;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;
import com.hcl.petpeersapp.repository.PetRepository;
import com.hcl.petpeersapp.repository.RegistrationRepository;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	@Transactional
	public ResponseEntity<String> savePet(PetDto petDto) {
		Pet pet = new Pet();

		pet.setName(petDto.getName());
		pet.setAge(petDto.getAge());
		pet.setPlace(petDto.getPlace());

		petRepository.save(pet);

		return new ResponseEntity<String>("Successfully-added", HttpStatus.OK);
	}

	@Override
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}

	@Override
	public ResponseEntity<?> buyPet(long petId, long userId) {
		Pet pet = petRepository.findById(petId).get();
		User user = registrationRepository.findById(userId).get();
		pet.setOwner(user);
		petRepository.save(pet);
		return new ResponseEntity<String>("successfully purchased", HttpStatus.OK);
	}

	@Override
	public List<Pet> getMyPets(User user) {
		return petRepository.findByOwner(user);
	}

}
