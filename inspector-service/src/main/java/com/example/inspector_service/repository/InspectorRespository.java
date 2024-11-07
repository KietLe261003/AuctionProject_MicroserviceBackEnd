package com.example.inspector_service.repository;


import com.example.inspector_service.entity.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorRespository extends JpaRepository<Inspector, Long> {
}
