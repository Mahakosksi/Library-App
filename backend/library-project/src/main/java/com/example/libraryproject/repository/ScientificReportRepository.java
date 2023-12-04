package com.example.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryproject.model.ScientificReport;

@Repository
public interface ScientificReportRepository extends JpaRepository<ScientificReport, String> {
    // You can add custom query methods if needed
}
