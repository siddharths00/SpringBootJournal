package net.spring.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;
    // Searching on Mongodb for username would be easy.
    // Non Null is part of Lombok and when we will be
    // setting these values, the setter will check for
    // these.
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    // The following acts as a foreign key.
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;
}
