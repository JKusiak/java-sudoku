import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTest {

    @Test
    public void isHelloing() {
        Hello newHello = new Hello();
        String result = newHello.forTest("Hi there ", "Number five");
        assertEquals("Hi there Number five", result);
    }
}