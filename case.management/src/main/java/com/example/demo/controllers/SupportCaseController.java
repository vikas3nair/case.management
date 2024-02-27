package com.example.demo.controllers;

import com.example.demo.models.SupportCase;
import com.example.demo.services.SupportCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support-cases")
public class SupportCaseController {
    private final SupportCaseService supportCaseService;

    public SupportCaseController(SupportCaseService supportCaseService) {
        this.supportCaseService = supportCaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSupportCase(@RequestParam String username,
                                               @RequestParam String summary,
                                               @RequestParam String description,
                                               @RequestParam(required = false) String screenshot) {
        SupportCase supportCase = supportCaseService.createSupportCase(username, summary, description, screenshot);
        if (supportCase != null) {
            return ResponseEntity.ok().body("Support case created successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create support case. Invalid username");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportCase> getSupportCaseById(@PathVariable Long id) {
        SupportCase supportCase = supportCaseService.getSupportCaseById(id);
        if (supportCase != null) {
            return ResponseEntity.ok().body(supportCase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}