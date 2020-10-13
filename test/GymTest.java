import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Toshiko Kuno
 * Date: 2020-10-10
 * Time: 16:57
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class GymTest {
    static Gym gymTest = new Gym();


    @Test
    public void readPersonalNumberDataTest() {
        Scanner idInput = new Scanner("12345");
        assertThrows(IllegalArgumentException.class, () -> gymTest.readPersonalNummerData(idInput));
        Scanner idInput2 = new Scanner("9911112222");
        assertEquals(gymTest.readPersonalNummerData(idInput2),"9911112222");
    }

    @Test
    public void readNameDataTest() {
        Scanner nameInput = new Scanner("bear belle   ");
        assertTrue(gymTest.readNameData(nameInput).equalsIgnoreCase("Bear Belle"));
        Scanner nameInput2 = new Scanner("   ");
        assertThrows(NoSuchElementException.class, () -> gymTest.readNameData(nameInput2));
    }

}
