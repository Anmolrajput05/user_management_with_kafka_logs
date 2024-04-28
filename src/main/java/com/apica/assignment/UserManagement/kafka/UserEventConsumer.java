package com.apica.assignment.UserManagement.kafka;

import com.apica.assignment.UserManagement.Repository.JournalRepository;
import com.apica.assignment.UserManagement.Model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEventConsumer {

    @Autowired
    private JournalRepository journalRepository;

    public List<Journal> getAllUser() {
        return journalRepository.JournalList();
    }

    @KafkaListener(topics = "user-events", groupId = "user-events-consumer")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        List<Journal> journals = getAllUser();
        // Record the message in the database
        Journal journal = new Journal();
        journal.setMessage(message);
        journals.add(journal);
    }
}
