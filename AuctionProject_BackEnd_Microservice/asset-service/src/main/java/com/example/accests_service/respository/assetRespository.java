package com.example.accests_service.respository;

import com.example.accests_service.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface assetRespository extends JpaRepository<Asset, Long> {
}
