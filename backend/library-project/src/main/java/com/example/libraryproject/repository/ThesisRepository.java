package com.example.libraryproject.repository;



import com.example.libraryproject.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    // You can add custom query methods here if needed
}
