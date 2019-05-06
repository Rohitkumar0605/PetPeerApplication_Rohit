package com.hcl.petpeersapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hcl.petpeersapp.dto.PetDto;
import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;

@Service
public interface PetService {

	public ResponseEntity<String> savePet(PetDto petDto);

	public List<Pet> getAllPets();

	public ResponseEntity<?> buyPet(long petId, long userId);

	public List<Pet> getMyPets(User user);

}
