package com.example.inspector_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspectorResponse {
    Long id;
    String license;
    Long userId;
}
