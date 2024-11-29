package com.example.inspector_service.mapper;

import com.example.inspector_service.dto.request.inspector.CreateInspectorRequest;
import com.example.inspector_service.entity.Inspector;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface InspectorMapper {
    Inspector toInspector(CreateInspectorRequest inspector);
    void updateInspector(@MappingTarget Inspector inspector, CreateInspectorRequest inspectorRequest);
}
