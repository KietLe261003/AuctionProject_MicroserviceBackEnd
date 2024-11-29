package com.example.inspector_service.dto.request.request;

import com.example.inspector_service.entity.Inspector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequestAsset {
    String name;
    String description;
    Boolean verify;
    Boolean status;
    Long inspectorId;//id nhà kiểm định
    Long userId; //id người yêu cầu kiểm định
    Long assetId; //id của sản phẩm kiểm định
    Boolean deflag;
}
