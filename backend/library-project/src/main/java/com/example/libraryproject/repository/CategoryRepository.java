package com.example.libraryproject.repository;

//CategoryRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.libraryproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
