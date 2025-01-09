package net.spring.journalApp.Service;

import net.spring.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        assertEquals(4, 2+2);
    }

    @Disabled
    @Test
    public void testFindByUsername() {
        assertNotNull(userRepository.findByUsername("user1"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,12,15",
            "5,5,50"
    })
    public void parameterTest(int a, int b, int expected){
        assertEquals(expected, a+b, "failed for "+expected);
    }
}
