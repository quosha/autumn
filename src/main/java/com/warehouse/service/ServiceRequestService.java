package com.warehouse.service;

import com.warehouse.model.ServiceRequest;
import com.warehouse.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public ServiceRequest save(ServiceRequest request) {
        return serviceRequestRepository.save(request);
    }
}
