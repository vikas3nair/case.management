package com.example.demo.services;

import com.example.demo.models.SupportCase;
import com.example.demo.models.User;
import com.example.demo.repositories.SupportCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class SupportCaseService {
    private final SupportCaseRepository supportCaseRepository;
    private final UserService userService;

    public SupportCaseService(SupportCaseRepository supportCaseRepository, UserService userService) {
        this.supportCaseRepository = supportCaseRepository;
        this.userService = userService;
    }

    public SupportCase createSupportCase(String username, String summary, String description, String screenshot) {
        User customer = userService.findByUsername(username);
        if (customer != null) {
            SupportCase supportCase = new SupportCase();
            supportCase.setSummary(summary);
            supportCase.setDescription(description);
            supportCase.setScreenshot(screenshot);
            supportCase.setCreatedBy(customer);
            // Set initial status, e.g., Open
            supportCase.setStatus("Open");
            return supportCaseRepository.save(supportCase);
        }
        return null;
    }

    public SupportCase getSupportCaseById(Long id) {
        return supportCaseRepository.findById(id).orElse(null);
    }
}