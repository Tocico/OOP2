import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-12
 * Time: 08:26
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


class MembersTest {
    static String path = "test/customersTest.txt";
    Members members = new Members();

    @Test
    public void generateMemberListFromFileAndGetMemberTest() {
        assertTrue(members.generateMemberListFromFile(path));
        String searchWord = "Alhambra Aromes";
        assertEquals(members.getMember(searchWord)
                .getName(),"Alhambra Aromes");
        searchWord = "Toshiko Kuno";
        assertNull(members.getMember(searchWord));
    }

}