package com.example.demo.controllers;

import com.example.demo.models.SupportCase;
import com.example.demo.services.SupportManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/support-manager")
public class SupportManagerController {
    private final SupportManagerService supportManagerService;

    public SupportManagerController(SupportManagerService supportManagerService) {
        this.supportManagerService = supportManagerService;
    }

    @GetMapping("/support-cases")
    public ResponseEntity<List<SupportCase>> getAllSupportCases() {
        List<SupportCase> supportCases = supportManagerService.getAllSupportCases();
        return ResponseEntity.ok().body(supportCases);
    }

    // Add endpoints for generating statistics and applying filters
}
