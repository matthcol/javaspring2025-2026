package org.example.basics.util;

public class Euclide {

    // equivalent Python: def gcd(a: int, b: int) -> int
    public static int gcd(int a, int b){
        while (a != b){
            if (a > b){
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}
