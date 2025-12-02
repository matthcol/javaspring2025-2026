package cinema.model.demo;

import cinema.adapter.MovieCsvAdapter;
import cinema.model.Movie;
import com.opencsv.CSVParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.CsvTools;

import java.util.*;
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
        // NavigableSet<Movie> movieNavigableSet = new TreeSet<>(movieList);

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
}
