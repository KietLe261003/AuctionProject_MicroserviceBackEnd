package com.example.inspector_service.dto.request.inspector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInspectorRequest {
    String license;
    Long userId;
}
