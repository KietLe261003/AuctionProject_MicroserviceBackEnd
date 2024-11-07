package com.example.inspector_service.mapper;

import com.example.inspector_service.dto.request.request.CreateRequestAsset;
import com.example.inspector_service.dto.response.RequestAssetResponse;
import com.example.inspector_service.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface RequestMapper {
    Request toRequest(CreateRequestAsset createRequestAsset);
    void updateRequest(@MappingTarget Request request, CreateRequestAsset createRequestAsset);
}
