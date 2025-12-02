package org.example.basics.util.tu;

import org.example.basics.util.Euclide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EuclideTest {

    @Test
    void testGcd_nominal(){
        // wikipedia presentation case
        int a = 15;
        int b = 21;
        int g = Euclide.gcd(a, b);
        assertEquals(3, g);
    }

    // https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1",
            "1, 7, 1",
            "7, 1, 1",
            "15, 21, 3",
            "21, 15, 3",
            "514_229, 317_811, 1",
            "317_811, 514_229, 1",
    })
    void testGcd_ok(int a, int b, int expected_gcd){
        int actual_gcd = Euclide.gcd(a, b);
        assertEquals(expected_gcd, actual_gcd);

    }


}