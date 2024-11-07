package com.example.inspector_service.repository;

import com.example.inspector_service.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRespository extends JpaRepository<Request,Long> {
}
