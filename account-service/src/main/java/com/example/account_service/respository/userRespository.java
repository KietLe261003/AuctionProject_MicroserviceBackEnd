package com.example.account_service.respository;



import com.example.account_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRespository extends JpaRepository<User,Long> {
}
