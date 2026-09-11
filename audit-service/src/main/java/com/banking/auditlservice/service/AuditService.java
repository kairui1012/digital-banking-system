package com.banking.auditlservice.service;

import com.banking.auditlservice.dto.AuditLogResponse;
import com.banking.auditlservice.dto.CreateAuditLogRequest;
import com.banking.auditlservice.entity.EventType;
import jakarta.validation.Valid;

import java.util.List;

public class AuditService {

    public List<AuditLogResponse> getRecentAuditLog() {
        return null;
    }

    public AuditLogResponse getAuditLogById(String id) {
    }

    public List<AuditLogResponse> getAuditLogByEntity(String entityId) {
    }

    public List<AuditLogResponse> getAuditLogByEventType(EventType eventType) {
    }

    public List<AuditLogResponse> getAuditLogByServiceName(String serviceName) {
    }

    public AuditLogResponse create(@Valid CreateAuditLogRequest createAuditLogRequest) {
        return null;
    }

}
