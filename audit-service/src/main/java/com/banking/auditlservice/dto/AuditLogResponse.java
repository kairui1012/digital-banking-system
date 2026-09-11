package com.banking.auditlservice.dto;

import com.banking.auditlservice.entity.EntityType;
import com.banking.auditlservice.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuditLogResponse {
    private String id;
    private String eventId;
    private EventType eventType;
    private String serviceName;
    private EntityType entityType;
    private String entityId;
    private String action;
    private String description;
    private Map<String, Object> metadata;
    private LocalDateTime createdAt;
}
