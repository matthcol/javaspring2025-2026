package org.example.basics.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class DemoCollection {

    @Test
    void demoCityList(){
        System.out.println("Demo City Collection");
        List<String> cityList = List.of("Toulouse", "Pau", "Bayonne", "Narbonne", "Lyon", "Montpellier"); // non modifiable
        System.out.println(cityList);

        // Java 5: foreach loop
        for (String city: cityList) System.out.println(city);

        for (var city: cityList){
            System.out.println("\t- " + city); // concatenation +
        }

        // for-i loop
        for (int i = 0; i < cityList.size(); i++) {
            String city = cityList.get(i);
            System.out.println("\t* " + city);
        }

        // Java 8: foreach - stream
        cityList.forEach(System.out::println); // ref fonction

        cityList.forEach(city -> System.out.println("\t~ " + city)); // lambda fonction

        // Error: java.lang.UnsupportedOperationException
        // cityList.add("Paris"); // java.lang.UnsupportedOperationException
    }

    @Test
    void demoCityModifiableList(){
        // type List : type abstrait = interface
        // types ArrayList, LinkedList : types concrets qui implémentent List
        List<String> cityList = new ArrayList<>();
        Collections.addAll(cityList,"Marseille", "Narbonne", "Montpellier");
        cityList.add("Toulouse");
        cityList.add("Pau");
        System.out.println(cityList);
    }

    @Test
    void demoCityListFilter(){
        var cityList = List.of("Toulouse", "Pau", "Bayonne", "Narbonne", "Lyon", "Montpellier", "Tarbes");
        System.out.println(cityList.getClass()); // introspection : java.util.ImmutableCollections$ListN

        for (var city: cityList){
            if (city.startsWith("T")) {
                System.out.println("City starting with T: " + city);
            }
        }
        System.out.println();

        for (var city: cityList){
            if (city.startsWith("T")) {
                System.out.println("City starting with T: " + city);
            } else {
                System.out.println("SKIP: " + city);
            }
        }
        System.out.println();

        for (var city: cityList){
            if (city.startsWith("T")) {
                System.out.println("City for work: " + city);
            } else if (city.startsWith("P")){
                System.out.println("City for week-end: " + city);
            } else {
                System.out.println("SKIP: " + city);
            }
        }
        System.out.println();

        for (var city: cityList){
            if (city.startsWith("T")){
                System.out.println("Stop at: " + city);
                break; // break: sort de la boucle for
            }
        }
        System.out.println();

        for (var city: cityList){
            if (city.startsWith("T")){
                System.out.println("SKIP: " + city);
                continue; // passe directement à l'iteration suivante
            }
            System.out.println("Stay in: " + city);
        }
        System.out.println();
    }

    @Test
    void demoSwitchCaseHistoric(){
        var cityList = List.of("Toulouse", "Pau", "Bayonne", "Narbonne", "Lyon", "Montpellier", "Tarbes");
        for (var city: cityList){
            switch (city.charAt(0)){
                case 'T':
                    System.out.println("Week-end in: " + city);
                    break; // from case
                case 'L':
                case 'P':
                    System.out.println("Work in: " + city);
                    break; // from case
                default:
                    System.out.println("SKIP: " + city);
            }
        }
    }

    @Test
    void demoSwitchCasePatternMatching(){
        // https://docs.oracle.com/en/java/javase/17/language/pattern-matching.html
        var cityList = List.of("Toulouse", "Pau", "Bayonne", "Narbonne", "Lyon", "Montpellier", "Tarbes");
        for (var city: cityList){
            switch (city.charAt(0)){
                case 'T' -> System.out.println("Week-end in: " + city);
                case 'L', 'P' -> System.out.println("Work in: " + city);
                default -> System.out.println("SKIP: " + city);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"Toulouse", "Paris", "Lyon", "Bordeaux"})
    void demoSwitchCasePatternMatchingValue(String city){
        // String city = "Toulouse";
        String program = switch (city.charAt(0)) {
            case 'T' -> "Week-end";
            case 'L', 'P' -> "Work";
            default -> "Nothing";
        };
        System.out.println("Program in " + city + ": " + program);
    }

    @Test
    void demoFilterStream(){
        var cityList = List.of("Toulouse", "Pau", "Bayonne", "Narbonne", "Lyon", "Montpellier", "Tarbes");
        cityList.stream()
                .filter(city -> city.startsWith("T"))
                .forEach(System.out::println);
        System.out.println();

        int totalLetters = cityList.stream()
                .peek(city -> System.out.println("Source: "  + city))
                .filter(city -> city.startsWith("T"))
                .peek(city -> System.out.println("After filter: " + city))
                // .map(city -> city.length())
                // .map(String::length)
                .mapToInt(String::length)
                .peek(nb_letter -> System.out.println("After map length: " + nb_letter))
                .sum();
        System.out.println("Total letters: " + totalLetters);
        System.out.println();

        List<Map<String, Integer>> cityLetterList = cityList.stream()
                .map(city -> Map.of(city, city.length()))
                .toList();
        System.out.println(cityLetterList);
    }
}
