package org.example.basics;


import org.example.basics.data.Person;
import org.example.basics.util.Euclide;

import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        System.out.println("On va faire du Java 21 :)");

        // Déclaration complète:
        // org.example.basics.data.Person p;

        // Déclaration simplifiée
        Person p1 = new Person();

        // Déclaration avec inférence de type
        var p2 = new Person();

        var now = LocalDateTime.now();
        var dt2 = LocalDateTime.of(2024,2,29,12,45);

        // 8 types primitifs de Java
        int nbPersons = 2; // short (16), int (32), long (62)
        double temperature = 15.4; // float (32), double (64) IEEE754
        boolean isOk = false;
        byte b = (byte) 0xf2;
        char c = 'é'; // Unicode character 16 bits

        String city = "Toulouse";


        Person personRef = p1; // share reference
        int nbPersonCopy = nbPersons;


        System.out.println(p1);
        System.out.println(p2);

        int g = Euclide.gcd(15, 21);
        System.out.println("GCD: " + g);
    }
}
