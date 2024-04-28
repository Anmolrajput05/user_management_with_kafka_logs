package com.apica.assignment.UserManagement.Repository;

import com.apica.assignment.UserManagement.Model.User;
import com.apica.assignment.UserManagement.Model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalRepository {
    @Autowired
    private List<User> userList;

    @Autowired
    private List<Journal>  Journals;

    public List<Journal> JournalList(){

        return Journals;
    }


    public Journal findById(Long journalId) {
        List<Journal> Journals = JournalList();
        for (Journal journal : Journals) {
            if(journal.getId().equals(journalId))
                return journal;
        }
        return null;
    }

}
