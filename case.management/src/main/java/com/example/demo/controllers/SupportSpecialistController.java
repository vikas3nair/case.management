package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.SupportCase;
import com.example.demo.services.SupportSpecialistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-specialist")
public class SupportSpecialistController {
    private final SupportSpecialistService supportSpecialistService;

    public SupportSpecialistController(SupportSpecialistService supportSpecialistService) {
        this.supportSpecialistService = supportSpecialistService;
    }

    @GetMapping("/support-cases")
    public ResponseEntity<List<SupportCase>> getAllSupportCases() {
        List<SupportCase> supportCases = supportSpecialistService.getAllSupportCases();
        return ResponseEntity.ok().body(supportCases);
    }

    @GetMapping("/support-cases/{id}")
    public ResponseEntity<SupportCase> getSupportCaseById(@PathVariable Long id) {
        SupportCase supportCase = supportSpecialistService.getSupportCaseById(id);
        if (supportCase != null) {
            return ResponseEntity.ok().body(supportCase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/support-cases/{id}/status")
    public ResponseEntity<SupportCase> updateSupportCaseStatus(@PathVariable Long id, @RequestParam String status) {
        SupportCase updatedSupportCase = supportSpecialistService.updateSupportCaseStatus(id, status);
        if (updatedSupportCase != null) {
            return ResponseEntity.ok().body(updatedSupportCase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/support-cases/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsForSupportCase(@PathVariable Long id) {
        List<Comment> comments = supportSpecialistService.getCommentsForSupportCase(id);
        if (comments != null) {
            return ResponseEntity.ok().body(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/support-cases/{id}/comments")
    public ResponseEntity<Comment> postComment(@PathVariable Long id, @RequestParam String username, @RequestParam String text) {
        Comment comment = supportSpecialistService.postComment(id, username, text);
        if (comment != null) {
            return ResponseEntity.ok().body(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
