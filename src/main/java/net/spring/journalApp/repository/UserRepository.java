package net.spring.journalApp.repository;

import net.spring.journalApp.entity.JournalEntry;
import net.spring.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}
