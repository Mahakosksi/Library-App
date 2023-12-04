package com.example.libraryproject.repository;

import com.example.libraryproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // You can add custom query methods here if needed
}
