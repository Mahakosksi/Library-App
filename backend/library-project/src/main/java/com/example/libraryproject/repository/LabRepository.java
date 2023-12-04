
package com.example.libraryproject.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.libraryproject.model.Lab;

public interface LabRepository extends JpaRepository<Lab,Integer> {
	
	Optional<Lab> findById(Integer id);
	
	@Query("SELECT lab.idlab FROM Lab lab JOIN lab.users user WHERE user.email = :userEmail")
    List<Integer> findAccessibleLabIdsByEmail(@Param("userEmail") String userEmail);

}

