package com.example.demo.services;

import com.example.demo.models.SupportCase;
import com.example.demo.models.User;
import com.example.demo.repositories.SupportCaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SupportCaseServiceTest {

    @Mock
    private SupportCaseRepository supportCaseRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private SupportCaseService supportCaseService;

    @Test
    public void testCreateSupportCase() {
        String username = "testUser";
        String summary = "Test summary";
        String description = "Test description";
        String screenshot = "screenshot.png";

        User user = new User();
        user.setUsername(username);
        Mockito.when(userService.findByUsername(username)).thenReturn(user);

        SupportCase supportCase = new SupportCase();
        supportCase.setSummary(summary);
        supportCase.setDescription(description);
        supportCase.setScreenshot(screenshot);
        Mockito.when(supportCaseRepository.save(Mockito.any(SupportCase.class))).thenReturn(supportCase);

        SupportCase createdCase = supportCaseService.createSupportCase(username, summary, description, screenshot);

        assertNotNull(createdCase);
        assertEquals(summary, createdCase.getSummary());
        assertEquals(description, createdCase.getDescription());
        assertEquals(screenshot, createdCase.getScreenshot());
        assertEquals(user, createdCase.getCreatedBy());
    }

    // Add more tests as needed
}
