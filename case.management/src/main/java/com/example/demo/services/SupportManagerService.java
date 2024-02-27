package com.example.demo.services;

import com.example.demo.models.SupportCase;
import com.example.demo.repositories.SupportCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportManagerService {
    private final SupportCaseRepository supportCaseRepository;

    public SupportManagerService(SupportCaseRepository supportCaseRepository) {
        this.supportCaseRepository = supportCaseRepository;
    }

    public List<SupportCase> getAllSupportCases() {
        return supportCaseRepository.findAll();
    }

}
