package cinema.model.demo;

import cinema.model.Movie;
import cinema.model.Person;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MovieDemo {

    @Test
    void demo(){
        var movie1 = new Movie();
        var movie2 = Movie.builder()
                .title("Dune: Part Two")
                .year(2024)
                .duration(166)
                .genres(Set.of("Desert Adventure", "Sci-Fi"))
                .build();
        var movie3 = Movie.builder()
                .title("One Battle After Another")
                .year(2025)
                .duration(161)
                .synopsis("When their evil enemy resurfaces after 16 years, a group of ex-revolutionaries reunite to rescue the daughter of one of their own")
                .genre("Thriller")
                .genre("Drama")
                .genre("Action")
                .genre("Crime")
                .build();
        Stream.of(movie1, movie2, movie3)
                .forEach(System.out::println);
        System.out.println();

        movie1.setTitle("Dune: Part One");
        movie1.setYear(2021);
        movie1.setDuration(155);
        System.out.println(movie1);
        System.out.println("Title: " + movie1.getTitle());
        System.out.println();

        Stream.of(movie1, movie2, movie3)
                .forEach(movie -> {
                    System.out.println(" - " + movie.getTitle());
                    System.out.println("      * release year: " + movie.getYear());
                    System.out.println("      * duration: " + movie.getDuration() + "mn");
                    System.out.println("      * genres: " + (
                            movie.getGenres().isEmpty()
                                    ? "NA"
                                    : String.join(", ", movie.getGenres())
                    ));
                    System.out.println("      * synopsis: " + Objects.toString(movie.getSynopsis(), "NA"));
                });
        System.out.println();
    }

    @Test
    void demoIntrospection(){
        Stream.of(
                "Cinema",
                Movie.builder()
                        .title("Dune: Part Two")
                        .year(2024)
                        .duration(166)
                        .genres(Set.of("Desert Adventure", "Sci-Fi"))
                        .build(),
                new Person("Zendaya", LocalDate.of(1996,9,1)),
                LocalDate.now(),
                List.of("A", "B", "C")
        ).forEach(object -> System.out.println(MessageFormat.format(
                "Object: {0} ; Class: {1}",
                object,
                object.getClass().getName()
        )));
    }

    @Test
    void demoStaticOrNotStatic(){
        // static : membre de classe (methode ou attribut)

        // attributs : constantes final + static
        System.out.println(Integer.MAX_VALUE);
        System.out.println(LocalDate.MAX);
        System.out.println(LocalDate.EPOCH);

        // méthodes : static
        var today = LocalDate.now();
        System.out.println(today);

        // membre d'instances (mode par défaut)
        System.out.println(today.getYear());
        System.out.println(today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            // getYear, format : methodes d'instance
            // ofPattern : methode de classe

    }

}