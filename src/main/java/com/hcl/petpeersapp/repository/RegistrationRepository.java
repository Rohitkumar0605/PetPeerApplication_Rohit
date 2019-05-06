package com.hcl.petpeersapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.petpeersapp.model.User;
import java.lang.String;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
