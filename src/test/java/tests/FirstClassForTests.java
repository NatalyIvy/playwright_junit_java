package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstClassForTests {

    @Test
    @DisplayName("Calculate 2*2")
    void calculator() {
        Assertions.assertEquals(4, 2 * 2);
    }

}
