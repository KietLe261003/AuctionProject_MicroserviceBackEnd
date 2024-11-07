package com.example.inspector_service.service;

import com.example.inspector_service.base_exception.AppException;
import com.example.inspector_service.base_exception.ErrorCode;
import com.example.inspector_service.dto.request.inspector.CreateInspectorRequest;
import com.example.inspector_service.entity.Inspector;
import com.example.inspector_service.mapper.InspectorMapper;
import com.example.inspector_service.repository.InspectorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectorService {
    @Autowired
    InspectorRespository respository;
    @Autowired
    InspectorMapper inspectorMapper;

    public Inspector createInspector(CreateInspectorRequest inspectorRequest) {
        Inspector inspector = inspectorMapper.toInspector(inspectorRequest);
        return respository.save(inspector);
    }

    public Inspector getInspectorById(Long id) {
        Inspector inspector = respository.findById(id).orElseThrow(()-> new AppException(ErrorCode.Not_Found_Inspector));
        return inspector;
    }
    public List<Inspector> findAll() {
        return respository.findAll();
    }

    public Inspector updateInspector(Long id,CreateInspectorRequest createInspectorRequest) {
        Inspector inspector = getInspectorById(id);
        inspectorMapper.updateInspector(inspector,createInspectorRequest);
        return respository.save(inspector);
    }

    public void deleteInspector(Long id) {
        respository.deleteById(id);
    }
}
