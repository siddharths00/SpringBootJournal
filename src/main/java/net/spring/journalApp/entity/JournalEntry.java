package net.spring.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//If you don't mention anything in brackets here then Spring will search for "JournalEntry"
//in the collections available in mongo db
@Data
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    // This is an object ID type of
    // mongodb.
    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
}
