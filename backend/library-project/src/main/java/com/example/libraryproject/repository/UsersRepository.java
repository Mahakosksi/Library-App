package com.example.libraryproject.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryproject.model.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
	
	Optional<Users> findByLoginAndPassword(String login, String password);

}
