
package com.example.libraryproject.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryproject.model.DbUser;

public interface DbUserRepository extends JpaRepository<DbUser,String> {
	
	DbUser findByEmail(String email);

}
