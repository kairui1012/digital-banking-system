package com.banking.auditlservice.dto;

import com.banking.auditlservice.entity.EntityType;
import com.banking.auditlservice.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateAuditLogRequest {

    @NotNull(message = "Event ID is required")
    private String eventId;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    @NotBlank(message = "Service name is required")
    private String serviceName;

    @NotNull(message = "Entity type is required")
    private EntityType entityType;

    @NotBlank(message = "Entity ID is required")
    private String entityId;

    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "Description is required")
    private String description;

    private Map<String, Object> metadata;
}
