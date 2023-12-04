package com.example.libraryproject.repository;

import java.util.List;

//RegularBookRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.libraryproject.model.RegularBook;

public interface RegularBookRepository extends JpaRepository<RegularBook, String> {
	
	@Query("SELECT rb FROM RegularBook rb " +
	           "WHERE rb.idpub IN " +
	           "(SELECT p.idpub FROM Publication p WHERE p.idstatus = 2 AND p.idlab = :idlab)")
	List<RegularBook> findRegularBooksByLabAndStatus2(@Param("idlab") Long labId);
	
}
