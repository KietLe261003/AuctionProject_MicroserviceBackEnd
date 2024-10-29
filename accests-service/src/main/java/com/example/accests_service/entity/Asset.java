package com.example.accests_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String assetName;
    String assetImage;
    String assetDescription;
    Integer assetPrice;
    Long inpectorId; //Id người thẩm định
    String assetType;
    Long assetStatusId;
    Boolean shppingStatus;
    Boolean delflag;

}
