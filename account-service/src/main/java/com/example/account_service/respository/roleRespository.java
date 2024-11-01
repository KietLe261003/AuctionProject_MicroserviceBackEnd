package com.example.account_service.respository;

import com.example.account_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRespository extends JpaRepository<Role,Long> {
}
