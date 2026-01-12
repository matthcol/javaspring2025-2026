package cinema.model.demo;

import cinema.adapter.MovieCsvAdapter;
import cinema.model.Movie;
import com.opencsv.CSVParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.CsvTools;

import java.awt.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieCollectionDemo {

    static List<Movie> movieList;

    @BeforeAll
    static void readData(){
        try (
                Stream<String[]> stream = CsvTools.streamFromTsv("src/test/resources/movies.tsv")
        ){
            movieList = stream
                    .map(MovieCsvAdapter::movieFromCsvLine)
                    .toList();
        } // auto - close
    }


    @Test
    void demoStreamLimit(){
        movieList.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void demoCollections(){
        List<Movie> movieList2 = new ArrayList<>(movieList);
        Set<Movie> movieSet = new HashSet<>(movieList);

        // TODO: comment trier les movies ???
        // NavigableSet<Movie> movieNavigableSet = new TreeSet<>(movieList); // pas d'ordre naturel => ordre externe

        movieList2.stream().limit(10).forEach(System.out::println);
        System.out.println();
        movieSet.stream().limit(10).forEach(System.out::println);
        System.out.println();

//        movieNavigableSet.stream().limit(10).forEach(System.out::println);

        NavigableSet<String> titles = movieList.stream()
                .filter(movie -> movie.getYear() == 1999)
                .map(Movie::getTitle)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(titles);
    }

    @Test
    void demoSortMovies(){
        List<Movie> movieListSorted = new ArrayList<>(movieList);
        // Collections.sort(movieListSorted, (m1, m2) -> -1);

        Collections.sort(movieListSorted, Comparator.comparing(Movie::getTitle));
        System.out.println(movieListSorted);
        System.out.println();

        Collections.sort(
                movieListSorted,
                Comparator.comparing(Movie::getYear)
                        .thenComparing(Movie::getTitle)
        );
        System.out.println(movieListSorted);
        System.out.println();
    }

    public static Stream<Arguments> movieComparatorSource() {
        return Stream.of(
                Arguments.of(
                        Comparator.comparing(Movie::getTitle),
                        "by title"
                ),
                Arguments.of(
                        Comparator.comparingInt(Movie::getYear)
                            .thenComparing(Movie::getTitle),
                        "by year, title"
                ),
                Arguments.of(
                        Comparator.comparing(Movie::getYear, Comparator.reverseOrder()) // ordre inverse de l'ordre naturel
                                .thenComparing(Movie::getTitle, String::compareToIgnoreCase),
                        "by year desc, title CI"
                ),
                Arguments.of(
                        Comparator.comparing(Movie::getDuration, Comparator.nullsLast(Comparator.reverseOrder()))
                        ,
                        "by duration desc"
                )
        );
    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("movieComparatorSource")
    void demoSortMovieComparator(Comparator<Movie> comparator, String label) {
        List<Movie> movieListSorted = new ArrayList<>(movieList);
        Collections.sort(movieListSorted, comparator);
        movieListSorted.forEach(movie -> System.out.println(MessageFormat.format(
                "{0} (year = {1}, duration = {2})",
                movie.getTitle(),
                movie.getYear(),
                movie.getDuration()
        )));
    }
}
