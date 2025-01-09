package net.spring.journalApp.service;

import net.spring.journalApp.entity.User;
import net.spring.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import net.spring.journalApp.entity.JournalEntry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    // Even though JournalEntryRepository is an interface, we don't need to
    // provide an implementation for it since Spring will do that for us.

    @Autowired
    private UserService userService;

    // The following will make the method atomic.
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {

        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            List<JournalEntry> entries = user.getJournalEntries();
            entries.add(saved);
            user.setJournalEntries(entries);
            userService.saveEntry(user);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured ",e);
        }
    }

    public void saveEntryPut(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
    return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removedOrNot = false;
        try{
            User user = userService.findByUsername(username);
            boolean ifRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(id));

            if(ifRemoved) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
            return ifRemoved;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured ",e);
        }
//        return false;
    }

}
