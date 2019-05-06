package com.hcl.petpeersapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.petpeersapp.model.Pet;
import com.hcl.petpeersapp.model.User;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	List<Pet> findByOwner(User owner);

}
