package cinema.model.tu;

import cinema.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @ParameterizedTest
    @ValueSource(strings = "E.T.") // accolades du tableau statique facultatives
    void testBuilder_1title(String title) {
        var movie = Movie.builder()
                .title(title)
                .build();
        assertAll(
                () -> assertEquals(title, movie.getTitle(), "title"),
                () -> assertTrue(movie.getGenres().isEmpty(), "genres is empty")
                // TODO: test other fields
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Rambo", "The Terminator", "Fast and Furious"}) // plusieurs valeurs => tableau statique
    void testBuilder_titles(String title) {
        var movie = Movie.builder()
                .title(title)
                .build();
        assertEquals(title, movie.getTitle());
        assertTrue(movie.getGenres().isEmpty());
        // TODO: test other fields
    }

    @ParameterizedTest
    @CsvSource({
            "The Terminator, 1984, 110",
            "Rambo, 1982, 98"
    }) // value est le seul paramètre fourni
    void testBuild_args(String title, int year, Integer duration){
        var movie = Movie.builder()
                .title(title)
                .year(year)
                .duration(duration)
                .build();
        // TODO assertions
    }

    @ParameterizedTest
    @CsvSource( // value = doit etre precisé ici car on fourni un 2e paramètre
            value = {
                "The Terminator, 1984, 110",
                "Rambo, 1982, #NA"
            },
            nullValues = "#NA"
    ) // value est le seul paramètre fourni
    void testBuild_args2(String title, int year, Integer duration){
        var movie = Movie.builder()
                .title(title)
                .year(year)
                .duration(duration)
                .build();
        // TODO assertions
    }

    @Test
    void testNoArgConstructor(){

    }
}