package com.example.inspector_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAssetResponse {
    Long id;
    String name;
    String description;
    Boolean verify;
    Boolean status;
    Long userId;
    Long assetId;
    Boolean deflag;
    Long inspectorId;
}
