package com.example.account_service.respository;



import com.example.account_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRespository extends JpaRepository<User,Long> {
}
