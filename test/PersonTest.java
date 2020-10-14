import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-12
 * Time: 08:33
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


class PersonTest {
    Person person = new Person("Nahema Ninsson", "7805211234", LocalDate.parse("2020-08-04"));
    Person person2 = new Person("Chamade Coriola", "8512021234", LocalDate.parse("2017-03-12"));

    @Test
    public void isActiveMemberTest() {
        assertTrue(person.isActiveMember());
        assertFalse(person2.isActiveMember());
    }

    @Test
    public void addVisitationDateTest() throws IOException {
        String memberDataPath = "test/MembersFolderTest/";
        String personalId = "7805211234";
        Path path = Paths.get(memberDataPath + personalId);
        assertFalse(Files.exists(path));
        assertTrue(person.addVisitationDate(memberDataPath));
        assertTrue(Files.exists(path));
        Files.delete(path);
    }
}