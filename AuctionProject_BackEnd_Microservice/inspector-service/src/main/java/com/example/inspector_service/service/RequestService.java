package com.example.inspector_service.service;

import com.example.inspector_service.base_exception.AppException;
import com.example.inspector_service.base_exception.ErrorCode;
import com.example.inspector_service.dto.request.request.CreateRequestAsset;
import com.example.inspector_service.dto.response.RequestAssetResponse;
import com.example.inspector_service.entity.Inspector;
import com.example.inspector_service.entity.Request;
import com.example.inspector_service.mapper.RequestMapper;
import com.example.inspector_service.repository.InspectorRespository;
import com.example.inspector_service.repository.RequestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRespository requestRespository;
    @Autowired
    InspectorService inspectorService;
    @Autowired
    InspectorRespository inspectorRespository;
    @Autowired
    RequestMapper requestMapper;

    public RequestAssetResponse createRequest(CreateRequestAsset requestRequest) throws AppException {
        Request request = requestMapper.toRequest(requestRequest);
        if(requestRequest.getInspectorId()!=null)
        {
            Inspector inspector = inspectorRespository.findById(requestRequest.getInspectorId()).orElseThrow(()-> new AppException(ErrorCode.Not_Found_Inspector));
            request.setInspector(inspector);
        }
        requestRespository.save(request);
        return convertToDto(request);
    }

    public List<RequestAssetResponse> getAllRequests() {
        List<Request> requests= requestRespository.findAll();
        return requests.stream().map(this::convertToDto).toList();
    }
    public RequestAssetResponse getRequestById(Long id) {
        Request request = requestRespository.findById(id).orElseThrow(()->new AppException(ErrorCode.Not_Found_Request));
        return convertToDto(request);
    }

    public RequestAssetResponse updateRequest(Long id,CreateRequestAsset requestRequest) throws AppException {
        Request request = requestRespository.findById(id).orElseThrow(()->new AppException(ErrorCode.Not_Found_Request));
        requestMapper.updateRequest(request,requestRequest);
        if(requestRequest.getInspectorId()!=null)
        {
            Inspector inspector = inspectorRespository.findById(requestRequest.getInspectorId()).orElseThrow(()-> new AppException(ErrorCode.Not_Found_Inspector));
            request.setInspector(inspector);
        }
        requestRespository.save(request);
        return convertToDto(request);
    }

    public void deleteRequestById(Long id) {
        requestRespository.deleteById(id);
    }


    public RequestAssetResponse convertToDto(Request request) {
        RequestAssetResponse requestDTO = new RequestAssetResponse();
        requestDTO.setId(request.getId());
        requestDTO.setName(request.getName());
        requestDTO.setDescription(request.getDescription());
        requestDTO.setVerify(request.getVerify());
        requestDTO.setStatus(request.getStatus());
        requestDTO.setUserId(request.getUserId());
        requestDTO.setAssetId(request.getAssetId());
        requestDTO.setDeflag(request.getDeflag());
        requestDTO.setInspectorId(request.getInspector().getId());
        return requestDTO;
    }


}
