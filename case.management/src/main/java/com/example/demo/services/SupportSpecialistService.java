package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.models.SupportCase;
import com.example.demo.models.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.SupportCaseRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportSpecialistService {
    private final UserRepository userRepository;
    private final SupportCaseRepository supportCaseRepository;
    private final CommentRepository commentRepository;

    public SupportSpecialistService(UserRepository userRepository, SupportCaseRepository supportCaseRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.supportCaseRepository = supportCaseRepository;
        this.commentRepository = commentRepository;
    }

    public List<SupportCase> getAllSupportCases() {
        return supportCaseRepository.findAll();
    }

    public SupportCase getSupportCaseById(Long id) {
        return supportCaseRepository.findById(id).orElse(null);
    }

    public SupportCase updateSupportCaseStatus(Long id, String status) {
        SupportCase supportCase = supportCaseRepository.findById(id).orElse(null);
        if (supportCase != null) {
            supportCase.setStatus(status);
            return supportCaseRepository.save(supportCase);
        }
        return null;
    }

    public List<Comment> getCommentsForSupportCase(Long supportCaseId) {
        SupportCase supportCase = supportCaseRepository.findById(supportCaseId).orElse(null);
        if (supportCase != null) {
            return commentRepository.findBySupportCaseOrderByCreatedAtAsc(supportCase);
        }
        return null;
    }

    public Comment postComment(Long supportCaseId, String username, String text) {
        SupportCase supportCase = supportCaseRepository.findById(supportCaseId).orElse(null);
        User user = userRepository.findByUsername(username);
        if (supportCase != null && user != null) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setUser(user);
            comment.setSupportCase(supportCase);
            return commentRepository.save(comment);
        }
        return null;
    }
}
