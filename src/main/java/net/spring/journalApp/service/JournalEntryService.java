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
    public void deleteById(ObjectId id, String username) {

        try{
            User user = userService.findByUsername(username);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveEntry(user);
    //        List<JournalEntry> entries = user.getJournalEntries();
    //        Optional<JournalEntry> saved = journalEntryRepository.findById(id);
    //        if (saved.isPresent()) {
    //            entries.remove(saved.get());
    //            user.setJournalEntries(entries);
    //            userService.saveEntry(user);
    //        }
            journalEntryRepository.deleteById(id);
    }
        catch(Exception e) {
        System.out.println(e);
        throw new RuntimeException("An error occured ",e);
    }
    }

}
