package net.spring.journalApp.controller;

import net.spring.journalApp.entity.JournalEntry;
import net.spring.journalApp.entity.User;
import net.spring.journalApp.service.JournalEntryService;
import net.spring.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {

        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> addJournalEntry(@RequestBody JournalEntry e, @PathVariable String username) {

        try {
            journalEntryService.saveEntry(e, username);
            return new ResponseEntity<>(e, HttpStatus.CREATED);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id) {
      Optional<JournalEntry> journalEntry = journalEntryService.findById(id);

        if (journalEntry.isPresent()) {
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String username, @PathVariable ObjectId id) {
        journalEntryService.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable String username,
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry
    )
    {
        JournalEntry originalEntry = journalEntryService.findById(id).orElse(null);

        if (originalEntry != null) {
            if (newEntry.getTitle() != null && !newEntry.getTitle().equals("")) {
                originalEntry.setTitle(newEntry.getTitle());
            }

            if (newEntry.getContent() != null && !newEntry.getContent().equals("")) {
                originalEntry.setContent(newEntry.getContent());
            }

            journalEntryService.saveEntryPut(originalEntry);
            return new ResponseEntity<>(originalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
