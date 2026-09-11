package com.banking.auditlservice.controller;

import com.banking.auditlservice.dto.AuditLogResponse;
import com.banking.auditlservice.dto.CreateAuditLogRequest;
import com.banking.auditlservice.entity.EventType;
import com.banking.auditlservice.service.AuditService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audits")
@Slf4j
@RequiredArgsConstructor

public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public ResponseEntity<List<AuditLogResponse>> getRecentAuditLog(){
        return ResponseEntity.ok(auditService.getRecentAuditLog());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponse> getAuditLogById(@PathVariable String id){
        return ResponseEntity.ok(auditService.getAuditLogById(id));
    }

    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<AuditLogResponse>> getAuditLogByEntity(@PathVariable String entityId){
        return ResponseEntity.ok(auditService.getAuditLogByEntity(entityId));
    }

    @GetMapping("/event/{eventType}")
    public ResponseEntity<List<AuditLogResponse>> getAuditLogByEventType(@PathVariable EventType eventType){
        return ResponseEntity.ok(auditService.getAuditLogByEventType(eventType));
    }

    @GetMapping("/service/{serviceName}")
    public ResponseEntity<List<AuditLogResponse>> getAuditLogByServiceName(@PathVariable String serviceName){
        return ResponseEntity.ok(auditService.getAuditLogByServiceName(serviceName));
    }

    @PostMapping("/create-audit")
    public ResponseEntity<AuditLogResponse> createAuditLog(@Valid @RequestBody CreateAuditLogRequest createAuditLogRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(auditService.create(createAuditLogRequest));
    }
}
