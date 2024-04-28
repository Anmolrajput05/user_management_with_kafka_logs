package com.apica.assignment.UserManagement.Controller;

import com.apica.assignment.UserManagement.Model.Journal;
import com.apica.assignment.UserManagement.Services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    // Endpoint to retrieve all journals
    @GetMapping("/journal")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Journal> getAllJournals() {
        return journalService.getAllJournals();
    }

    // Endpoint to retrieve a specific journal by ID
    @GetMapping("/{journalId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Journal getJournalById(@PathVariable Long journalId) {
        return journalService.getJournalById(journalId);
    }

    // Add endpoints for create, update, delete as needed
}
