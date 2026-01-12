package cinema.model.demo;

import cinema.comparator.TitleComparator;
import cinema.model.ColorEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class SortDemo {

    public static Stream<List<?>> dataComparableSource() {
        return Stream.of(
                List.of("Toulouse", "Pau", "Paris", "Bayonne"),
                List.of(12, 34, -1, 7, 87),
                List.of(
                        LocalDate.of(2000, 2, 29),
                        LocalDate.of(2024, 2, 29),
                        LocalDate.now(),
                        LocalDate.of(1900, 2, 28)
                ),
                List.of(ColorEnum.COLOR, ColorEnum.BLACK_AND_WHITE)
        );
    }

    @Test
    void demoSortPrimitiveNumbers(){
        int[] numbers = {12, 15, 7, -1, 18};
        System.out.println(Arrays.toString(numbers));
        Arrays.sort(numbers); // tri suivant l'ordre naturel du type int (défini par <)
        System.out.println(Arrays.toString(numbers));
    }

    @Test
    void demoSortBoxedNumbers(){
        List<Integer> numbers = List.of(12, 15, 7, -1, 18);
        System.out.println(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers); // tri suivant l'ordre naturel du type Integer qui est Comparable => méthode compareTo
        System.out.println(sortedNumbers);
        System.out.println("Min : " + Collections.min(numbers));
        System.out.println("Max : " + Collections.max(numbers));
    }

    @ParameterizedTest
    @MethodSource("dataComparableSource")
    <T extends Comparable<? super T>> void demoSortComparables(List<T> data){
        List<T> dataSorted = new ArrayList<>(data);
        Collections.sort(dataSorted);
        System.out.println("Sorted list: " + dataSorted);
        System.out.println("Min : " + Collections.min(dataSorted));
        System.out.println("Max : " + Collections.max(dataSorted));
        NavigableSet<T> dataSet = new TreeSet<>(data);
        System.out.println("Sorted set: " + dataSet);
    }

    @ParameterizedTest
    @CsvSource({
            "Pau, Toulouse",
            "Toulouse, Pau",
            "Toulouse, Toulouse"
    })
    void demoCompareTo(String city1, String city2){
        int result = city1.compareTo(city2);
        String format = "{0} {1} {2} (comparison = {3})";
        if (result < 0) System.out.println(MessageFormat.format(format, city1 , "<", city2, result));
        else if (result == 0) System.out.println(MessageFormat.format(format, city1 , "=", city2, result));
        else System.out.println(MessageFormat.format(format, city1 , ">", city2, result)); // case result > 0
    }

    @Test
    void demoSortComparator(){
        var data = List.of("Toulouse", "bayonne", "PAU", "paris", "VALENCE", "TROYES");
        var dataSorted = data.stream()
                .sorted()
                .toList();
        System.out.println(dataSorted); // [PAU, TROYES, Toulouse, VALENCE, bayonne, paris]  => ordre naturel = ordre technique

        var dataSortedCI = data.stream()
                // .sorted((city1, city2) -> city1.compareToIgnoreCase(city2)) // ordre externe Comparator
                .sorted(String::compareToIgnoreCase)
                .toList();
        System.out.println(dataSortedCI);

        var dataSortedComparatorObject = data.stream()
                .sorted(new TitleComparator()) // comparator objet (< Java 8)
                .toList();
        System.out.println(dataSortedComparatorObject);
    }

    // sort Movie => cf MovieCollectionDemo
}
